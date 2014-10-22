// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.rest;
import java.net.URISyntaxException;
import java.util.Collection;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.aynu.yukar.testing.AbstractRestClientTest;
/**
 * @see VariableResource
 * @author nilcy
 */
@RunWith(Arquillian.class)
@RunAsClient
public class VariableResourceTest extends AbstractRestClientTest {
    /** ロガー */
    private static final Logger LOG = LoggerFactory.getLogger(VariableResourceTest.class);
    /** コンストラクタ */
    public VariableResourceTest() {
        super("resources/variables/");
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
    /** @see VariableResource#getVariables() */
    @SuppressWarnings("javadoc")
    @Test
    public void test() throws URISyntaxException {
        final Collection<Variable> variables = root().request(MediaType.APPLICATION_XML_TYPE).get(
            new GenericType<Collection<Variable>>() {
            });
        LOG.debug("variables={}", variables);
    }
}
