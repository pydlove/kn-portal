package com.aiocloud.kn.portal.web.system.service;

import com.aiocloud.kn.portal.web.system.vo.IdNameVO;
import com.aiocloud.kn.portal.web.system.vo.KnMenuVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 *
 * @description: MenusService.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-07-25 16:30
 */
public interface MenusService {

    List<KnMenuVO> list();

    KnMenuVO getById(Long id);

    Long delete(Long id);

    KnMenuVO update(Long id, KnMenuVO menuVO);

    KnMenuVO create(KnMenuVO menuVO);

    Page<KnMenuVO> page(Integer pageNum, Integer pageSize, String menuName);

    List<KnMenuVO> getRootMenus();

    List<IdNameVO> getAllMenus();

    List<KnMenuVO> getMenusByLevel(Integer level);
}
