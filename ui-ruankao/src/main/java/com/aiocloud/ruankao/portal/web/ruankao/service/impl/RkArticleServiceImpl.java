package com.aiocloud.ruankao.portal.web.ruankao.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.common.enums.DeletedStatusEnum;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkArticle;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkMenu;
import com.aiocloud.kn.portal.dao.ruankao.mapper.RkArticleMapper;
import com.aiocloud.kn.portal.dao.ruankao.mapper.RkMenuMapper;
import com.aiocloud.ruankao.portal.web.ruankao.service.RkArticleService;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkArticleMenuVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkArticleTitleVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkArticleVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkMenuVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkSearchVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @description: ArticleServiceImpl.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-11 20:16
 */
@RequiredArgsConstructor
@Service
public class RkArticleServiceImpl implements RkArticleService {

    private final RkArticleMapper rkArticleMapper;
    private final RkMenuMapper rkMenuMapper;

    @Override
    public List<RkArticleMenuVO> getTitleList(Long parentMenuId) {

        List<RkMenu> RkMenus = rkMenuMapper.selectByParentId(parentMenuId);
        if (CollUtil.isEmpty(RkMenus)) {
            return null;
        }

        List<Long> menuIds = RkMenus.stream().map(RkMenu::getId).toList();
        List<RkArticle> RkArticles = rkArticleMapper.selectTitleByMenuIds(menuIds);

        Map<Long, List<RkArticleTitleVO>> menuMap = new HashMap<>();
        for (RkArticle RkArticle : RkArticles) {
            Long menuId = RkArticle.getMenuId();
            List<RkArticleTitleVO> articleTitleList = menuMap.computeIfAbsent(menuId, k -> new ArrayList<>());
            articleTitleList.add(new RkArticleTitleVO(RkArticle.getId(), RkArticle.getArticleTitle(), RkArticle.getMenuId()));
        }

        List<RkArticleMenuVO> result = new ArrayList<>();
        for (RkMenu RkMenu : RkMenus) {
            List<RkArticleTitleVO> articleTitleList = menuMap.getOrDefault(RkMenu.getId(), new ArrayList<>());
            RkArticleMenuVO RkArticleMenuVO = new RkArticleMenuVO(RkMenu.getId(), RkMenu.getMenuName(), articleTitleList);
            result.add(RkArticleMenuVO);
        }

        return result;
    }

    @Override
    public RkArticleVO getArticle(Long articleId) {

        RkArticle RkArticle = rkArticleMapper.selectByPrimaryKey(articleId);
        return BeanUtil.copyProperties(RkArticle, RkArticleVO.class);
    }

    @Override
    public Page<RkArticleVO> getArticlePage(String title, Long menuId, Integer pageNum, Integer pageSize) {

        Page<RkArticle> page = new Page<>(pageNum, pageSize);

        QueryWrapper<RkArticle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());

        if (StrUtil.isNotBlank(title)) {
            queryWrapper.like("article_title", title);
        }

        if (null != menuId) {
            queryWrapper.eq("menu_id", menuId);
        }

        queryWrapper.orderByDesc("create_time");

        Page<RkArticle> menuPage = rkArticleMapper.selectPage(page, queryWrapper);

        List<RkArticle> records = menuPage.getRecords();
        Map<Long, RkMenu> menuMap = new HashMap<>();
        if (CollUtil.isNotEmpty(records)) {
            Set<Long> menuIds = records.stream().map(RkArticle::getMenuId).collect(Collectors.toSet());
            List<RkMenu> RkMenus = rkMenuMapper.selectByIds(menuIds);
            menuMap = RkMenus.stream().collect(Collectors.toMap(RkMenu::getId, menu -> menu));
        }

        Map<Long, RkMenu> finalMenuMap = menuMap;
        List<RkArticleVO> result = records.stream()
                .map(menu -> {
                    RkArticleVO RkArticleVO = BeanUtil.copyProperties(menu, RkArticleVO.class);
                    RkMenu RkMenu = Optional.ofNullable(finalMenuMap.get(menu.getMenuId())).orElse(new RkMenuVO());
                    RkArticleVO.setMenuName(RkMenu.getMenuName());

                    return RkArticleVO;
                })
                .collect(Collectors.toList());

        Page<RkArticleVO> voPage = new Page<>(pageNum, pageSize, menuPage.getTotal());
        voPage.setRecords(result);

        return voPage;
    }

    @Override
    public RkArticleVO create(RkArticleVO articleVO) {

        RkArticle RkArticle = new RkArticle();
        RkArticle.setMenuId(articleVO.getMenuId());
        RkArticle.setArticleTitle(articleVO.getArticleTitle());
        RkArticle.setArticleContent(articleVO.getArticleContent());

        int result = rkArticleMapper.insert(RkArticle);
        if (result > 0) {
            return BeanUtil.copyProperties(RkArticle, RkArticleVO.class);
        }

        return null;
    }

    @Override
    public RkArticleVO update(Long id, RkArticleVO articleVO) {

        RkArticle RkArticle = rkArticleMapper.selectById(id);
        if (RkArticle == null) {
            return null;
        }

        RkArticle.setArticleTitle(articleVO.getArticleTitle());
        RkArticle.setArticleContent(articleVO.getArticleContent());
        RkArticle.setMenuId(articleVO.getMenuId());

        int result = rkArticleMapper.updateById(RkArticle);
        if (result > 0) {
            return BeanUtil.copyProperties(RkArticle, RkArticleVO.class);
        }

        return null;
    }

    @Override
    public Long delete(Long id) {

        RkArticle RkArticle = rkArticleMapper.selectByPrimaryKey(id);
        RkArticle.setDeletedStatus(DeletedStatusEnum.DELETED.getCode());

        rkArticleMapper.updateByPrimaryKeySelective(RkArticle);

        return RkArticle.getId();
    }

    @Override
    public Page<RkSearchVO> searchArticlePage(String content, Integer pageNum, Integer pageSize) {

        int total = rkArticleMapper.selectCountByFulltextSearch(content);

        int offset = (pageNum - 1) * pageSize;
        List<RkArticle> RkArticles = rkArticleMapper.selectByFulltextSearch(content, offset, pageSize);

        Map<Long, RkMenu> menuMap = new HashMap<>();
        if (CollUtil.isNotEmpty(RkArticles)) {
            Set<Long> menuIds = RkArticles.stream().map(RkArticle::getMenuId).collect(Collectors.toSet());
            List<RkMenu> RkMenus = rkMenuMapper.selectRootMenu(menuIds);
            menuMap = RkMenus.stream().collect(Collectors.toMap(RkMenu::getId, menu -> menu));
        }

        Map<Long, RkMenu> finalMenuMap = menuMap;
        List<RkSearchVO> result = RkArticles.stream()
                .map(article -> {
                    RkMenu RkMenu = finalMenuMap.get(article.getMenuId());
                    return new RkSearchVO(
                            article.getId(),
                            RkMenu.getParentId(),
                            article.getMenuId(),
                            article.getArticleTitle(),
                            article.getArticleContent());
                })
                .toList();

        Page<RkSearchVO> page = new Page<>(pageNum, pageSize, total);
        page.setRecords(result);

        return page;
    }
}
