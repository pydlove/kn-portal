package com.aiocloud.onetable.console.web.login.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aiocloud.onetable.console.base.exception.BadRequestException;
import com.aiocloud.onetable.console.base.exception.BizException;
import com.aiocloud.onetable.console.base.exception.ErrorCode;
import com.aiocloud.onetable.console.web.login.UserPwdTool;
import com.aiocloud.onetable.console.web.login.dto.LoginDTO;
import com.aiocloud.onetable.console.web.login.service.UserService;
import com.aiocloud.onetable.console.web.login.vo.UserInfoVO;
import com.aiocloud.onetable.console.web.test.service.TestService;
import com.aiocloud.onetable.mysql.sys.mapper.SysUserMapper;
import com.aiocloud.onetable.mysql.sys.po.SysUserPO;
import com.aiocloud.onetable.mysql.test.mapper.TestMapper;
import com.aiocloud.onetable.mysql.test.po.TestInfoPO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Permission;
import java.util.List;
import java.util.Optional;

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


    private final SysUserMapper sysUserMapper;
    private final PasswordEncoder passwordEncoder;
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

        SysUserPO sysUser = Optional.ofNullable(sysUserMapper.selectByUsername(login.getUsername())).orElse(new SysUserPO());
        String desPassword = UserPwdTool.doPasswordDeAesCBC(login.getRandomId(), login.getUserPwd());
        boolean matches = passwordEncoder.matches(desPassword, sysUser.getUserPassword());
        if (matches) {
            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setToken(sysUser.getId().toString());
            userInfoVO.setUserId(sysUser.getId());
            userInfoVO.setUsername(sysUser.getUserName());

            return userInfoVO;
        } else {
            throw new BadRequestException(ErrorCode.USER_OR_PASSWORD_ERROR);
        }

    }

    //    @Resource
//    private UserMapper mapper;
//    @Autowired
//    private UserRoleService userRoleService;
//    @Autowired
//    private RolePermissionService rolePermissionService;
//    @Autowired
//    private PermissionService permissionService;
//
//    public List<Permission> getPermissionByUsername(String username) {
//        User user = super.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username), true);
//        return this.getPermissionByUser(user);
//    }
//
//    public List<Permission> getPermissionByUserId(Integer userId) {
//        User user = super.getById(userId);
//        return this.getPermissionByUser(user);
//    }
//
//    public List<Permission> getPermissionByUser(User user) {
//        List<Permission> permissions = new ArrayList<>();
//        if (null != user) {
//            List<UserRole> userRoles = userRoleService.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, user.getId()));
//            if (CollectionUtils.isNotEmpty(userRoles)) {
//                List<Integer> roleIds = new ArrayList<>();
//                userRoles.stream().forEach(userRole -> {
//                    roleIds.add(userRole.getRoleId());
//                });
//                List<RolePermission> rolePermissions = rolePermissionService.list(Wrappers.<RolePermission>lambdaQuery().in(RolePermission::getRoleId, roleIds));
//                if (CollectionUtils.isNotEmpty(rolePermissions)) {
//                    List<Integer> permissionIds = new ArrayList<>();
//                    rolePermissions.stream().forEach(rolePermission -> {
//                        permissionIds.add(rolePermission.getPermissionId());
//                    });
//                    permissions = permissionService.list(Wrappers.<Permission>lambdaQuery().in(Permission::getId, permissionIds));
//                }
//            }
//        }
//        return permissions;
//    }
}
