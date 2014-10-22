// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.domain.auth;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.github.aynu.yukar.framework.persistence.AbstractVersionable;
/**
 * 認可エンティティ
 * @author nilcy
 */
@Entity
@Table(name = "Permission", uniqueConstraints = { @UniqueConstraint(columnNames = { "account",
    "role" }) })
@NamedQueries({ @NamedQuery(name = "Permission.findUK_account_role", query = "SELECT e FROM Permission e WHERE e.account=:account AND e.role=:role") })
// CHECKSTYLE:OFF
public class Permission extends AbstractVersionable<Permission> {
    /** 識別番号 */
    private static final long serialVersionUID = -2989313024762807768L;
    /** アカウント */
    @Column(name = "account", nullable = false)
    private String account;
    /** ロール */
    @Column(name = "role", nullable = false)
    private String role;
    /** コンストラクタ */
    public Permission() {
    }
    /**
     * コンストラクタ
     * @param account {@link #account}
     * @param role {@link #role}
     */
    public Permission(final String account, final String role) {
        this.account = account;
        this.role = role;
    }
    /**
     * {@link #account} の取得
     * @return {@link #account}
     */
    public final String getAccount() {
        return account;
    }
    /**
     * {@link #account} の設定
     * @param account {@link #account}
     */
    public final void setAccount(final String account) {
        this.account = account;
    }
    /**
     * {@link #role} の取得
     * @return {@link #role}
     */
    public final String getRole() {
        return role;
    }
    /**
     * {@link #role} の設定
     * @param role {@link #role}
     */
    public final void setRole(final String role) {
        this.role = role;
    }
}
// CHECKSTYLE:ON
