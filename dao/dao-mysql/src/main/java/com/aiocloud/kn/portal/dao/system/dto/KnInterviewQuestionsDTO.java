package com.aiocloud.kn.portal.dao.system.dto;

import com.aiocloud.kn.portal.dao.base.domain.BaseDomain;
import com.aiocloud.kn.portal.dao.system.domain.KnInterviewQuestions;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: KnInterviewQuestions.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 19:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class KnInterviewQuestionsDTO extends KnInterviewQuestions {

    private Long templateId;
    private String templateName;
}