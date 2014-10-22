// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.domain.auth;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.github.aynu.yukar.framework.persistence.SmartRepository;
import com.github.aynu.yukar.framework.persistence.SmartRepositoryImpl;
import com.github.aynu.yukar.framework.persistence.SmartRepositoryListener;
/**
 * 認証リポジトリ作成
 * @author nilcy
 */
public class CertificationRepositoryProducer {
    /** エンティティマネージャ */
    @Inject
    private EntityManager manager;
    /** コンストラクタ */
    public CertificationRepositoryProducer() {
    }
    /**
     * 先進リポジトリの作成
     * @return 先進リポジトリI/F
     */
    @Produces
    public SmartRepository<Certification, CertificationFilter> createSmartRepository() {
        final SmartRepositoryListener<Certification, CertificationFilter> listener = (builder,
            query, root, filter) -> query.select(root).where(
                builder.equal(root.get("account"), filter.getAccount()));
            return new SmartRepositoryImpl<>(Certification.class, manager, listener);
    }
}
