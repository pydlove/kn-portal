package com.aiocloud.onetable.mysql.table.mapper;

import com.aiocloud.onetable.mysql.table.po.TableUserRelPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @description: TableUserRelMapper.java 
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-21 14:38 
 */
@Mapper
public interface TableUserRelMapper extends BaseMapper<TableUserRelPO> {

    int deleteByPrimaryKey(Long id);

    int insert(TableUserRelPO record);

    int insertSelective(TableUserRelPO record);

    TableUserRelPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TableUserRelPO record);

    int updateByPrimaryKey(TableUserRelPO record);

    TableUserRelPO selectByUserIdAndTableId(Long userId, Long tableId);
}
