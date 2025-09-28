package com.aiocloud.ruankao.portal.web.ruankao.service.impl;

import com.aiocloud.common.base.exception.BadRequestException;
import com.aiocloud.common.base.exception.BizException;
import com.aiocloud.common.base.exception.ErrorCode;
import com.aiocloud.common.utils.DruidPasswordEncryptorUtil;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkUser;
import com.aiocloud.kn.portal.dao.ruankao.mapper.RkUserMapper;
import com.aiocloud.ruankao.portal.web.ruankao.service.TokenService;
import com.aiocloud.ruankao.portal.web.ruankao.service.UserService;
import com.aiocloud.ruankao.portal.web.ruankao.service.VerificationCodeService;
import com.aiocloud.ruankao.portal.web.ruankao.vo.SendCodeVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.UserLoginResponseVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.UserLoginVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.UserRegisterVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @description: UserServiceImpl.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-27 11:41
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final RkUserMapper userMapper;
    private final VerificationCodeService verificationCodeService;
    private final TokenService tokenService;

    @Override
    public boolean register(UserRegisterVO registerRequest) {

        // 1. 验证验证码
        if (!verificationCodeService.validateCode(registerRequest.getVerificationKey(),
                registerRequest.getVerificationCode())) {
            throw new RuntimeException("验证码错误或已过期");
        }

        // 2. 检查用户名是否已存在
        // 这里需要在RkUserMapper中添加根据用户名查询的方法
        RkUser existingUser = userMapper.selectOne(new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<RkUser>()
                .eq("username", registerRequest.getUsername())
                .eq("deleted_status", 0));

        if (existingUser != null) {
            throw new RuntimeException("用户名已存在");
        }

        // 3. 验证密码一致性
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new RuntimeException("两次输入的密码不一致");
        }

        try {

            // 4. 创建用户
            RkUser user = new RkUser();
            user.setUsername(registerRequest.getUsername());
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setVersion(0);
            user.setDeletedStatus(0);
            user.setUserStatus(1);

            // 5. 密码加密处理
            String salt = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
            user.setSalt(salt);
            user.setUserPwd(DruidPasswordEncryptorUtil.encrypt(registerRequest.getPassword() + salt));

            userMapper.insert(user);

            return true;
        } catch (Exception ex) {
            log.error("user register error, params: {}, caused by:", registerRequest, ex);
            throw new BadRequestException(ErrorCode.USER_OR_PASSWORD_ERROR);
        }
    }

    @Override
    public UserLoginResponseVO login(UserLoginVO loginRequest) {

        UserLoginResponseVO response = new UserLoginResponseVO();

        try {
            // 2. 查询用户
            RkUser user = userMapper.selectOne(new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<RkUser>()
                    .eq("username", loginRequest.getUsername())
                    .eq("deleted_status", 0));

            if (user == null) {
                response.setSuccess(false);
                response.setMessage("用户名或密码错误");
                return response;
            }

            // 3. 验证密码
            String encodedPassword = DruidPasswordEncryptorUtil.encrypt(loginRequest.getPassword() + user.getSalt());
            if (!encodedPassword.equals(user.getUserPwd())) {
                response.setSuccess(false);
                response.setMessage("用户名或密码错误");
                return response;
            }

            // 4. 更新最后登录时间
            user.setLastLoginTime(new Date());
            userMapper.updateById(user);

            // 5. 生成token（这里简化处理，实际项目中可以使用JWT）
            String token = UUID.randomUUID().toString().replace("-", "");

            response.setSuccess(true);
            response.setToken(token);
            response.setMessage("登录成功");
            response.setUser(user);

            tokenService.storeToken(token, user);

            return response;
        } catch (Exception ex) {
            log.error("login error, params: {}, cause by:", loginRequest, ex);
            throw new BadRequestException(ErrorCode.USER_OR_PASSWORD_ERROR);
        }
    }

    @Override
    public String getVerificationCode(SendCodeVO sendCodeRequest) {

        // 生成验证码
        String code = verificationCodeService.generateCode(sendCodeRequest.getKey());

        log.info("send verification code：{}", code);

        return code;
    }

    @Override
    public String getAndCheckVerificationCode(SendCodeVO sendCodeVO) {
        return verificationCodeService.getVerificationCode(sendCodeVO);
    }
}
