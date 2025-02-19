package com.aiocloud.onetable.mysql.table.mapper;

import com.aiocloud.onetable.mysql.table.po.TableInfoPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @description: TableInfoMapper.java 
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-19 22:01 
 */
@Mapper
public interface TableInfoMapper extends BaseMapper<TableInfoPO> {

    int deleteByPrimaryKey(Long id);

    int insert(TableInfoPO record);

    int insertSelective(TableInfoPO record);

    TableInfoPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TableInfoPO record);

    int updateByPrimaryKey(TableInfoPO record);

}
