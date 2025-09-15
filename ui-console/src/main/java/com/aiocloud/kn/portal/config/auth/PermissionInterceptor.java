package com.aiocloud.kn.portal.config.auth;

import com.aiocloud.kn.portal.base.common.CommonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

import static com.aiocloud.kn.portal.base.exception.ErrorCode.UNAUTHORIZED;

/**
 *
 * @description: PermissionInterceptor.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-15 9:45
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionInterceptor implements HandlerInterceptor {

    private final PermissionProperties permissionProperties;
    private final ObjectMapper objectMapper;

    /**
     * preHandle
     *
     * @since 1.0.0
     *
     * @param: request
     * @param: response
     * @param: handler
     * @return: boolean
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-15 9:46
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是处理方法，直接放行
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        PermissionRequired permissionRequired = handlerMethod.getMethodAnnotation(PermissionRequired.class);

        // 如果方法上没有权限注解，直接放行
        if (permissionRequired == null) {
            return true;
        }

        // 如果权限验证未启用，直接放行
        if (!permissionProperties.isEnabled()) {
            log.debug("权限验证已关闭，放行请求: {}", request.getRequestURI());
            return true;
        }

        // 如果该方法不需要权限验证，直接放行
        if (!permissionRequired.required()) {
            return true;
        }

        // 获取请求头中的token
        String token = request.getHeader("Authorization");

        // 验证token
        if (token == null || !token.equals(permissionProperties.getToken())) {
            log.warn("权限验证失败，请求URI: {}, IP: {}", request.getRequestURI(), getClientIpAddress(request));
            sendErrorResponse(response, "权限验证失败，无效的token");
            return false;
        }

        log.debug("权限验证通过，请求URI: {}", request.getRequestURI());
        return true;
    }

    /**
     * 发送错误响应
     */
    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        CommonResponse<String> errorResponse = new CommonResponse<>(UNAUTHORIZED, message);
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

        PrintWriter writer = response.getWriter();
        writer.write(jsonResponse);
        writer.flush();
        writer.close();
    }

    /**
     * 获取客户端IP地址
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String xip = request.getHeader("X-Real-IP");
        String xfor = request.getHeader("X-Forwarded-For");

        if (xfor != null && !xfor.isEmpty() && !"unknown".equalsIgnoreCase(xfor)) {
            int index = xfor.indexOf(",");
            if (index != -1) {
                return xfor.substring(0, index);
            } else {
                return xfor;
            }
        }

        if (xip != null && !xip.isEmpty() && !"unknown".equalsIgnoreCase(xip)) {
            return xip;
        }

        return request.getRemoteAddr();
    }
}
