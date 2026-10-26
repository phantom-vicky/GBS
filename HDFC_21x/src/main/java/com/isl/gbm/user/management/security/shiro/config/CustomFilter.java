package com.isl.gbm.user.management.security.shiro.config;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The Class CustomFilter.
 */
//@Component
public class CustomFilter extends FormAuthenticationFilter {

    /**
     * The Constant message.
     */
    private static final String MESSAGE = "Access denied.";

    /* (non-Javadoc)
     * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#onAccessDenied(jakarta.servlet.ServletRequest, jakarta.servlet.ServletResponse)
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse;
        try {
            httpResponse = WebUtils.toHttp(response);
        } catch (ClassCastException ex) {
            // Not a HTTP Servlet operation
            return super.onAccessDenied(request, response);
        }

        httpResponse.sendError(401, MESSAGE);
        return false; // No further processing.
    }
}