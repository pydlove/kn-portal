package com.aiocloud.onetable.console.web.sys.service;

import com.aiocloud.onetable.console.web.sys.vo.MenuVO;

import java.util.List;

/**
 *
 * @description: MenuService.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-21 22:37 
 */
public interface MenuService {

    List<MenuVO> selectAccessMenus(String username);

}
