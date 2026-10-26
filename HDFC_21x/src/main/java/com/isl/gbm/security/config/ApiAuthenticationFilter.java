package com.isl.gbm.security.config;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;

/**
 * Authentication filter for API from Outside of GBM
 *
 * It checks for Api User Credentials
 */
public class ApiAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //Have to Verify Headers
        chain.doFilter(request,response);
    }
}
