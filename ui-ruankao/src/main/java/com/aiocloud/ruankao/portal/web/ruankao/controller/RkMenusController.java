package com.aiocloud.ruankao.portal.web.ruankao.controller;

import com.aiocloud.common.base.common.CommonResponse;
import com.aiocloud.ruankao.portal.web.ruankao.service.RkMenusService;
import com.aiocloud.ruankao.portal.web.ruankao.vo.IdNameVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkMenuVO;
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
@RequestMapping("/rk_menus")
public class RkMenusController {

    private final RkMenusService rkMenusService;

    /**
     * list
     *
     * @since 1.0.0
     *
     * @return: com.aiocloud.ui.portal.base.common.CommonResponse<java.util.List < com.aiocloud.ui.portal.system.vo.RkMenuVO>>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-07-25 17:47 
     */
    @GetMapping("/list")
    public CommonResponse<List<RkMenuVO>> list() {
        return new CommonResponse<>(rkMenusService.list());
    }

    /**
     * list
     *
     * @since 1.0.0
     *
     * @return: com.aiocloud.ui.portal.base.common.CommonResponse<java.util.List < com.aiocloud.ui.portal.system.vo.RkMenuVO>>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-07-25 17:47
     */
    @GetMapping("/page")
    public CommonResponse<Page<RkMenuVO>> page(
            @RequestParam(required = false) String menuName,
            @RequestParam() Integer pageNum,
            @RequestParam() Integer pageSize
    ) {
        return new CommonResponse<>(rkMenusService.page(pageNum, pageSize, menuName));
    }

    /**
     * create menu
     *
     * @param menuVO menu information
     * @return created menu
     */
    @PostMapping("/create")
    public CommonResponse<RkMenuVO> create(@RequestBody RkMenuVO menuVO) {
        return new CommonResponse<>(rkMenusService.create(menuVO));
    }

    /**
     * update menu
     *
     * @param id menu id
     * @param menuVO menu information
     * @return updated menu
     */
    @PutMapping("/update/{id}")
    public CommonResponse<RkMenuVO> update(@PathVariable Long id, @RequestBody RkMenuVO menuVO) {
        return new CommonResponse<>(rkMenusService.update(id, menuVO));
    }

    /**
     * delete menu
     *
     * @param id menu id
     * @return success or failure
     */
    @DeleteMapping("/delete/{id}")
    public CommonResponse<Long> delete(@PathVariable Long id) {
        return new CommonResponse<>(rkMenusService.delete(id));
    }

    /**
     * get menu by id
     *
     * @param id menu id
     * @return menu information
     */
    @GetMapping("/detail/{id}")
    public CommonResponse<RkMenuVO> getById(@PathVariable Long id) {
        return new CommonResponse<>(rkMenusService.getById(id));
    }

    /**
     * getRootMenus
     *
     * @since 1.0.0
     *
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<java.util.List < com.aiocloud.kn.portal.web.system.vo.RkMenuVO>>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-11 19:33 
     */
    @GetMapping("/root")
    public CommonResponse<List<RkMenuVO>> getRootMenus() {
        return new CommonResponse<>(rkMenusService.getRootMenus());
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
        return new CommonResponse<>(rkMenusService.getAllMenus());
    }

    /**
     * getMenuByLevel
     *
     * @since 1.0.0
     *
     * @param: level
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<com.aiocloud.kn.portal.web.system.vo.RkMenuVO>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-14 21:08
     */
    @GetMapping("/level/{level}")
    public CommonResponse<List<RkMenuVO>> getMenusByLevel(@PathVariable Integer level) {
        return new CommonResponse<>(rkMenusService.getMenusByLevel(level));
    }


}
