// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.rest;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
/**
 * RESTアプリケーション
 * @author nilcy
 */
@ApplicationPath("/resources")
public class RestApplication extends Application {
    /** コンストラクタ */
    public RestApplication() {
    }
    /** {@inheritDoc} */
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(VariableResource.class);
        classes.add(ChatResource.class);
        return classes;
    }
}
