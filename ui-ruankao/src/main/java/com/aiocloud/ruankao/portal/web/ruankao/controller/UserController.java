package com.aiocloud.ruankao.portal.web.ruankao.controller;

import com.aiocloud.common.base.common.CommonResponse;
import com.aiocloud.ruankao.portal.web.ruankao.service.TokenService;
import com.aiocloud.ruankao.portal.web.ruankao.service.UserService;
import com.aiocloud.ruankao.portal.web.ruankao.service.VerificationCodeService;
import com.aiocloud.ruankao.portal.web.ruankao.vo.SendCodeVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.UserLoginResponseVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.UserLoginVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.UserRegisterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @description: UserController.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-27 11:35
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/rk_user")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;
    private final VerificationCodeService verificationCodeService;

    @PostMapping("/register")
    public CommonResponse<Boolean> register(@RequestBody UserRegisterVO registerVO) {
        boolean result = userService.register(registerVO);
        return new CommonResponse<>(result);
    }

    @PostMapping("/login")
    public CommonResponse<UserLoginResponseVO> login(@RequestBody UserLoginVO loginRequest) {
        UserLoginResponseVO response = userService.login(loginRequest);
        return CommonResponse.success(response);
    }
    @GetMapping("/logout")
    public CommonResponse<Boolean> logout() {
        return CommonResponse.success(tokenService.removeToken());
    }

    @PostMapping("/get-code")
    public CommonResponse<String> getVerificationCode(@RequestBody SendCodeVO SendCodeVO) {
        return CommonResponse.success(userService.getVerificationCode(SendCodeVO));
    }

    @GetMapping("/get-key")
    public CommonResponse<String> getVerificationKey() {
        return CommonResponse.success(verificationCodeService.getVerificationKey());
    }
}
