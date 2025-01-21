package com.aiocloud.onetable.console.config.security;

import cn.hutool.json.JSONUtil;
import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.constant.SystemConstant;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @description: LoginSuccessHandler.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-01-20 15:54
 */
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenGenerator jwtTokenGenerator;
    private final JwtTokenProperties jwtTokenProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json;charset=UTF-8");

        // Generate a token and place it in the request header
        String token = jwtTokenGenerator.generateToken(authentication.getName(), jwtTokenProperties.getIssuer(), jwtTokenProperties.getAudience());
        httpServletResponse.setHeader(SystemConstant.X_AUTH_TOKEN, token);

        CommonResponse<String> commonResponse = new CommonResponse<>(null);

        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write(JSONUtil.toJsonStr(commonResponse).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
