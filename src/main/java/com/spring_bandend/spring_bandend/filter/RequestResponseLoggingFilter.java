package com.spring_bandend.spring_bandend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Slf4j
public class RequestResponseLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startAt = System.currentTimeMillis();
        try {
            filterChain.doFilter(request,response);
        } finally {
            long durationMs = System.currentTimeMillis() - startAt;
            String query = request.getQueryString() != null ? "?" + request.getQueryString() : "";
            log.info("➡️ {} {}{} | client={}", request.getMethod(), request.getRequestURI(), query, request.getRemoteAddr());
            log.info("⬅️ {} {} | {} ms", response.getStatus(), request.getRequestURI(), durationMs);
        }
    }
}
