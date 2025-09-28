package com.aiocloud.ruankao.portal.web.ruankao.controller;

import com.aiocloud.common.base.common.CommonResponse;
import com.aiocloud.ruankao.portal.web.ruankao.service.RkArticleService;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkArticleMenuVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkArticleVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkSearchVO;
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
 * @description: ArticleController.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-11 20:15
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/rk_article")
public class RkArticleController {

    private final RkArticleService rkArticleService;

    /**
     * getTitleList
     *
     * @since 1.0.0
     *
     * @param: parentId
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<java.util.List < com.aiocloud.kn.portal.web.system.vo.RkArticleVO>>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-11 20:18
     */
    @GetMapping("/titleList")
    public CommonResponse<List<RkArticleMenuVO>> getTitleList(
            @RequestParam() Long parentMenuId
    ) {
        return new CommonResponse<>(rkArticleService.getTitleList(parentMenuId));
    }

    /**
     * getArticle
     *
     * @since 1.0.0
     *
     * @param: articleId
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<com.aiocloud.kn.portal.web.system.vo.RkArticleVO>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-12 15:05
     */
    @GetMapping("/get")
    public CommonResponse<RkArticleVO> getArticle(
            @RequestParam() Long articleId
    ) {
        return new CommonResponse<>(rkArticleService.getArticle(articleId));
    }


    /**
     * getArticlePage
     *
     * @since 1.0.0
     *
     * @param: title
     * @param: pageNum
     * @param: pageSize
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<com.aiocloud.kn.portal.web.system.vo.RkArticleVO>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-12 16:33 
     */
    @GetMapping("/page")
    public CommonResponse<Page<RkArticleVO>> getArticlePage(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long menuId,
            @RequestParam() Integer pageNum,
            @RequestParam() Integer pageSize
    ) {
        return new CommonResponse<>(rkArticleService.getArticlePage(title, menuId, pageNum, pageSize));
    }

    /**
     * create
     *
     * @since 1.0.0
     *
     * @param: articleVO
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<com.aiocloud.kn.portal.web.system.vo.RkArticleVO>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-12 16:39 
     */
    @PostMapping("/create")
    public CommonResponse<RkArticleVO> create(@RequestBody RkArticleVO articleVO) {
        return new CommonResponse<>(rkArticleService.create(articleVO));
    }

    /**
     * update
     *
     * @since 1.0.0
     *
     * @param: id
     * @param: articleVO
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<com.aiocloud.kn.portal.web.system.vo.KnMenuVO>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-12 16:39 
     */
    @PutMapping("/update/{id}")
    public CommonResponse<RkArticleVO> update(@PathVariable Long id, @RequestBody RkArticleVO articleVO) {
        return new CommonResponse<>(rkArticleService.update(id, articleVO));
    }

    /**
     * delete
     *
     * @since 1.0.0
     *
     * @param: id
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<java.lang.Long>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-12 16:38
     */
    @DeleteMapping("/delete/{id}")
    public CommonResponse<Long> delete(@PathVariable Long id) {
        return new CommonResponse<>(rkArticleService.delete(id));
    }

    @GetMapping("/search/page")
    public CommonResponse<Page<RkSearchVO>> searchArticlePage(
            @RequestParam() String content,
            @RequestParam() Integer pageNum,
            @RequestParam() Integer pageSize
    ) {
        return new CommonResponse<>(rkArticleService.searchArticlePage(content, pageNum, pageSize));
    }
}
