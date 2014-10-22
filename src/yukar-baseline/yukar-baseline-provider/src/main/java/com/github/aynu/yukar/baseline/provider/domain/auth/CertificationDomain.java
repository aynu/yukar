// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.domain.auth;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.Validate;
import com.github.aynu.yukar.framework.lang.AbstractValueObject;
/**
 * 認証ドメイン
 * @author nilcy
 */
public class CertificationDomain extends AbstractValueObject<CertificationDomain> {
    /** 識別番号 */
    private static final long serialVersionUID = -7875471541734976140L;
    /** 認証エンティティ */
    private final Certification certification;
    /**
     * コンストラクタ
     * @param certification {@link #certification}
     */
    public CertificationDomain(final Certification certification) {
        this.certification = certification;
    }
    /**
     * パスワード変更
     * <dl>
     * <dt>使用条件
     * <dd>SHA-256ダイジェスト(HEXエンコーディング)化したパスワードで、新しい認証エンティティを返却すること。
     * </dl>
     * @param password 新パスワード(空でないこと,新旧パスワードが同一でないこと)
     * @return 認証エンティティ
     */
    public Certification changePassword(final String password) {
        Validate.notBlank(password);
        final String newPassword = DigestUtils.sha256Hex(password);
        Validate.isTrue(!newPassword.equals(certification.getPassword()),
            "The validated password is same");
        return new Certification(certification.getAccount(), newPassword);
    }
}
