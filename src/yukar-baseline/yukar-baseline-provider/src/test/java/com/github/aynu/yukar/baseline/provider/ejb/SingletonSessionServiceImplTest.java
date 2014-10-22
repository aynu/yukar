// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.ejb;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import javax.ejb.EJB;
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
import com.github.aynu.yukar.baseline.registry.ejb.SingletonSessionService;
import com.github.aynu.yukar.baseline.registry.ejb.SingletonSessionServiceRemote;
/**
 * @see SingletonSessionService
 * @see SingletonSessionServiceRemote
 * @see SingletonSessionServiceImpl
 * @author nilcy
 */
@RunWith(Arquillian.class)
@Transactional(value = TransactionMode.ROLLBACK)
public class SingletonSessionServiceImplTest {
    /** @see SingletonSessionService */
    @EJB
    private SingletonSessionService testee;
    /** @see SingletonSessionServiceRemote */
    @EJB
    private SingletonSessionServiceRemote testeeRemote;
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
            .addAsLibraries(
                Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve()
                    .withTransitivity().asFile());
    }
    /** @see SingletonSessionService#sayHello(String) */
    @Test
    public final void test() {
        assertThat(testee, is(not(nullValue())));
        assertThat(testee.sayHello("nilcy"), is("こんにちは nilcy さん。"));
    }
    /** @see SingletonSessionServiceRemote#sayHello(String) */
    @Test
    public final void testRemote() {
        assertThat(testeeRemote, is(not(nullValue())));
        assertThat(testeeRemote.sayHello("nilcy"), is("こんにちは nilcy さん。"));
    }
}
