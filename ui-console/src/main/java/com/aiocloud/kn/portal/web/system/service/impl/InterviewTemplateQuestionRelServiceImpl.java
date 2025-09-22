package com.aiocloud.kn.portal.web.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.aiocloud.kn.portal.dao.system.domain.KnInterviewTemplateQuestionRel;
import com.aiocloud.kn.portal.dao.system.dto.KnInterviewTemplateQuestionRelDTO;
import com.aiocloud.kn.portal.dao.system.mapper.KnInterviewTemplateQuestionRelMapper;
import com.aiocloud.kn.portal.web.system.service.InterviewTemplateQuestionRelService;
import com.aiocloud.kn.portal.web.system.vo.KnInterviewTemplateQuestionRelVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @description: InterviewTemplateQuestionRelServiceImpl.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 22:25
 */
@RequiredArgsConstructor
@Service
public class InterviewTemplateQuestionRelServiceImpl implements InterviewTemplateQuestionRelService {

    private final KnInterviewTemplateQuestionRelMapper knInterviewTemplateQuestionRelMapper;

    @Override
    public KnInterviewTemplateQuestionRelVO create(KnInterviewTemplateQuestionRelVO interviewTemplateQuestionRelVO) {

        KnInterviewTemplateQuestionRel knInterviewTemplateQuestionRel = BeanUtil.copyProperties(interviewTemplateQuestionRelVO, KnInterviewTemplateQuestionRel.class);
        knInterviewTemplateQuestionRelMapper.insert(knInterviewTemplateQuestionRel);
        interviewTemplateQuestionRelVO.setId(knInterviewTemplateQuestionRel.getId());

        return interviewTemplateQuestionRelVO;
    }

    @Transactional
    @Override
    public List<KnInterviewTemplateQuestionRelVO> batchCreate(List<KnInterviewTemplateQuestionRelVO> interviewTemplateQuestionRelVOs) {

        if (CollUtil.isEmpty(interviewTemplateQuestionRelVOs)) {
            return new ArrayList<>();
        }

        KnInterviewTemplateQuestionRelVO knInterviewTemplateQuestionRelVO = interviewTemplateQuestionRelVOs.get(0);
        Long templateId = knInterviewTemplateQuestionRelVO.getTemplateId();
        knInterviewTemplateQuestionRelMapper.deleteByTemplateId(templateId);

        List<KnInterviewTemplateQuestionRel> entities = interviewTemplateQuestionRelVOs.stream()
                .map(vo -> BeanUtil.copyProperties(vo, KnInterviewTemplateQuestionRel.class))
                .collect(Collectors.toList());

        knInterviewTemplateQuestionRelMapper.insertBatch(entities);

        return entities.stream()
                .map(entity -> BeanUtil.copyProperties(entity, KnInterviewTemplateQuestionRelVO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<KnInterviewTemplateQuestionRelVO> getByTemplateId(Long templateId) {
        List<KnInterviewTemplateQuestionRelDTO> result = knInterviewTemplateQuestionRelMapper.selectRelWithQuestionTitle(templateId);
        return BeanUtil.copyToList(result, KnInterviewTemplateQuestionRelVO.class);
    }

    @Override
    public Long delete(Long id) {
        knInterviewTemplateQuestionRelMapper.deleteById(id);
        return id;
    }

    @Override
    @Transactional
    public Integer batchDelete(List<Long> ids) {
        return knInterviewTemplateQuestionRelMapper.deleteBatchIds(ids);
    }
}
