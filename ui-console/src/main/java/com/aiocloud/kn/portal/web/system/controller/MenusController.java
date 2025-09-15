package com.aiocloud.kn.portal.web.system.controller;

import com.aiocloud.kn.portal.base.common.CommonResponse;
import com.aiocloud.kn.portal.web.system.service.MenusService;
import com.aiocloud.kn.portal.web.system.vo.IdNameVO;
import com.aiocloud.kn.portal.web.system.vo.KnMenuVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
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
 * @description: MenusController.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-07-25 16:30
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/menus")
public class MenusController {

    private final MenusService menusService;

    /**
     * list
     *
     * @since 1.0.0
     *
     * @return: com.aiocloud.ui.portal.base.common.CommonResponse<java.util.List < com.aiocloud.ui.portal.system.vo.KnMenuVO>>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-07-25 17:47 
     */
    @GetMapping("/list")
    public CommonResponse<List<KnMenuVO>> list() {
        return new CommonResponse<>(menusService.list());
    }

    /**
     * list
     *
     * @since 1.0.0
     *
     * @return: com.aiocloud.ui.portal.base.common.CommonResponse<java.util.List < com.aiocloud.ui.portal.system.vo.KnMenuVO>>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-07-25 17:47
     */
    @GetMapping("/page")
    public CommonResponse<Page<KnMenuVO>> page(
            @RequestParam() Integer pageNum,
            @RequestParam() Integer pageSize
    ) {
        return new CommonResponse<>(menusService.page(pageNum, pageSize));
    }

    /**
     * create menu
     *
     * @param menuVO menu information
     * @return created menu
     */
    @PostMapping("/create")
    public CommonResponse<KnMenuVO> create(@RequestBody KnMenuVO menuVO) {
        return new CommonResponse<>(menusService.create(menuVO));
    }

    /**
     * update menu
     *
     * @param id menu id
     * @param menuVO menu information
     * @return updated menu
     */
    @PutMapping("/update/{id}")
    public CommonResponse<KnMenuVO> update(@PathVariable Long id, @RequestBody KnMenuVO menuVO) {
        return new CommonResponse<>(menusService.update(id, menuVO));
    }

    /**
     * delete menu
     *
     * @param id menu id
     * @return success or failure
     */
    @DeleteMapping("/delete/{id}")
    public CommonResponse<Long> delete(@PathVariable Long id) {
        return new CommonResponse<>(menusService.delete(id));
    }

    /**
     * get menu by id
     *
     * @param id menu id
     * @return menu information
     */
    @GetMapping("/detail/{id}")
    public CommonResponse<KnMenuVO> getById(@PathVariable Long id) {
        return new CommonResponse<>(menusService.getById(id));
    }

    /**
     * getRootMenus
     *
     * @since 1.0.0
     *
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<java.util.List < com.aiocloud.kn.portal.web.system.vo.KnMenuVO>>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-11 19:33 
     */
    @GetMapping("/root")
    public CommonResponse<List<KnMenuVO>> getRootMenus() {
        return new CommonResponse<>(menusService.getRootMenus());
    }


    /**
     * getAllMenus
     *
     * @since 1.0.0
     *
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<java.util.List < com.aiocloud.kn.portal.web.system.vo.IdNameVO>>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-14 10:32 
     */
    @GetMapping("/all")
    public CommonResponse<List<IdNameVO>> getAllMenus() {
        return new CommonResponse<>(menusService.getAllMenus());
    }

    /**
     * getMenuByLevel
     *
     * @since 1.0.0
     *
     * @param: level
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<com.aiocloud.kn.portal.web.system.vo.KnMenuVO>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-14 21:08
     */
    @GetMapping("/level/{level}")
    public CommonResponse<List<KnMenuVO>> getMenusByLevel(@PathVariable Integer level) {
        return new CommonResponse<>(menusService.getMenusByLevel(level));
    }


}
