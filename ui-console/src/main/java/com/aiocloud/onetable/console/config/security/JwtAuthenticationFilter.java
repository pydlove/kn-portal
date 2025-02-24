package com.aiocloud.onetable.console.config.security;

import com.aiocloud.onetable.console.base.exception.BadRequestException;
import com.aiocloud.onetable.console.base.exception.ErrorCode;
import com.aiocloud.onetable.console.constant.SystemConstant;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @description: JwtAuthenticationFilter.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-01-21 15:03 
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${system.url.whitelist:/login/do}")
    private String urlWhitelist;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private final JwtTokenGenerator jwtTokenGenerator;
    private final AccountUserDetailsService accountUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        Claims claims;

        String[] urls = urlWhitelist.split(SystemConstant.SEPARATOR_COMMA);
        for (String url : urls) {
            if (Objects.equals(contextPath + url.trim(), request.getRequestURI())) {
                chain.doFilter(request, response);
                return;
            }
        }

        claims = getClaimsAndCheckToken(request, response);
        if (claims == null) {
            return;
        }

        executeRequest(request, response, chain, claims);
    }

    private void executeRequest(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Claims claims) throws IOException {

        try {

            String username = claims.getSubject();

            // 构建UsernamePasswordAuthenticationToken，这里密码为null，是因为提供了正确的token，实现自动登录
            // List<GrantedAuthority> authorities = accountUserDetailsService.getUserAuthority(username);

            List<GrantedAuthority> authorities = new ArrayList<>();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);

        } catch (BadRequestException ex) {
            handleBadRequestException(response, ex);
        } catch (Exception ex) {
            handleGeneralException(response, ex);
        }
    }

    private Claims getClaimsAndCheckToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {

            String token = request.getHeader(SystemConstant.TOKEN);

            // 未获取到token，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以可以放行
            // 没有token相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口
            // if (StrUtil.isBlankOrUndefined(token)) {
            //     chain.doFilter(request, response);
            //     return;
            // }

            Claims claims = jwtTokenGenerator.extractAllClaims(token);
            if (claims == null) {
                throw new BadRequestException(ErrorCode.TOKEN_EXCEPTION);
            }

            if (jwtTokenGenerator.isTokenExpired(claims.getExpiration())) {
                throw new BadRequestException(ErrorCode.TOKEN_HAS_EXPIRED);
            }

            return claims;

        } catch (BadRequestException ex) {
            tokenNotExistOrExpired(response, ex);
        } catch (Exception ex) {
            tokenNotExistOrExpired(response);
        }

        return null;
    }

    private void handleBadRequestException(HttpServletResponse response, BadRequestException e) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(String.format("{\"code\": 500, \"message\": \"%s\"}", e.getMessage()));
    }

    private void handleGeneralException(HttpServletResponse response, Exception e) throws IOException {
        log.error("General exception occurred", e);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\": 500, \"message\": \"服务器内部错误\"}");
    }

    private void tokenNotExistOrExpired(HttpServletResponse response, BadRequestException e) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(String.format("{\"code\": 401, \"message\": \"%s\"}", e.getMessage()));
    }

    private void tokenNotExistOrExpired(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\": 401, \"message\": \"服务器内部错误\"}");
    }

}
