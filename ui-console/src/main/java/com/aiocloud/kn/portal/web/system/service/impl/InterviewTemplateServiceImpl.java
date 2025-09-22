package com.aiocloud.kn.portal.web.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.kn.portal.dao.system.domain.KnInterviewTemplate;
import com.aiocloud.kn.portal.dao.system.domain.KnMenu;
import com.aiocloud.kn.portal.dao.system.mapper.KnInterviewTemplateMapper;
import com.aiocloud.kn.portal.enums.DeletedStatusEnum;
import com.aiocloud.kn.portal.web.system.service.InterviewTemplateService;
import com.aiocloud.kn.portal.web.system.vo.IdNameVO;
import com.aiocloud.kn.portal.web.system.vo.KnInterviewTemplateVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @description: InterviewTemplateServiceImpl.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 19:29
 */
@RequiredArgsConstructor
@Service
public class InterviewTemplateServiceImpl implements InterviewTemplateService {

    private final KnInterviewTemplateMapper knInterviewTemplateMapper;

    @Override
    public Page<KnInterviewTemplateVO> getInterviewTemplatePage(String templateName, Integer pageNum, Integer pageSize) {

        Page<KnInterviewTemplate> page = new Page<>(pageNum, pageSize);

        QueryWrapper<KnInterviewTemplate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());

        if (StrUtil.isNotBlank(templateName)) {
            queryWrapper.like("template_name", templateName);
        }

        queryWrapper.orderByDesc("create_time");

        Page<KnInterviewTemplate> questionsPage = knInterviewTemplateMapper.selectPage(page, queryWrapper);

        List<KnInterviewTemplateVO> records = questionsPage.getRecords().stream()
                .map(menu -> BeanUtil.copyProperties(menu, KnInterviewTemplateVO.class))
                .collect(Collectors.toList());

        Page<KnInterviewTemplateVO> voPage = new Page<>(pageNum, pageSize, questionsPage.getTotal());
        voPage.setRecords(records);

        return voPage;
    }

    @Override
    public KnInterviewTemplateVO create(KnInterviewTemplateVO InterviewTemplateVO) {

        KnInterviewTemplate knInterviewTemplate = new KnInterviewTemplate();
        knInterviewTemplate.setTemplateName(InterviewTemplateVO.getTemplateName());
        knInterviewTemplate.setTemplateDesc(InterviewTemplateVO.getTemplateDesc());
        knInterviewTemplate.setTemplateLevel(InterviewTemplateVO.getTemplateLevel());

        int result = knInterviewTemplateMapper.insert(knInterviewTemplate);
        if (result > 0) {
            return BeanUtil.copyProperties(knInterviewTemplate, KnInterviewTemplateVO.class);
        }

        return null;
    }

    @Override
    public KnInterviewTemplateVO update(Long id, KnInterviewTemplateVO InterviewTemplateVO) {

        KnInterviewTemplate knInterviewTemplate = knInterviewTemplateMapper.selectById(id);
        if (knInterviewTemplate == null) {
            return null;
        }

        knInterviewTemplate.setTemplateName(InterviewTemplateVO.getTemplateName());
        knInterviewTemplate.setTemplateDesc(InterviewTemplateVO.getTemplateDesc());
        knInterviewTemplate.setTemplateLevel(InterviewTemplateVO.getTemplateLevel());

        int result = knInterviewTemplateMapper.updateById(knInterviewTemplate);
        if (result > 0) {
            return BeanUtil.copyProperties(knInterviewTemplate, KnInterviewTemplateVO.class);
        }

        return null;
    }

    @Override
    public Long delete(Long id) {

        KnInterviewTemplate knInterviewTemplate = knInterviewTemplateMapper.selectByPrimaryKey(id);
        knInterviewTemplate.setDeletedStatus(DeletedStatusEnum.DELETED.getCode());

        knInterviewTemplateMapper.updateByPrimaryKeySelective(knInterviewTemplate);

        return knInterviewTemplate.getId();
    }

    @Override
    public List<IdNameVO> getAllTemplates() {

        QueryWrapper<KnInterviewTemplate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());
        queryWrapper.orderByDesc("create_time");

        List<KnInterviewTemplate> knInterviewTemplate = knInterviewTemplateMapper.selectList(queryWrapper);

        return knInterviewTemplate.stream()
                .map(element -> new IdNameVO(element.getId(), element.getTemplateName()))
                .collect(Collectors.toList());
    }
}
