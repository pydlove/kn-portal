package com.aiocloud.kn.portal.dao.system.domain;

import com.aiocloud.kn.portal.dao.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @description: KnInterviewTemplate.java 
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-09-20 19:23
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="kn_interview_template")
@Data
public class KnInterviewTemplate extends BaseDomain {

    private String templateName;

    private String templateDesc;

    private Integer templateLevel;
}