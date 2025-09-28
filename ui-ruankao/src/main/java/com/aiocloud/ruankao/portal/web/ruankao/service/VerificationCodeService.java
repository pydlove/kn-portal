package com.aiocloud.ruankao.portal.web.ruankao.service;

import com.aiocloud.ruankao.portal.web.ruankao.vo.SendCodeVO;

/**
 *
 * @description: VerificationCodeService.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-27 11:40
 */
public interface VerificationCodeService {

    /**
     * 生成6位验证码
     * @param key 缓存key，可以是手机号或邮箱
     * @return 生成的验证码
     */
    String generateCode(String key);

    /**
     * 验证验证码是否正确
     * @param key 缓存key
     * @param code 用户输入的验证码
     * @return 验证结果
     */
    boolean validateCode(String key, String code);

    /**
     * 删除验证码
     * @param key 缓存key
     */
    void removeCode(String key);

    String getVerificationKey();

    String getVerificationCode(SendCodeVO sendCodeVO);

}
