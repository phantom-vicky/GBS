package com.isl.gbm.user.management.security.shiro.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.util.UUID;

/**
 * Stores CSRF tokens on the Apache Shiro session instead of the servlet HttpSession.
 * <p>
 * Shiro 2.2.x rotates the session id on {@code Subject.login()}. The servlet
 * {@link jakarta.servlet.http.HttpSession} wrapper can still point at the stopped
 * session, so {@link HttpSessionCsrfTokenRepository#saveToken} fails with
 * {@code UnknownSessionException}. Reading and writing the token through
 * {@link SecurityUtils#getSubject()} uses the current Shiro session.
 */
public class ShiroSessionCsrfTokenRepository implements CsrfTokenRepository {

    static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";
    static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";
    static final String CSRF_TOKEN_ATTR_NAME =
            HttpSessionCsrfTokenRepository.class.getName().concat(".CSRF_TOKEN");

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        return new DefaultCsrfToken(DEFAULT_CSRF_HEADER_NAME, DEFAULT_CSRF_PARAMETER_NAME,
                UUID.randomUUID().toString());
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        if (token == null) {
            Session session = SecurityUtils.getSubject().getSession(false);
            if (session != null) {
                session.removeAttribute(CSRF_TOKEN_ATTR_NAME);
            }
            return;
        }
        SecurityUtils.getSubject().getSession(true).setAttribute(CSRF_TOKEN_ATTR_NAME, token);
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        Session session = SecurityUtils.getSubject().getSession(false);
        if (session == null) {
            return null;
        }
        Object stored = session.getAttribute(CSRF_TOKEN_ATTR_NAME);
        return stored instanceof CsrfToken csrfToken ? csrfToken : null;
    }
    private String createNewToken() {
        return UUID.randomUUID().toString();
    }
}
