package com.isl.gbm.user.management.security.shiro.config;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Component
public class ShiroCsrfFilter extends AccessControlFilter {

    private static final Logger logger = LoggerFactory.getLogger(ShiroCsrfFilter.class);

    @Autowired
    @Qualifier("customCsrfTokenRepository")
    private CsrfTokenRepository csrfTokenRepository;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return true; // Let requests pass; validation happens in onAccessDenied
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        logger.debug("CSRF Filter processing request: {} {}", httpRequest.getMethod(), httpRequest.getRequestURI());

        // Generate or retrieve CSRF token
        CsrfToken csrfToken = csrfTokenRepository.loadToken(httpRequest);
        if (csrfToken == null) {
            // Generate new token
            csrfToken = csrfTokenRepository.generateToken(httpRequest);
            csrfTokenRepository.saveToken(csrfToken, httpRequest, httpResponse);
            logger.debug("Generated new CSRF token: {}", csrfToken.getToken());
        } else {
            logger.debug("Found existing CSRF token: {}", csrfToken.getToken());
        }

        // Always expose token in response headers
        httpResponse.setHeader("X-CSRF-TOKEN", csrfToken.getToken());
        httpResponse.setHeader("x-csrf-token", csrfToken.getToken());

        // For POST, PUT, DELETE, PATCH requests, validate CSRF token
        if (isWriteMethod(httpRequest.getMethod())) {
            String requestToken = httpRequest.getHeader("X-CSRF-TOKEN");
            logger.debug("Request CSRF Token from header: {}", requestToken);

            if (requestToken == null || !requestToken.equals(csrfToken.getToken())) {
                logger.warn("CSRF Token validation failed. Expected: {}, Received: {}", csrfToken.getToken(), requestToken);
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF Token");
                return false;
            }
            logger.debug("CSRF Token validation successful");
        }

        return true;
    }

    private boolean isWriteMethod(String method) {
        return ("POST".equalsIgnoreCase(method) ||
                "PUT".equalsIgnoreCase(method) ||
                "DELETE".equalsIgnoreCase(method) ||
                "PATCH".equalsIgnoreCase(method));
    }
}
