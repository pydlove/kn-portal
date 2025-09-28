package com.aiocloud.ruankao.portal.web.ruankao.service;

import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamCalendarQuestionRelVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 考试日历问题关联服务接口
 */
public interface ExamCalendarQuestionRelService {

    /**
     * 批量保存日历问题关联关系
     * @param calendarId 日历日期ID
     * @param relations 关联关系列表
     * @return 是否保存成功
     */
    boolean batchSaveRelations(Long calendarId, List<RkExamCalendarQuestionRelVO> relations);

    /**
     * 根据日历日期ID查询关联的问题
     *
     * @param calendarId 日历日期ID
     * @param pageNum
     * @param pageSize
     * @return 关联问题列表
     */
    Page<RkExamCalendarQuestionRelVO> pageByCalendarDateId(Long calendarId, Long pageNum, Long pageSize);

    /**
     * 删除日历日期关联的所有问题
     * @param calendarDateId 日历日期ID
     * @return 是否删除成功
     */
    boolean deleteByCalendarDateId(Long calendarDateId);
}
