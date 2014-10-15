// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.testing;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 * @see Hello
 * @author nilcy
 */
@SuppressWarnings("static-method")
public class HelloTest {
    /** @see Hello#sayHello(String) */
    @Test
    public void testSayHello() {
        CoverageHelper.privateConstructor(Hello.class);
        assertThat(Hello.sayHello("nilcy"), is("こんにちは nilcy さん。"));
    }
}
