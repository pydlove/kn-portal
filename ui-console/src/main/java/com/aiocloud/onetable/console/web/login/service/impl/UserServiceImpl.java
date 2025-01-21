package com.aiocloud.onetable.console.web.login.service.impl;

import com.aiocloud.onetable.console.web.login.service.UserService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

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
public class UserServiceImpl implements UserService {

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
