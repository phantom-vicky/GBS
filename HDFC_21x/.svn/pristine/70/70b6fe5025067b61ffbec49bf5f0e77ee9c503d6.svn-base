package com.isl.gbm.security.config;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HeaderValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String host = request.getHeader("Host");

        if (!isValidHost(host)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Malformed Host");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean isValidHost(String host) {
            return "10.51.203.106:2222".equalsIgnoreCase(host);
//        return "localhost:9908".equalsIgnoreCase(host);
    }
}

