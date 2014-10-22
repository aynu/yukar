// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.domain.auth;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @see CertificationDomain
 * @author nilcy
 */
@SuppressWarnings({ "static-method", "javadoc" })
public class CertificationDomainTest {
    private static final Logger LOG = LoggerFactory.getLogger(CertificationDomainTest.class);
    @Test
    public final void test() {
        final Certification user1 = new Certification("user#1", DigestUtils.sha256Hex("password#1"));
        LOG.debug("user1 : {}", user1);
        final CertificationDomain testee = new CertificationDomain(user1);
        try {
            testee.changePassword(null);
            fail();
        } catch (final NullPointerException e) {
            LOG.debug(e.toString());
        }
        try {
            testee.changePassword(StringUtils.EMPTY);
            fail();
        } catch (final IllegalArgumentException e) {
            LOG.debug(e.toString());
        }
        try {
            testee.changePassword("password#1");
            fail();
        } catch (final IllegalArgumentException e) {
            LOG.debug(e.toString());
        }
        final Certification user2 = testee.changePassword("password#2");
        assertThat(user2, is(not(user1)));
        LOG.debug("user2 : {}", user1);
    }
    @Test
    public final void sha256Hex() {
        assertThat(DigestUtils.sha256Hex("password"),
            is("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8"));
    }
}
