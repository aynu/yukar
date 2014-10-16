// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.framework;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.Crypt;
/**
 * コーデックヘルパー
 * <dl>
 * <dt>使用条件
 * <dd>コーデック関連の操作に使用すること。
 * </dl>
 * @author nilcy
 */
public final class CodecHelper {
    /** 非公開コンストラクタ */
    private CodecHelper() {
    }
    /**
     * Hex(16進数)エンコード
     * <dl>
     * <dt>使用条件
     * <dd>Hex(16進数)エンコードに使用すること。
     * </dl>
     * @param data バイナリデータ
     * @return Hex(16進数)エンコード後データ
     */
    public static char[] encodeHex(final byte[] data) {
        return Hex.encodeHex(data);
    }
    /**
     * Hex(16進数)デコード
     * <dl>
     * <dt>使用条件
     * <dd>Hex(16進数)デコードに使用すること。
     * </dl>
     * @param data バイナリデータ
     * @return Hex(16進数)デコード後データ
     * @throws DecoderException デコード例外
     */
    public static byte[] decodeHex(final char[] data) throws DecoderException {
        return Hex.decodeHex(data);
    }
    /**
     * Base64エンコード
     * <dl>
     * <dt>使用条件
     * <dd>Base64エンコードに使用すること。
     * </dl>
     * @param data バイナリデータ
     * @return Base64エンコード後データ
     */
    public static byte[] encodeBase64(final byte[] data) {
        return Base64.encodeBase64(data);
    }
    /**
     * Base64デコード
     * <dl>
     * <dt>使用条件
     * <dd>Base64デコードに使用すること。
     * </dl>
     * @param data バイナリデータ
     * @return Base64デコード後データ
     */
    public static byte[] decodeBase64(final byte[] data) {
        return Base64.decodeBase64(data);
    }
    /**
     * crypt互換ハッシュ
     * <dl>
     * <dt>使用条件
     * <dd>GNU libc crypt(3) compatible hash method.
     * </dl>
     * @param key キー
     * @return ハッシュ化したデータ
     */
    public static String crypt(final String key) {
        return Crypt.crypt(key);
    }
    /**
     * crypt互換ハッシュ
     * <dl>
     * <dt>使用条件
     * <dd>GNU libc crypt(3) compatible hash method.
     * </dl>
     * @param key キー
     * @param salt ソルト
     * @return ハッシュ化したデータ
     */
    public static String crypt(final String key, final String salt) {
        return Crypt.crypt(key, salt);
    }
}
