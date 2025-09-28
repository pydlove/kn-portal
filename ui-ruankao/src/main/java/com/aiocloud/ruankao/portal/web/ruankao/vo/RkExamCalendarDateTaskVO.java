package com.aiocloud.ruankao.portal.web.ruankao.vo;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamCalendarDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: RkExamCalendarDateVO.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 17:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RkExamCalendarDateTaskVO extends RkExamCalendarDate {

    private Boolean isFinished;
}
