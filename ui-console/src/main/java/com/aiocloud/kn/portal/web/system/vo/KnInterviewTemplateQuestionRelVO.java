package com.aiocloud.kn.portal.web.system.vo;

import com.aiocloud.kn.portal.dao.system.domain.KnInterviewTemplateQuestionRel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *
 * @description: KnInterviewTemplateQuestionRelVO.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 22:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class KnInterviewTemplateQuestionRelVO extends KnInterviewTemplateQuestionRel {

    private List<Long> questionIds;

    private String questionTitle;
}
