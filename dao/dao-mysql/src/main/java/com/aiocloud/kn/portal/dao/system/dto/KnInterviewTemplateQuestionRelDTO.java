package com.aiocloud.kn.portal.dao.system.dto;

import com.aiocloud.kn.portal.dao.system.domain.KnInterviewTemplateQuestionRel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: KnInterviewTemplateQuestionRelDTO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-09-20 23:36 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class KnInterviewTemplateQuestionRelDTO extends KnInterviewTemplateQuestionRel {

    private String questionTitle;
}
