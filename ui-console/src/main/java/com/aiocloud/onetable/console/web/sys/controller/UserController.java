
package com.aiocloud.onetable.console.web.sys.controller;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.base.common.PageRequest;
import com.aiocloud.onetable.console.base.common.PaginationResult;
import com.aiocloud.onetable.console.web.sys.dto.UserAddDTO;
import com.aiocloud.onetable.console.web.sys.dto.UserDTO;
import com.aiocloud.onetable.console.web.sys.dto.UserUpdateDTO;
import com.aiocloud.onetable.console.web.sys.service.UserService;
import com.aiocloud.onetable.console.web.sys.vo.MessageVO;
import com.aiocloud.onetable.console.web.sys.vo.UserPageVO;
import com.aiocloud.onetable.mysql.sys.po.SysUserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @description: UserController.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-22 21:06 
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public CommonResponse<Integer> addUser(@RequestBody UserAddDTO userAddDTO) {
        return new CommonResponse<>(userService.insertUser(userAddDTO));
    }

    @PostMapping("/delete")
    public CommonResponse<Integer> deleteUser(@RequestParam("id") Long id) {
        return new CommonResponse<>(userService.deleteUserById(id));
    }

    @PostMapping("/update")
    public CommonResponse<Integer> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        return new CommonResponse<>(userService.updateUser(userUpdateDTO));
    }

    @GetMapping("/page")
    public CommonResponse<PaginationResult<UserPageVO>> getUserPage(UserDTO userDTO, PageRequest pageRequest) {
        return new CommonResponse<>(userService.getUserPage(userDTO, pageRequest));
    }
}