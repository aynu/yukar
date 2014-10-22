// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.consumer.servlet;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.slf4j.Logger;
/**
 * 基本フィルター
 * @author nilcy
 */
@WebFilter(filterName = "SimpleFilter", urlPatterns = { "/" }, initParams = {})
public class SimpleFilter implements Filter {
    /** ロガー */
    @Inject
    private Logger log;
    /** コンストラクタ */
    public SimpleFilter() {
    }
    /** {@inheritDoc} */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        log.info("---- FILTER INITIALIZED. ----");
    }
    /** {@inheritDoc} */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
        final FilterChain chain) throws IOException, ServletException {
        // Ok to throws
        log.info("---- FILTERED. ----");
        chain.doFilter(request, response);
    }
    /** {@inheritDoc} */
    @Override
    public void destroy() {
        log.info("---- FILTER DESTROYED. ----");
    }
}
