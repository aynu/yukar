// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.consumer.servlet;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.Logger;
/**
 * 基本コンテキストリスナー
 * @author nilcy
 */
@WebListener
public class SimpleContextListener implements ServletContextListener {
    /** ロガー */
    @Inject
    private Logger log;
    /** コンストラクタ */
    public SimpleContextListener() {
    }
    /** {@inheritDoc} */
    @Override
    public void contextInitialized(final ServletContextEvent event) {
        final ServletContext context = event.getServletContext();
        context.setAttribute("ServletContextListener", this.getClass().getCanonicalName());
        log.info("==== CONTEXT INITIALIZED. ====");
    }
    /** {@inheritDoc} */
    @Override
    public void contextDestroyed(final ServletContextEvent event) {
        log.info("==== CONTEXT DESTROYED. ====");
    }
}
