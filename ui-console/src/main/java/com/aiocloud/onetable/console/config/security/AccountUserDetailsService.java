package com.aiocloud.onetable.console.config.security;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.onetable.console.web.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Permission;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @description: AccountUserDetailsService.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-01-21 15:17 
 */
@RequiredArgsConstructor
@Service
public class AccountUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username), true);
//        if (user == null) {
//            throw new UsernameNotFoundException("用户名或密码错误");
//        }
//        return new AccountUser(user.getId(), user.getUsername(), user.getPassword(), getUserAuthority(user.getUsername()));

        return null;
    }

    /**
     * 获取用户权限信息（角色、菜单权限）
     *
     * @since 1.0.0
     *

     * @param: username
     * @return: java.util.List<org.springframework.security.core.GrantedAuthority>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-01-21 15:18 
     */
    public List<GrantedAuthority> getUserAuthority(String username) {

//        // 角色(比如ROLE_admin)，菜单操作权限(比如sys:user:list)
//        // 角色必须以ROLE_开头，security在判断角色时会自动截取ROLE_
//        List<Permission> permissions = userService.getPermissionByUsername(username);
//
//        // 比如ROLE_admin,ROLE_normal,sys:user:list,...
//        String authority = "";
//        if (CollUtil.isNotEmpty(permissions)) {
//            List<String> urls = permissions.stream().map(Permission::getUrl).collect(Collectors.toList());
//            authority = StrUtil.join(",", urls);
//        }
//        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);

        return null;
    }
}
