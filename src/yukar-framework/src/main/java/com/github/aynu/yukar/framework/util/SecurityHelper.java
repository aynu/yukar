// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.framework.util;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.aynu.yukar.framework.lang.StandardRuntimeException;
/**
 * セキュリティヘルパー
 * <dl>
 * <dt>使用条件
 * <dd>セキュリティ関連の操作に使用すること。
 * </dl>
 * @author nilcy
 */
public final class SecurityHelper {
    /** ロガー */
    private static final Logger LOG = LoggerFactory.getLogger(SecurityHelper.class);
    /** 非公開コンストラクタ */
    private SecurityHelper() {
    }
    /**
     * 乱数生成器の取得
     * <dl>
     * <dt>使用条件
     * <dd>乱数生成器を取得するときに使用すること。
     * </dl>
     * @return 最大強度の乱数生成器
     * @throws NoSuchAlgorithmException 該当アルゴリズムなし例外
     */
    public static SecureRandom getSecureRandom() throws NoSuchAlgorithmException {
        return SecureRandom.getInstanceStrong();
    }
    /**
     * RSA公開鍵ペアの作成
     * <dl>
     * <dt>使用条件
     * <dd>RSAの鍵ペア生成器を作成し、2048ビット長の鍵ペアを作成すること。
     * </dl>
     * @return RSA公開鍵ペア
     */
    public static KeyPair createKeyPair() {
        try {
            final KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            final KeyPair pair = generator.generateKeyPair();
            if (LOG.isDebugEnabled()) {
                final RSAPublicKey publicKey = (RSAPublicKey) pair.getPublic();
                final RSAPrivateKey privateKey = (RSAPrivateKey) pair.getPrivate();
                LOG.debug("public-modulus={}",
                    Base64.encodeBase64String(publicKey.getModulus().toByteArray()));
                LOG.debug("public-exponent={}",
                    Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray()));
                LOG.debug("private-modulus={}",
                    Base64.encodeBase64String(privateKey.getModulus().toByteArray()));
                LOG.debug("private-exponent={}",
                    Base64.encodeBase64String(privateKey.getPrivateExponent().toByteArray()));
            }
            return pair;
        } catch (final NoSuchAlgorithmException e) {
            throw new StandardRuntimeException(e);
        }
    }
    /**
     * RSA公開鍵の作成
     * <dl>
     * <dt>使用条件
     * <dd>RSAの鍵生成器を作成し、引数をもとに公開鍵を作成すること。
     * </dl>
     * @param modulus モジュラス
     * @param exponent エクスポーネント
     * @return RSA公開鍵
     */
    public static RSAPublicKey createPublicKey(final BigInteger modulus, final BigInteger exponent) {
        try {
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) keyFactory
                .generatePublic(new RSAPublicKeySpec(modulus, exponent));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new StandardRuntimeException(e);
        }
    }
    /**
     * RSA秘密鍵の作成
     * <dl>
     * <dt>使用条件
     * <dd>RSAの鍵生成器を作成し、引数をもとに秘密鍵を作成すること。
     * </dl>
     * @param modulus モジュラス
     * @param exponent エクスポーネント
     * @return RSA秘密鍵
     */
    public static RSAPrivateKey createPrivateKey(final BigInteger modulus, final BigInteger exponent) {
        try {
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(new RSAPrivateKeySpec(modulus,
                exponent));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new StandardRuntimeException(e);
        }
    }
    /**
     * パスワード共通鍵の作成
     * <dl>
     * <dt>使用条件
     * <dd>PBKDF2WithHmacSHA1の秘密鍵生成器を作成し、引数をもとに繰返し65536件、キー長128で共通鍵を作成すること。
     * </dl>
     * @param password パスワード
     * @param salt ソルト
     * @return パスワード共通鍵
     */
    public static SecretKey createSecretKey(final char[] password, final byte[] salt) {
        try {
            final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            final KeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
            return factory.generateSecret(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new StandardRuntimeException(e);
        }
    }
    /**
     * AES共通鍵の作成
     * <dl>
     * <dt>使用条件
     * <dd>AESの共通鍵を作成すること。
     * </dl>
     * @param key 鍵データ
     * @return AES共通鍵
     */
    public static SecretKey createSecretKey(final byte[] key) {
        return new SecretKeySpec(key, "AES");
    }
    /**
     * 小規模データの暗号
     * <dl>
     * <dt>使用条件
     * <dd>RSA/ECB/PKCS1Paddingで引数をもとに暗号すること。
     * </dl>
     * @param key 暗号キー
     * @param input 入力データ
     * @return 暗号データ
     */
    public static byte[] encrypt(final Key key, final byte[] input) {
        try {
            final Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(input);
        } catch (final NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
            | IllegalBlockSizeException | BadPaddingException e) {
            throw new StandardRuntimeException(e);
        }
    }
    /**
     * 小規模データの復号
     * <dl>
     * <dt>使用条件
     * <dd>RSA/ECB/PKCS1Paddingで引数をもとに復号すること。
     * </dl>
     * @param key 暗号キー
     * @param input 入力データ
     * @return 復号データ
     */
    public static byte[] decrypt(final Key key, final byte[] input) {
        try {
            final Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(input);
        } catch (final NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
            | IllegalBlockSizeException | BadPaddingException e) {
            throw new StandardRuntimeException(e);
        }
    }
    /**
     * 大規模データの暗号
     * <dl>
     * <dt>使用条件
     * <dd>AES/CBC/PKCS5Paddingで引数をもとに暗号すること。
     * </dl>
     * @param key 暗号キー
     * @param iv 初期化ベクター
     * @param input 入力データ
     * @return 暗号データ
     */
    public static byte[] encrypt(final Key key, final IvParameterSpec iv, final byte[] input) {
        try {
            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            return cipher.doFinal(input);
        } catch (final NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
            | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            throw new StandardRuntimeException(e);
        }
    }
    /**
     * 大規模データの復号
     * <dl>
     * <dt>使用条件
     * <dd>AES/CBC/PKCS5Paddingで引数をもとに復号すること。
     * </dl>
     * @param key 暗号キー
     * @param iv 初期化ベクター
     * @param input 入力データ
     * @return 復号データ
     */
    public static byte[] decrypt(final Key key, final IvParameterSpec iv, final byte[] input) {
        try {
            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            return cipher.doFinal(input);
        } catch (final NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
            | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            throw new StandardRuntimeException(e);
        }
    }
    /**
     * 署名鍵ペアの作成
     * <dl>
     * <dt>使用条件
     * <dd>ECの鍵ペア生成器を作成し、256ビット長の鍵ペアを作成すること。
     * </dl>
     * @return 鍵ペア(公開鍵と秘密鍵)
     */
    public static KeyPair createSignKeyPair() {
        try {
            final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(256);
            return keyPairGenerator.generateKeyPair();
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 署名の作成
     * <dl>
     * <dt>使用条件
     * <dd>NONEwithECDSAの署名生成器を作成し、引数をもとに署名を作成すること。
     * </dl>
     * @param key 秘密鍵
     * @param data データ
     * @return 署名
     */
    public static byte[] sign(final PrivateKey key, final byte[] data) {
        try {
            final Signature sign = Signature.getInstance("NONEwithECDSA");
            sign.initSign(key);
            sign.update(data);
            return sign.sign();
        } catch (final NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new StandardRuntimeException(e);
        }
    }
    /**
     * 署名の検証
     * <dl>
     * <dt>使用条件
     * <dd>NONEwithECDSAの署名生成器を作成し、引数をもとに署名を検証すること。
     * </dl>
     * @param publicKey 公開鍵
     * @param message メッセージ
     * @param signature 署名
     * @return 検証結果
     */
    public static boolean verify(final PublicKey publicKey, final byte[] signature,
        final byte[] message) {
        try {
            final Signature sign = Signature.getInstance("NONEwithECDSA");
            sign.initVerify(publicKey);
            sign.update(message);
            return sign.verify(signature);
        } catch (final NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new StandardRuntimeException(e);
        }
    }
}
