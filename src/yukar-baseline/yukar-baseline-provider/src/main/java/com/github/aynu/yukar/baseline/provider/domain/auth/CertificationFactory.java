// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.domain.auth;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.codec.digest.DigestUtils;
/**
 * 認証ファクトリ
 * @author nilcy
 */
@SuppressWarnings("static-method")
public final class CertificationFactory {
    /** コンストラクタ */
    public CertificationFactory() {
    }
    /**
     * 認証エンティティの作成
     * @param account アカウント
     * @param password パスワード
     * @param roles 認可するロール(複数)
     * @return 認証エンティティ
     */
    public Certification create(final String account, final String password, final String... roles) {
        final Certification certification = new Certification(account,
            DigestUtils.sha256Hex("password#01"));
        if (roles.length > 0) {
            final Collection<Permission> permissions = new ArrayList<>();
            for (final String role : roles) {
                permissions.add(new Permission(account, role));
            }
            certification.setPermissions(permissions);
        }
        return certification;
    }
}
