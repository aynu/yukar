// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.framework.core;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import com.github.aynu.yukar.framework.core.StandardRuntimeException;
/**
 * @see StandardRuntimeException
 * @author nilcy
 */
@SuppressWarnings({ "static-method", "javadoc" })
public class StandardRuntimeExceptionTest {
    @Test
    public final void test() {
        try {
            throw new StandardRuntimeException();
        } catch (final StandardRuntimeException e) {
            assertThat(e.getLocalizedMessage(), is(nullValue()));
        }
        try {
            throw new StandardRuntimeException("testee");
        } catch (final StandardRuntimeException e) {
            assertThat(e.getLocalizedMessage(), is("testee"));
        }
        try {
            throw new StandardRuntimeException(new UnsupportedOperationException());
        } catch (final StandardRuntimeException e) {
            assertThat(e.getLocalizedMessage(), is("java.lang.UnsupportedOperationException"));
        }
        try {
            throw new StandardRuntimeException("testee", new UnsupportedOperationException());
        } catch (final StandardRuntimeException e) {
            assertThat(e.getLocalizedMessage(), is("testee"));
        }
        try {
            throw new StandardRuntimeException("testee", new UnsupportedOperationException(), true,
                true);
        } catch (final StandardRuntimeException e) {
            assertThat(e.getLocalizedMessage(), is("testee"));
        }
    }
}
