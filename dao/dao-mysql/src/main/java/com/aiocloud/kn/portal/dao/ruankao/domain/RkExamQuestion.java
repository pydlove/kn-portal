package com.aiocloud.kn.portal.dao.ruankao.domain;

import com.aiocloud.kn.portal.dao.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 *
 * @description: RkExamQuestion.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 13:43
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="rk_exam_question")
@Data
public class RkExamQuestion extends BaseDomain {

    private String type;

    private String title;

    private BigDecimal score;

    private Integer difficulty;

    private Integer status;
}