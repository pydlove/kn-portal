package com.aiocloud.kn.portal.web.system.controller;

import com.aiocloud.kn.portal.base.common.CommonResponse;
import com.aiocloud.kn.portal.config.auth.PermissionRequired;
import com.aiocloud.kn.portal.web.system.service.ArticleService;
import com.aiocloud.kn.portal.web.system.vo.KnArticleMenuVO;
import com.aiocloud.kn.portal.web.system.vo.KnArticleVO;
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
 * @description: ArticleController.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-11 20:15
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    /**
     * getTitleList
     *
     * @since 1.0.0
     *
     * @param: parentId
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<java.util.List < com.aiocloud.kn.portal.web.system.vo.KnArticleVO>>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-11 20:18
     */
    @GetMapping("/titleList")
    public CommonResponse<List<KnArticleMenuVO>> getTitleList(
            @RequestParam() Long parentMenuId
    ) {
        return new CommonResponse<>(articleService.getTitleList(parentMenuId));
    }

    /**
     * getArticle
     *
     * @since 1.0.0
     *
     * @param: articleId
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<com.aiocloud.kn.portal.web.system.vo.KnArticleVO>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-12 15:05
     */
    @GetMapping("/get")
    public CommonResponse<KnArticleVO> getArticle(
            @RequestParam() Long articleId
    ) {
        return new CommonResponse<>(articleService.getArticle(articleId));
    }


    /**
     * getArticlePage
     *
     * @since 1.0.0
     *
     * @param: title
     * @param: pageNum
     * @param: pageSize
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<com.aiocloud.kn.portal.web.system.vo.KnArticleVO>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-12 16:33 
     */
    @GetMapping("/page")
    public CommonResponse<Page<KnArticleVO>> getArticlePage(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long menuId,
            @RequestParam() Integer pageNum,
            @RequestParam() Integer pageSize
    ) {
        return new CommonResponse<>(articleService.getArticlePage(title, menuId, pageNum, pageSize));
    }

    /**
     * create
     *
     * @since 1.0.0
     *
     * @param: articleVO
     * @return: com.aiocloud.kn.portal.base.common.CommonResponse<com.aiocloud.kn.portal.web.system.vo.KnArticleVO>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-12 16:39 
     */
    @PostMapping("/create")
    public CommonResponse<KnArticleVO> create(@RequestBody KnArticleVO articleVO) {
        return new CommonResponse<>(articleService.create(articleVO));
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
    public CommonResponse<KnArticleVO> update(@PathVariable Long id, @RequestBody KnArticleVO articleVO) {
        return new CommonResponse<>(articleService.update(id, articleVO));
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
        return new CommonResponse<>(articleService.delete(id));
    }

}
