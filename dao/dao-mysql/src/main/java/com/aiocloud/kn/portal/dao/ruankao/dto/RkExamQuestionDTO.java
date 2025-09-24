package com.aiocloud.kn.portal.dao.ruankao.dto;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: RkExamQuestionDTO.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-24 15:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RkExamQuestionDTO extends RkExamQuestion {

    private Integer sortOrder;
}
