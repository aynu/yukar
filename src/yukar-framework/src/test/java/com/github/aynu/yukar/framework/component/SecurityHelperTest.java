// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.framework.component;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.junit.Test;
import com.github.aynu.yukar.framework.component.SecurityHelper;
/**
 * @see SecurityHelper
 * @author nilcy
 */
@SuppressWarnings({ "static-method", "boxing" })
public class SecurityHelperTest {
    /**
     * @throws NoSuchAlgorithmException 該当アルゴリズムなし例外
     * @see SecurityHelper#getSecureRandom()
     */
    @Test
    public void testSecureRandom() throws NoSuchAlgorithmException {
        final SecureRandom testee = SecurityHelper.getSecureRandom();
        assertThat(testee, is(not(nullValue())));
        for (int i = 0; i < 1000; ++i) {
            assertThat(testee.nextDouble(), is(closeTo(0, 1.0)));
        }
    }
}
