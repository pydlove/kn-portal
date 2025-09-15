package com.aiocloud.kn.portal.web.system.controller;

import cn.hutool.core.lang.UUID;
import com.aiocloud.kn.portal.base.common.CommonResponse;
import com.aiocloud.kn.portal.config.auth.PermissionRequired;
import com.aiocloud.kn.portal.web.system.service.ArticleService;
import com.aiocloud.kn.portal.web.system.vo.KnArticleMenuVO;
import com.aiocloud.kn.portal.web.system.vo.KnArticleVO;
import com.aiocloud.kn.portal.web.system.vo.LoginVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @description: LoginController.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-15 10:20
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    /**
     * doLogin
     *
     * @since 1.0.0
     *
     * @param: username
     * @param: password
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<com.aiocloud.kn.portal.web.system.vo.LoginVO>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-15 10:24
     */
    @GetMapping("/do")
    public CommonResponse<LoginVO> doLogin(
            @RequestParam() String username,
            @RequestParam() String password
    ) {

        UUID uuid = UUID.fastUUID();

        // md5加密
        String originPassword = "py131022";
        String md5Password = DigestUtils.md5DigestAsHex(originPassword.getBytes());
        if ("admin".equals(username) && md5Password.equals(password)) {
            return new CommonResponse<>(new LoginVO(username, true, uuid.toString()));
        }

        return new CommonResponse<>(new LoginVO(username, false, null));
    }

}
