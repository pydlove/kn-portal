package com.aiocloud.onetable.console.web.login.controller;

import cn.hutool.core.util.StrUtil;
import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.config.security.JwtTokenGenerator;
import com.aiocloud.onetable.console.config.security.JwtTokenProperties;
import com.aiocloud.onetable.console.constant.SystemConstant;
import com.aiocloud.onetable.console.web.login.UserPwdTool;
import com.aiocloud.onetable.console.web.login.dto.LoginDTO;
import com.aiocloud.onetable.console.web.login.service.UserService;
import com.aiocloud.onetable.console.web.login.vo.UserInfoVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @description: LoginController.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-16 15:47 
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final JwtTokenProperties jwtTokenProperties;

    /**
     * login
     *
     * @since 1.0.0
     *
     * @param: loginRequest
     * @return: org.springframework.http.ResponseEntity<?>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-02-15 17:22
     */
    @PostMapping("/do")
    public CommonResponse<UserInfoVO> login(@RequestBody LoginDTO login, HttpServletResponse response) {

        UserInfoVO userInfoVO = userService.doLogin(login);
        String token = userInfoVO.getToken();
        if(StrUtil.isNotEmpty(token)) {
            response.addHeader(SystemConstant.TOKEN, token);
        }

        return new CommonResponse<>(userInfoVO);
    }

    @GetMapping(value = "/random-code")
    public CommonResponse<Map<String, String>> randomCode(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();

        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            String key = secureRandom.nextInt() + String.valueOf(System.currentTimeMillis());
            String value = secureRandom.nextInt() + String.valueOf(System.currentTimeMillis());

            // base64 encoding ensures that keyBase64 is 16 bits and is used for encryption and decryption of aes128
            String keyBase64 = Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.UTF_8));
            String valueBase64 = Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));

            // The front end needs to take out the first 16 bits
            result.put(SystemConstant.PASS_RANDOM_ID, keyBase64);
            result.put(SystemConstant.PASS_RANDOM_CODE, valueBase64);
            UserPwdTool.put(keyBase64, valueBase64);
        } catch (Exception ex) {
            log.error("get random code error, caused by:", ex);
        }

        return new CommonResponse<>(result);
    }
}
