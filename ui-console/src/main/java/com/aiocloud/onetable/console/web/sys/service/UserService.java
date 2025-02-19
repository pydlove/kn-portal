package com.aiocloud.onetable.console.web.sys.service;


import com.aiocloud.onetable.console.web.sys.dto.LoginDTO;
import com.aiocloud.onetable.console.web.sys.vo.UserInfoVO;
import org.springframework.security.core.userdetails.User;

import java.security.Permission;
import java.util.List;

/**
 *
 * @description: UserService.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-01-21 15:25 
 */
public interface UserService {

    List<Permission> getPermissionByUsername(String username);

    List<Permission> getPermissionByUserId(Integer userId);

    List<Permission> getPermissionByUser(User user);

    UserInfoVO doLogin(LoginDTO login);

    Long getCurrentUserId();

}
