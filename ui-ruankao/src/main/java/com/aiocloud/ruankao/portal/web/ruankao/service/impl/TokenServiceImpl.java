package com.aiocloud.ruankao.portal.web.ruankao.service.impl;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkUser;
import com.aiocloud.ruankao.portal.web.ruankao.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @description: TokenServiceImpl.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-27 20:54
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    // 使用Redis或内存存储token信息
    private final Map<String, TokenInfo> tokenStore = new ConcurrentHashMap<>();

    @Override
    public void storeToken(String token, RkUser user) {
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserId(user.getId());
        tokenInfo.setUsername(user.getUsername());
        tokenInfo.setCreateTime(System.currentTimeMillis());
        // 设置过期时间，例如2小时
        tokenInfo.setExpireTime(System.currentTimeMillis() + 2 * 60 * 60 * 1000);

        tokenStore.put(token, tokenInfo);
    }

    public TokenInfo validateToken(String token) {
        TokenInfo tokenInfo = tokenStore.get(token);
        if (tokenInfo == null) {
            return null; // token不存在
        }

        if (System.currentTimeMillis() > tokenInfo.getExpireTime()) {
            tokenStore.remove(token); // 清除过期token
            return null; // token已过期
        }

        return tokenInfo;
    }

    @Override
    public Boolean removeToken() {
        String token = null;
        String username = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            token = (String) request.getAttribute("token");
            username = (String) request.getAttribute("username");
            tokenStore.remove(token);
            log.info("User logs out and removes the token, token: {}, username: {}", token, username);
        } catch (Exception ex) {
            log.error("Invalid or expired token user logs out and removes the token error, token: {}, username: {}, caused by:",  token, username, ex);
        }

        return null;
    }

    @Data
    public static class TokenInfo {
        private Long userId;
        private String username;
        private Long createTime;
        private Long expireTime;
    }
}
