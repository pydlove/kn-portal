package com.aiocloud.ruankao.portal.web.ruankao.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.common.enums.DeletedStatusEnum;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkMenu;
import com.aiocloud.kn.portal.dao.ruankao.mapper.RkMenuMapper;
import com.aiocloud.ruankao.portal.web.ruankao.service.RkMenusService;
import com.aiocloud.ruankao.portal.web.ruankao.vo.IdNameVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkMenuVO;
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
public class RkMenusServiceImpl implements RkMenusService {

    private final RkMenuMapper rkMenuMapper;

    @Override
    public List<RkMenuVO> list() {

        List<RkMenu> RkMenuList = rkMenuMapper.selectAll();

        return getMenuList(RkMenuList);
    }

    @Override
    public RkMenuVO getById(Long id) {

        RkMenu RkMenu = rkMenuMapper.selectById(id);
        if (RkMenu == null) {
            return null;
        }

        return BeanUtil.copyProperties(RkMenu, RkMenuVO.class);
    }

    @Override
    public Long delete(Long id) {

        RkMenu RkMenu = rkMenuMapper.selectByPrimaryKey(id);
        RkMenu.setDeletedStatus(DeletedStatusEnum.DELETED.getCode());

        rkMenuMapper.updateByPrimaryKeySelective(RkMenu);

        return RkMenu.getId();
    }

    @Override
    public RkMenuVO update(Long id, RkMenuVO menuVO) {

        RkMenu RkMenu = rkMenuMapper.selectById(id);
        if (RkMenu == null) {
            return null;
        }

        RkMenu.setMenuName(menuVO.getMenuName());
        RkMenu.setMenuDesc(menuVO.getMenuDesc());
        RkMenu.setMenuUrl(menuVO.getMenuUrl());
        RkMenu.setMenuLevel(menuVO.getMenuLevel());
        RkMenu.setParentId(menuVO.getParentId());
        RkMenu.setOrderNo(menuVO.getOrderNo());
        RkMenu.setEnabledStatus(menuVO.getEnabledStatus());

        int result = rkMenuMapper.updateById(RkMenu);
        if (result > 0) {
            return BeanUtil.copyProperties(RkMenu, RkMenuVO.class);
        }

        return null;
    }

    @Override
    public RkMenuVO create(RkMenuVO menuVO) {

        RkMenu RkMenu = new RkMenu();
        RkMenu.setMenuName(menuVO.getMenuName());
        RkMenu.setMenuDesc(menuVO.getMenuDesc());
        RkMenu.setMenuUrl(menuVO.getMenuUrl());
        RkMenu.setMenuLevel(menuVO.getMenuLevel());
        RkMenu.setParentId(menuVO.getParentId());
        RkMenu.setOrderNo(menuVO.getOrderNo());
        RkMenu.setEnabledStatus(menuVO.getEnabledStatus());

        int result = rkMenuMapper.insert(RkMenu);
        if (result > 0) {
            return BeanUtil.copyProperties(RkMenu, RkMenuVO.class);
        }

        return null;
    }

    @Override
    public Page<RkMenuVO> page(Integer pageNum, Integer pageSize, String menuName) {

        Page<RkMenu> page = new Page<>(pageNum, pageSize);

        QueryWrapper<RkMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());
        queryWrapper.orderByAsc("order_no")
                .orderByDesc("create_time");

        if (StrUtil.isNotBlank(menuName)) {
            queryWrapper.like("menu_name", menuName);
        }

        Page<RkMenu> menuPage = rkMenuMapper.selectPage(page, queryWrapper);

        List<RkMenuVO> records = menuPage.getRecords().stream()
                .map(menu -> BeanUtil.copyProperties(menu, RkMenuVO.class))
                .collect(Collectors.toList());

        Page<RkMenuVO> voPage = new Page<>(pageNum, pageSize, menuPage.getTotal());
        voPage.setRecords(records);

        return voPage;
    }

    @Override
    public List<RkMenuVO> getRootMenus() {

        QueryWrapper<RkMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());
        queryWrapper.eq("menu_level", 1);
        queryWrapper.orderByAsc("order_no").orderByDesc("create_time");

        List<RkMenu> RkMenuList = rkMenuMapper.selectList(queryWrapper);

        return RkMenuList.stream()
                .map(menu -> BeanUtil.copyProperties(menu, RkMenuVO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<IdNameVO> getAllMenus() {

        QueryWrapper<RkMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());
        queryWrapper.orderByDesc("create_time");

        List<RkMenu> RkMenus = rkMenuMapper.selectList(queryWrapper);

        return RkMenus.stream()
                .map(menu -> new IdNameVO(menu.getId(), menu.getMenuName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RkMenuVO> getMenusByLevel(Integer level) {


        QueryWrapper<RkMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());
        queryWrapper.eq("menu_level", level);

        List<RkMenu> RkMenus = rkMenuMapper.selectList(queryWrapper);

        return RkMenus.stream()
                .map(menu -> BeanUtil.copyProperties(menu, RkMenuVO.class))
                .collect(Collectors.toList());
    }

    /**
     * getMenuList
     *
     * @since 1.0.0
     *
     * @param: RkMenuList
     * @return: java.util.List<com.aiocloud.kn.portal.web.system.vo.RkMenuVO>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-11 19:39 
     */
    private List<RkMenuVO> getMenuList(List<RkMenu> RkMenuList) {

        Map<Long, RkMenuVO> menuMap = RkMenuList.stream()
                .map(menu -> BeanUtil.copyProperties(menu, RkMenuVO.class))
                .collect(Collectors.toMap(RkMenuVO::getId, menu -> menu));

        List<RkMenuVO> rootMenus = new ArrayList<>();
        for (RkMenuVO menuVO : menuMap.values()) {

            Long parentId = menuVO.getParentId();

            if (parentId == null || parentId == 0) {
                rootMenus.add(menuVO);
            } else {
                RkMenuVO parentMenu = menuMap.get(parentId);
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
