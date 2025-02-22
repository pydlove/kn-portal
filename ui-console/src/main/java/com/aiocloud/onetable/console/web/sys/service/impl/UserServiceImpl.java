package com.aiocloud.onetable.console.web.sys.service.impl;

import com.aiocloud.onetable.console.base.exception.BadRequestException;
import com.aiocloud.onetable.console.base.exception.ErrorCode;
import com.aiocloud.onetable.console.config.security.JwtTokenGenerator;
import com.aiocloud.onetable.console.config.security.JwtTokenProperties;
import com.aiocloud.onetable.console.web.sys.UserPwdTool;
import com.aiocloud.onetable.console.web.sys.dto.LoginDTO;
import com.aiocloud.onetable.console.web.sys.service.MenuService;
import com.aiocloud.onetable.console.web.sys.service.UserService;
import com.aiocloud.onetable.console.web.sys.vo.MenuVO;
import com.aiocloud.onetable.console.web.sys.vo.UserInfoVO;
import com.aiocloud.onetable.mysql.sys.mapper.SysUserMapper;
import com.aiocloud.onetable.mysql.sys.po.SysUserPO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Permission;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @description: UserServiceImpl.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-01-21 15:24 
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUserPO> implements UserService {

    private static final Map<String, Long> USER_ID_MAP = new ConcurrentHashMap<>();

    private final MenuService menuService;
    private final SysUserMapper sysUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final JwtTokenProperties jwtTokenProperties;
    @Override
    public List<Permission> getPermissionByUsername(String username) {
        return null;
    }

    @Override
    public List<Permission> getPermissionByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<Permission> getPermissionByUser(User user) {
        return null;
    }

    @Override
    public UserInfoVO doLogin(LoginDTO login) {

        String username = login.getUsername();
        SysUserPO sysUser = Optional.ofNullable(sysUserMapper.selectByUsername(username)).orElse(new SysUserPO());
        String desPassword = UserPwdTool.doPasswordDeAesCBC(login.getRandomId(), login.getUserPwd());
        boolean matches = passwordEncoder.matches(desPassword, sysUser.getUserPassword());
        if (matches) {

            String token = jwtTokenGenerator.generateToken(username, jwtTokenProperties.getIssuer(), jwtTokenProperties.getAudience());

            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setToken(token);
            userInfoVO.setUserId(sysUser.getId());
            userInfoVO.setUsername(sysUser.getUserName());

            List<MenuVO> menuVOList = menuService.selectAccessMenus(username);
            userInfoVO.setMenuList(menuVOList);

            return userInfoVO;
        } else {
            throw new BadRequestException(ErrorCode.USER_OR_PASSWORD_ERROR);
        }

    }

    @Override
    public Long getCurrentUserId() {

        // Get HttpServletRequest
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String username = jwtTokenGenerator.getUserName(request);

        boolean userIdExist = USER_ID_MAP.containsKey(username);
        if (userIdExist) {
            return USER_ID_MAP.get(username);
        }

        // Query user id by username
        SysUserPO sysUser = Optional.ofNullable(sysUserMapper.selectByUsername(username)).orElse(new SysUserPO());
        USER_ID_MAP.put(username, sysUser.getId());

        return sysUser.getId();
    }
}
