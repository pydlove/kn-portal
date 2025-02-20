package com.aiocloud.onetable.mysql.table.mapper;

import com.aiocloud.onetable.mysql.table.po.TableInfoPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @auther ybin
 */
@Mapper
public interface TableInfoMapper extends BaseMapper<TableInfoPO> {

    int deleteByPrimaryKey(Long id);

    int insert(TableInfoPO record);

    int insertSelective(TableInfoPO record);

    TableInfoPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TableInfoPO record);

    int updateByPrimaryKey(TableInfoPO record);

    List<TableInfoPO> selectAll();

}
