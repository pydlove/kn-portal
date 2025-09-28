package com.aiocloud.kn.portal.dao.ruankao.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *
 * @description: RkUserCalendarRel.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-28 8:57
 */
@TableName(value ="rk_user_calendar_rel")
@Data
public class RkUserCalendarRel {
    private Long id;

    private Long calendarId;

    private Long userId;
}