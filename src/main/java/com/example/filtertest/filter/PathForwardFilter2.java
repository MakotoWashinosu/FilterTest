package com.example.filtertest.filter;

import com.example.filtertest.config.SysCheckProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// PathForwardFilter2はFilterRegistrationBeanで登録されるため、@Componentは必要ありません。
// PathForwardFilter2を有効/無効にするには、FilterRegistrationConfigの@Beanをコメントアウトしてください。
public class PathForwardFilter2 extends OncePerRequestFilter {

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

    public PathForwardFilter2(SysCheckProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        String forwardPath = properties.getForwardPath();

        System.out.println("[PathForwardFilter2] entered doFilterInternal: requestUri=" + requestUri);

        System.out.println("[PathForwardFilter2] forward before: would forward " + requestUri + " to " + forwardPath);
        request.getRequestDispatcher(forwardPath).forward(request, response);
        System.out.println("[PathForwardFilter2] forward after: would have forwarded " + requestUri + " to " + forwardPath);

    }
}
