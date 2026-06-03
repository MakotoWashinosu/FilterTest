package com.example.filtertest.filter;

import com.example.filtertest.config.SysCheckProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

//ここでComponentをコメントアウトすると、PathForwardFilter1はSpringのコンテキストに登録されなくなります。
@Component
public class PathForwardFilter1 extends OncePerRequestFilter {

    private final SysCheckProperties properties;

    public PathForwardFilter1(SysCheckProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        String forwardPath = properties.getForwardPath();
        List<String> configuredPaths = properties.getPaths();

        System.out.println("[PathForwardFilter1] entered doFilterInternal: requestUri=" + requestUri);

        if (configuredPaths != null && forwardPath != null
                && configuredPaths.contains(requestUri)
                && !requestUri.equals(forwardPath)) {
            System.out.println("[PathForwardFilter1] forward before: forwarding " + requestUri + " to " + forwardPath);
            request.getRequestDispatcher(forwardPath).forward(request, response);
            System.out.println("[PathForwardFilter1] forward after: forwarded " + requestUri + " to " + forwardPath);
            return;
        }

        System.out.println("[PathForwardFilter1] doFilter before: continuing filter chain for " + requestUri);
        filterChain.doFilter(request, response);
        System.out.println("[PathForwardFilter1] doFilter after: returned from filter chain for " + requestUri);

    }
}
