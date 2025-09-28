package com.aiocloud.ruankao.portal.web.ruankao.service;

import com.aiocloud.ruankao.portal.web.ruankao.vo.RkArticleMenuVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkArticleVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkSearchVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface RkArticleService {
    List<RkArticleMenuVO> getTitleList(Long parentMenuId);

    RkArticleVO getArticle(Long articleId);

    Page<RkArticleVO> getArticlePage(String title, Long menuId, Integer pageNum, Integer pageSize);

    RkArticleVO create(RkArticleVO articleVO);

    RkArticleVO update(Long id, RkArticleVO articleVO);

    Long delete(Long id);

    Page<RkSearchVO> searchArticlePage(String content, Integer pageNum, Integer pageSize);
}
