package com.isl.gbm.security.config;

import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.user.management.constants.UserConstants;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Filter to handle correlation ID and user session ID for request tracing.
 *
 * This filter:
 * - Generates a unique correlation ID for each request
 * - Extracts user session ID from Shiro security context
 * - Sets both IDs in MDC for logging purposes
 * - Ensures proper cleanup of MDC after request processing
 */
@Component
public class CorrelationIdFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String correlationId = UUID.randomUUID().toString();

            // Extract SOL ID (branch code) from Shiro session
            String branchCode = "N/A";
            try {
                org.apache.shiro.subject.Subject subject = org.apache.shiro.SecurityUtils.getSubject();
                if (subject != null && subject.getSession() != null) {
                    Object solId = subject.getSession().getAttribute(UserConstants.SOL_ID);
                    if (solId != null) {
                        branchCode = solId.toString();
                    }
                }
            } catch (Exception ignored) {
                // Ignore Shiro exceptions for unauthenticated requests
            }

            MDC.put("correlationId", correlationId);
            MDC.put("branchCode", branchCode);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // Log the error but don't let it break the request
            System.err.println("Error in CorrelationIdFilter: " + e.getMessage());
            // Continue with the filter chain even if there's an error
            filterChain.doFilter(request, response);
        } finally {
            // Always clean up MDC
            try {
                MDC.remove("correlationId");
                MDC.remove("branchCode");
            } catch (Exception ignored) {
                // Ignore cleanup errors
            }
        }
    }
}
