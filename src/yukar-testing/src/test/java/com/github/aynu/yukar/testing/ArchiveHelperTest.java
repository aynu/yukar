// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.testing;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
/**
 * @see ArchiveHelper
 * @author nilcy
 */
@SuppressWarnings("static-method")
public class ArchiveHelperTest {
    /**
     * @see ArchiveHelper#trace(org.jboss.shrinkwrap.api.Archive)
     */
    @Test
    public void test() {
        String basePackage = "com.github.aynu.yukar";
        ArchiveHelper.trace(ShrinkWrap.create(JavaArchive.class).addPackages(true,
            basePackage));
        ArchiveHelper.trace(ShrinkWrap.create(WebArchive.class).addPackages(true,
            basePackage));
    }
}
