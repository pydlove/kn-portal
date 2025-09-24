package com.aiocloud.kn.portal.dao.ruankao.domain;


import com.aiocloud.kn.portal.dao.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @description: RkExamCalendarDate.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 17:41
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="rk_exam_calendar_date")
@Data
public class RkExamCalendarDate extends BaseDomain {

    private Date calendarDate;

    private Integer sortOrder;

    private String planTitle;

    private String planDescription;
}