// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.framework.persistence;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * @see Testee
 * @author nilcy
 */
@RunWith(Arquillian.class)
@Transactional(value = TransactionMode.ROLLBACK)
@SuppressWarnings("boxing")
public class TesteeTest {
    /** エンティティマネージャ */
    @PersistenceContext
    private EntityManager manager;
    /**
     * デプロイ
     * @return JAR
     */
    @Deployment
    public static Archive<?> deploy() {
        return ShrinkWrap
            .create(WebArchive.class)
            .addPackages(true, "com.github.aynu.yukar")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsManifestResource("persistence.xml", "META-INF/persistence.xml")
            .addAsLibraries(
                Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve()
                .withTransitivity().asFile());
    }
    /**
     * @see EntityManager#persist(Object)
     * @see EntityManager#createQuery(String, Class)
     */
    @Test
    public final void test() {
        assertThat(manager, is(not(nullValue())));
        final Testee entity = new Testee();
        entity.setCode("code#01");
        entity.setName("name#01");
        manager.persist(entity);
        final Collection<Testee> entities = manager.createQuery("select t from Testee t",
            Testee.class).getResultList();
        assertThat(entities.size(), is(1));
        final Testee first = entities.iterator().next();
        assertThat(first.getCode(), is("code#01"));
        assertThat(first.getName(), is("name#01"));
    }
}
