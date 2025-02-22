
package com.aiocloud.onetable.console.web.sys.controller;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.base.common.PageRequest;
import com.aiocloud.onetable.console.base.common.PaginationResult;
import com.aiocloud.onetable.console.web.sys.dto.UserAddDTO;
import com.aiocloud.onetable.console.web.sys.dto.UserDTO;
import com.aiocloud.onetable.console.web.sys.dto.UserUpdateDTO;
import com.aiocloud.onetable.console.web.sys.service.RoleService;
import com.aiocloud.onetable.console.web.sys.service.UserService;
import com.aiocloud.onetable.console.web.sys.vo.RoleVO;
import com.aiocloud.onetable.console.web.sys.vo.UserPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @description: RoleController.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-22 21:06 
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/all")
    public CommonResponse<List<RoleVO>> getAllRole() {
        return new CommonResponse<>(roleService.getAllRole());
    }
}