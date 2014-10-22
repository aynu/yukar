// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.domain;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * エンティティマネージャ作成
 * @author nilcy
 */
public class EntityManagerProducer {
    /** エンティティマネージャ */
    @PersistenceContext
    @Produces
    private EntityManager manager;
    /** コンストラクタ */
    public EntityManagerProducer() {
    }
}
