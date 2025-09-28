package com.aiocloud.ruankao.portal.web.ruankao.service;

import com.aiocloud.ruankao.portal.web.ruankao.vo.SendCodeVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.UserLoginResponseVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.UserLoginVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.UserRegisterVO;

/**
 *
 * @description: UserService.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-27 11:40
 */
public interface UserService {

    /**
     * 用户注册
     * @param registerRequest 注册请求参数
     * @return 注册结果
     */
    boolean register(UserRegisterVO registerRequest);

    /**
     * 用户登录
     * @param loginRequest 登录请求参数
     * @return 登录结果
     */
    UserLoginResponseVO login(UserLoginVO loginRequest);

    /**
     * 发送验证码
     * @param sendCodeRequest 发送验证码请求
     */
    String getVerificationCode(SendCodeVO sendCodeRequest);

    String getAndCheckVerificationCode(SendCodeVO sendCodeVO);

}
