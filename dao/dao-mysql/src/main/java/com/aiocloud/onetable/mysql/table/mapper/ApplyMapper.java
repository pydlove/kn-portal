package com.aiocloud.onetable.mysql.table.mapper;

import com.aiocloud.onetable.mysql.table.po.ApplyPO;

import java.util.List;

/**
* @author py_wo
* @description 针对表【t_apply】的数据库操作Mapper
* @createDate 2025-02-18 14:37:11
* @Entity com.aiocloud.ontable.mysql.sys.po.TApply
*/
public interface ApplyMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ApplyPO record);

    int insertSelective(ApplyPO record);

    ApplyPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApplyPO record);

    int updateByPrimaryKey(ApplyPO record);

    List<ApplyPO> getAllApplies();

}
