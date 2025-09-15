package com.aiocloud.kn.portal.web.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.kn.portal.dao.system.domain.KnArticle;
import com.aiocloud.kn.portal.dao.system.domain.KnMenu;
import com.aiocloud.kn.portal.dao.system.mapper.KnArticleMapper;
import com.aiocloud.kn.portal.dao.system.mapper.KnMenuMapper;
import com.aiocloud.kn.portal.enums.DeletedStatusEnum;
import com.aiocloud.kn.portal.web.system.service.ArticleService;
import com.aiocloud.kn.portal.web.system.vo.KnArticleMenuVO;
import com.aiocloud.kn.portal.web.system.vo.KnArticleTitleVO;
import com.aiocloud.kn.portal.web.system.vo.KnArticleVO;
import com.aiocloud.kn.portal.web.system.vo.KnMenuVO;
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
public class ArticleServiceImpl implements ArticleService {

    private final KnArticleMapper knArticleMapper;
    private final KnMenuMapper knMenuMapper;

    @Override
    public List<KnArticleMenuVO> getTitleList(Long parentMenuId) {

        List<KnMenu> knMenus = knMenuMapper.selectByParentId(parentMenuId);
        if (CollUtil.isEmpty(knMenus)) {
            return null;
        }

        List<Long> menuIds = knMenus.stream().map(KnMenu::getId).toList();
        List<KnArticle> knArticles = knArticleMapper.selectTitleByMenuIds(menuIds);

        Map<Long, List<KnArticleTitleVO>> menuMap = new HashMap<>();
        for (KnArticle knArticle : knArticles) {
            Long menuId = knArticle.getMenuId();
            List<KnArticleTitleVO> articleTitleList = menuMap.computeIfAbsent(menuId, k -> new ArrayList<>());
            articleTitleList.add(new KnArticleTitleVO(knArticle.getId(), knArticle.getArticleTitle()));
        }

        List<KnArticleMenuVO> result = new ArrayList<>();
        for (KnMenu knMenu : knMenus) {
            List<KnArticleTitleVO> articleTitleList = menuMap.getOrDefault(knMenu.getId(), new ArrayList<>());
            KnArticleMenuVO knArticleMenuVO = new KnArticleMenuVO(knMenu.getId(), knMenu.getMenuName(), articleTitleList);
            result.add(knArticleMenuVO);
        }

        return result;
    }

    @Override
    public KnArticleVO getArticle(Long articleId) {

        KnArticle knArticle = knArticleMapper.selectByPrimaryKey(articleId);
        return BeanUtil.copyProperties(knArticle, KnArticleVO.class);
    }

    @Override
    public Page<KnArticleVO> getArticlePage(String title, Long menuId, Integer pageNum, Integer pageSize) {

        Page<KnArticle> page = new Page<>(pageNum, pageSize);

        QueryWrapper<KnArticle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());

        if (StrUtil.isNotBlank(title)) {
            queryWrapper.like("article_title", title);
        }

        if (null != menuId) {
             queryWrapper.eq("menu_id", menuId);
        }

        queryWrapper.orderByDesc("create_time");

        Page<KnArticle> menuPage = knArticleMapper.selectPage(page, queryWrapper);

        List<KnArticle> records = menuPage.getRecords();
        Map<Long, KnMenu> menuMap = new HashMap<>();
        if (CollUtil.isNotEmpty(records)) {
            Set<Long> menuIds = records.stream().map(KnArticle::getMenuId).collect(Collectors.toSet());
            List<KnMenu> knMenus = knMenuMapper.selectByIds(menuIds);
            menuMap = knMenus.stream().collect(Collectors.toMap(KnMenu::getId, menu -> menu));
        }

        Map<Long, KnMenu> finalMenuMap = menuMap;
        List<KnArticleVO> result = records.stream()
                .map(menu -> {
                    KnArticleVO knArticleVO = BeanUtil.copyProperties(menu, KnArticleVO.class);
                    KnMenu knMenu = Optional.ofNullable(finalMenuMap.get(menu.getMenuId())).orElse(new KnMenuVO());
                    knArticleVO.setMenuName(knMenu.getMenuName());

                    return knArticleVO;
                })
                .collect(Collectors.toList());

        Page<KnArticleVO> voPage = new Page<>(pageNum, pageSize, menuPage.getTotal());
        voPage.setRecords(result);

        return voPage;
    }

    @Override
    public KnArticleVO create(KnArticleVO articleVO) {

        KnArticle knArticle = new KnArticle();
        knArticle.setMenuId(articleVO.getMenuId());
        knArticle.setArticleTitle(articleVO.getArticleTitle());
        knArticle.setArticleContent(articleVO.getArticleContent());

        int result = knArticleMapper.insert(knArticle);
        if (result > 0) {
            return BeanUtil.copyProperties(knArticle, KnArticleVO.class);
        }

        return null;
    }

    @Override
    public KnArticleVO update(Long id, KnArticleVO articleVO) {

        KnArticle knArticle = knArticleMapper.selectById(id);
        if (knArticle == null) {
            return null;
        }

        knArticle.setArticleTitle(articleVO.getArticleTitle());
        knArticle.setArticleContent(articleVO.getArticleContent());
        knArticle.setMenuId(articleVO.getMenuId());

        int result = knArticleMapper.updateById(knArticle);
        if (result > 0) {
            return BeanUtil.copyProperties(knArticle, KnArticleVO.class);
        }

        return null;
    }

    @Override
    public Long delete(Long id) {

        KnArticle knArticle = knArticleMapper.selectByPrimaryKey(id);
        knArticle.setDeletedStatus(DeletedStatusEnum.DELETED.getCode());

        knArticleMapper.updateByPrimaryKeySelective(knArticle);

        return knArticle.getId();
    }
}
