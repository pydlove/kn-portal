package com.aiocloud.kn.portal.web.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.common.enums.DeletedStatusEnum;
import com.aiocloud.kn.portal.dao.system.domain.KnMenu;
import com.aiocloud.kn.portal.dao.system.mapper.KnMenuMapper;
import com.aiocloud.kn.portal.web.system.service.MenusService;
import com.aiocloud.kn.portal.web.system.vo.IdNameVO;
import com.aiocloud.kn.portal.web.system.vo.KnMenuVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @description: MenusServiceImpl.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-07-25 16:30
 */
@Service
@RequiredArgsConstructor
public class MenusServiceImpl implements MenusService {

    private final KnMenuMapper knMenuMapper;

    @Override
    public List<KnMenuVO> list() {

        List<KnMenu> knMenuList = knMenuMapper.selectAll();

        return getMenuList(knMenuList);
    }

    @Override
    public KnMenuVO getById(Long id) {

        KnMenu knMenu = knMenuMapper.selectById(id);
        if (knMenu == null) {
            return null;
        }

        return BeanUtil.copyProperties(knMenu, KnMenuVO.class);
    }

    @Override
    public Long delete(Long id) {

        KnMenu knMenu = knMenuMapper.selectByPrimaryKey(id);
        knMenu.setDeletedStatus(DeletedStatusEnum.DELETED.getCode());

        knMenuMapper.updateByPrimaryKeySelective(knMenu);

        return knMenu.getId();
    }

    @Override
    public KnMenuVO update(Long id, KnMenuVO menuVO) {

        KnMenu knMenu = knMenuMapper.selectById(id);
        if (knMenu == null) {
            return null;
        }

        knMenu.setMenuName(menuVO.getMenuName());
        knMenu.setMenuDesc(menuVO.getMenuDesc());
        knMenu.setMenuUrl(menuVO.getMenuUrl());
        knMenu.setMenuLevel(menuVO.getMenuLevel());
        knMenu.setParentId(menuVO.getParentId());
        knMenu.setOrderNo(menuVO.getOrderNo());
        knMenu.setEnabledStatus(menuVO.getEnabledStatus());

        int result = knMenuMapper.updateById(knMenu);
        if (result > 0) {
            return BeanUtil.copyProperties(knMenu, KnMenuVO.class);
        }

        return null;
    }

    @Override
    public KnMenuVO create(KnMenuVO menuVO) {

        KnMenu knMenu = new KnMenu();
        knMenu.setMenuName(menuVO.getMenuName());
        knMenu.setMenuDesc(menuVO.getMenuDesc());
        knMenu.setMenuUrl(menuVO.getMenuUrl());
        knMenu.setMenuLevel(menuVO.getMenuLevel());
        knMenu.setParentId(menuVO.getParentId());
        knMenu.setOrderNo(menuVO.getOrderNo());
        knMenu.setEnabledStatus(menuVO.getEnabledStatus());

        int result = knMenuMapper.insert(knMenu);
        if (result > 0) {
            return BeanUtil.copyProperties(knMenu, KnMenuVO.class);
        }

        return null;
    }

    @Override
    public Page<KnMenuVO> page(Integer pageNum, Integer pageSize, String menuName) {

        Page<KnMenu> page = new Page<>(pageNum, pageSize);

        QueryWrapper<KnMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());
        queryWrapper.orderByDesc("create_time");

        if (StrUtil.isNotBlank(menuName)) {
            queryWrapper.like("menu_name", menuName);
        }

        Page<KnMenu> menuPage = knMenuMapper.selectPage(page, queryWrapper);

        List<KnMenuVO> records = menuPage.getRecords().stream()
                .map(menu -> BeanUtil.copyProperties(menu, KnMenuVO.class))
                .collect(Collectors.toList());

        Page<KnMenuVO> voPage = new Page<>(pageNum, pageSize, menuPage.getTotal());
        voPage.setRecords(records);

        return voPage;
    }

    @Override
    public List<KnMenuVO> getRootMenus() {

        QueryWrapper<KnMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());
        queryWrapper.eq("menu_level", 1);
        queryWrapper.orderByAsc("order_no").orderByDesc("create_time");

        List<KnMenu> knMenuList = knMenuMapper.selectList(queryWrapper);

        return knMenuList.stream()
                .map(menu -> BeanUtil.copyProperties(menu, KnMenuVO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<IdNameVO> getAllMenus() {

        QueryWrapper<KnMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());
        queryWrapper.orderByDesc("create_time");

        List<KnMenu> knMenus = knMenuMapper.selectList(queryWrapper);

        return knMenus.stream()
                .map(menu -> new IdNameVO(menu.getId(), menu.getMenuName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<KnMenuVO> getMenusByLevel(Integer level) {


        QueryWrapper<KnMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());
        queryWrapper.eq("menu_level", level);

        List<KnMenu> knMenus = knMenuMapper.selectList(queryWrapper);

        return knMenus.stream()
                .map(menu -> BeanUtil.copyProperties(menu, KnMenuVO.class))
                .collect(Collectors.toList());
    }

    /**
     * getMenuList
     *
     * @since 1.0.0
     *
     * @param: knMenuList
     * @return: java.util.List<com.aiocloud.kn.portal.web.system.vo.KnMenuVO>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-11 19:39 
     */
    private List<KnMenuVO> getMenuList(List<KnMenu> knMenuList) {

        Map<Long, KnMenuVO> menuMap = knMenuList.stream()
                .map(menu -> BeanUtil.copyProperties(menu, KnMenuVO.class))
                .collect(Collectors.toMap(KnMenuVO::getId, menu -> menu));

        List<KnMenuVO> rootMenus = new ArrayList<>();
        for (KnMenuVO menuVO : menuMap.values()) {

            Long parentId = menuVO.getParentId();

            if (parentId == null || parentId == 0) {
                rootMenus.add(menuVO);
            } else {
                KnMenuVO parentMenu = menuMap.get(parentId);
                if (parentMenu != null) {
                    if (parentMenu.getChildrenMenu() == null) {
                        parentMenu.setChildrenMenu(new ArrayList<>());
                    }

                    parentMenu.getChildrenMenu().add(menuVO);
                }
            }
        }

        return rootMenus;
    }
}
