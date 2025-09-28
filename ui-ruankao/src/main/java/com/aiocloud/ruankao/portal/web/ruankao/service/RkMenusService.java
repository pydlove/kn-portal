package com.aiocloud.ruankao.portal.web.ruankao.service;

import com.aiocloud.ruankao.portal.web.ruankao.vo.IdNameVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkMenuVO;
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
public interface RkMenusService {

    List<RkMenuVO> list();

    RkMenuVO getById(Long id);

    Long delete(Long id);

    RkMenuVO update(Long id, RkMenuVO menuVO);

    RkMenuVO create(RkMenuVO menuVO);

    Page<RkMenuVO> page(Integer pageNum, Integer pageSize, String menuName);

    List<RkMenuVO> getRootMenus();

    List<IdNameVO> getAllMenus();

    List<RkMenuVO> getMenusByLevel(Integer level);
}
