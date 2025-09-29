package com.aiocloud.kn.portal.config;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;


@lombok.extern.slf4j.Slf4j
@Component
public class IpLoggingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest httpRequest) {
            String clientIp = getClientIp(httpRequest);
            String requestURI = httpRequest.getRequestURI();
            String method = httpRequest.getMethod();

            log.info("Request from IP: {} | Method: {} | URI: {}", clientIp, method, requestURI);
        }

        chain.doFilter(request, response);
    }

    private String getClientIp(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty() && !"unknown".equalsIgnoreCase(xForwardedFor)) {
            return xForwardedFor.split(",")[0].trim();
        }

        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty() && !"unknown".equalsIgnoreCase(xRealIp)) {
            return xRealIp;
        }

        return request.getRemoteAddr();
    }
}
