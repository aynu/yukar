// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.domain.auth;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import com.github.aynu.yukar.framework.persistence.PersistenceException;
import com.github.aynu.yukar.framework.persistence.SmartRepository;
/**
 * @see Certification
 * @author nilcy
 */
@RunWith(Arquillian.class)
@Transactional(value = TransactionMode.ROLLBACK)
@SuppressWarnings("javadoc")
public class CertificationTest {
    @Inject
    private SmartRepository<Certification, CertificationFilter> testee;
    @Inject
    private Logger log;
    @Deployment
    public static Archive<?> deploy() {
        return ShrinkWrap
            .create(WebArchive.class)
            .addPackages(true, "com.github.aynu.yukar")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsManifestResource("persistence.xml", "META-INF/persistence.xml")
            .addAsResource("config.properties")
            .addAsResource("error-messages.properties")
            .addAsLibraries(
                Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve()
                .withTransitivity().asFile());
    }
    @Before
    public void before() throws PersistenceException {
        testee.save(new Certification("user#01", DigestUtils.sha256Hex("password#01")));
        testee.flush();
    }
    @Test
    public final void test() throws PersistenceException {
        final Certification entity = testee.findOne(new CertificationFilter("user#01"));
        log.debug("entity : {}", entity);
    }
}
