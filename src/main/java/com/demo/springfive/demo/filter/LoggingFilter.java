package com.demo.springfive.demo.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * Created by xiwang on 10/3/17.
 * LoggingFilter
 */
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class LoggingFilter extends CommonsRequestLoggingFilter {

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        HttpServletResponseWrapper wrapper = new ContentCachingResponseWrapper(response);
        super.doFilterInternal(request, wrapper, filterChain);
    }

    @Override
    protected boolean isIncludePayload() {
        return true;
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return true;
    }
}
