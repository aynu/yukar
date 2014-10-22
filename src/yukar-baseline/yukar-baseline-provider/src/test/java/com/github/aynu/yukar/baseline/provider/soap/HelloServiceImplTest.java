// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.soap;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.github.aynu.yukar.testing.AbstractSoapClientTest;
/**
 * @see HelloService
 * @see HelloServiceImpl
 * @author nilcy
 */
@RunWith(Arquillian.class)
@RunAsClient
public class HelloServiceImplTest extends AbstractSoapClientTest {
    /** 挨拶サービスI/F */
    private HelloService testee;
    /** コンストラクタ */
    public HelloServiceImplTest() {
        super("http://soap.provider.template.kuzumeji.com/", "HelloService");
    }
    /**
     * デプロイ
     * @return WAR
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
    /** テスト前処理 */
    @Before
    public void before() {
        testee = getPort(HelloService.class);
    }
    /** @see HelloService#sayHello(String) */
    @Test
    public void test() {
        assertThat(testee.sayHello("nilcy"), is("こんにちは nilcy さん。"));
    }
}
