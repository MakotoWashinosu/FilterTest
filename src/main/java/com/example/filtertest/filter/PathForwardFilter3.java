package com.example.filtertest.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.filtertest.config.SysCheckProperties;

import java.io.IOException;

//ここでComponentをコメントアウトすると、PathForwardFilter3はSpringのコンテキストに登録されなくなります。
@Component
public class PathForwardFilter3 extends OncePerRequestFilter {

/*
http://localhost:8080/external/syscheck
http://localhost:8080/rp-vcas/syscheck
http://localhost:8080/rp-auth/syscheck
http://localhost:8080/syscheck
http://localhost:8080/hello

cd C:\dev\FilterTest
mvn clean spring-boot:run
*/    
    private final SysCheckProperties properties;

    public PathForwardFilter3(SysCheckProperties properties) {
        this.properties = properties;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestUri = request.getRequestURI();

        System.out.println("[PathForwardFilter3] entered shouldNotFilter: requestUri=" + requestUri);

        if(properties.getForwardPath() == null) {
            return true; // If no forward path is configured, skip this filter entirely.
        }

        boolean isMatch = properties.getPaths() != null && properties.getPaths().contains(requestUri) && !requestUri.equals(properties.getForwardPath());
        System.out.println("[PathForwardFilter3] exited shouldNotFilter: return=" + !isMatch);
        return !isMatch;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        String forwardPath = properties.getForwardPath();

        System.out.println("[PathForwardFilter3] entered doFilterInternal: requestUri=" + requestUri);

        System.out.println("[PathForwardFilter3] forward before: would forward " + requestUri + " to " + forwardPath);
        request.getRequestDispatcher(forwardPath).forward(request, response);
        System.out.println("[PathForwardFilter3] forward after: would have forwarded " + requestUri + " to " + forwardPath);

        //System.out.println("[PathForwardFilter3] doFilter before: continuing filter chain for " + requestUri);
        //filterChain.doFilter(request, response);
        //System.out.println("[PathForwardFilter3] doFilter after: returned from filter chain for " + requestUri);
    }
}
