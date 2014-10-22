// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.domain.auth;
import com.github.aynu.yukar.framework.lang.AbstractValueObject;
/**
 * 認証エンティティ検索条件
 * @author nilcy
 */
public class CertificationFilter extends AbstractValueObject<CertificationFilter> {
    /** 識別番号 */
    private static final long serialVersionUID = -985653761854953733L;
    /** アカウント */
    private final String account;
    /**
     * コンストラクタ
     * @param account {@link #account}
     */
    public CertificationFilter(final String account) {
        this.account = account;
    }
    /**
     * {@link #account} の取得
     * @return {@link #account}
     */
    public final String getAccount() {
        return account;
    }
}
