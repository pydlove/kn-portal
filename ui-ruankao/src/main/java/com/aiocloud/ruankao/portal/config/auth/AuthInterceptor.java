package com.aiocloud.ruankao.portal.config.auth;

import cn.hutool.core.util.StrUtil;
import com.aiocloud.ruankao.portal.web.ruankao.service.TokenService;
import com.aiocloud.ruankao.portal.web.ruankao.service.impl.TokenServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @description: AuthInterceptor.java 
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-09-27 20:56
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取header中的token
        String token = request.getHeader("token");

        if (StrUtil.isEmpty(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("缺少认证token");
            log.error("missing authentication token, url: {}", request.getRequestURL());
            return false;
        }

        // 验证token有效性
        TokenServiceImpl.TokenInfo tokenInfo = tokenService.validateToken(token);
        if (tokenInfo == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("无效或过期的token");
            log.error("invalid or expired token, url: {}", request.getRequestURL());
            return false;
        }

        // 将用户信息存储到请求中，供后续使用
        request.setAttribute("userId", tokenInfo.getUserId());
        request.setAttribute("username", tokenInfo.getUsername());
        request.setAttribute("token", token);

        return true;
    }
}

