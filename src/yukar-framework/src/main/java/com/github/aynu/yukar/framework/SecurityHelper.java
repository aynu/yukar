// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.framework;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/**
 * セキュリティヘルパー
 * <dl>
 * <dt>使用条件
 * <dd>セキュリティ関連の操作に使用すること。
 * </dl>
 * @author nilcy
 */
public final class SecurityHelper {
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
}
