package com.aiocloud.kn.portal.web.system.service;

import com.aiocloud.kn.portal.web.system.vo.KnArticleMenuVO;
import com.aiocloud.kn.portal.web.system.vo.KnArticleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface ArticleService {
    List<KnArticleMenuVO> getTitleList(Long parentMenuId);

    KnArticleVO getArticle(Long articleId);

    Page<KnArticleVO> getArticlePage(String title, Long menuId, Integer pageNum, Integer pageSize);

    KnArticleVO create(KnArticleVO articleVO);

    KnArticleVO update(Long id, KnArticleVO articleVO);

    Long delete(Long id);
}
