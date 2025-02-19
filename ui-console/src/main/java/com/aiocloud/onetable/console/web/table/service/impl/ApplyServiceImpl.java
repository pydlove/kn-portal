package com.aiocloud.onetable.console.web.table.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.onetable.console.base.common.PageRequest;
import com.aiocloud.onetable.console.base.common.PaginationResult;
import com.aiocloud.onetable.console.web.table.dto.ApplyDTO;
import com.aiocloud.onetable.console.web.table.service.ApplyService;
import com.aiocloud.onetable.console.web.table.vo.ApplyVO;
import com.aiocloud.onetable.mysql.table.mapper.ApplyMapper;
import com.aiocloud.onetable.mysql.table.po.ApplyPO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @description: ApplyServiceImpl.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-18 14:51 
 */
@RequiredArgsConstructor
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, ApplyPO> implements ApplyService {

    private final ApplyMapper applyMapper;

    @Override
    public int submitApply(ApplyDTO applyDTO) {
        ApplyPO applyPO = BeanUtil.copyProperties(applyDTO, ApplyPO.class);
        return applyMapper.insertSelective(applyPO);
    }

    @Override
    public PaginationResult<ApplyVO> getAllApplies(ApplyDTO applyDTO, PageRequest pageRequest) {

        Page<ApplyPO> applyPage = new Page<>(pageRequest.getPage(), pageRequest.getPage());
        QueryWrapper<ApplyPO> queryWrapper = new QueryWrapper<>();

        if (StrUtil.isNotEmpty(applyDTO.getUsername())) {
            queryWrapper.lambda().eq(ApplyPO::getUsername, applyDTO.getUsername());
        }

        Page<ApplyPO> result = applyMapper.selectPage(applyPage, queryWrapper);
        List<ApplyPO> records = result.getRecords();
        List<ApplyVO> applies = BeanUtil.copyToList(records, ApplyVO.class);

        return new PaginationResult<>(result.getTotal(), applies);
    }
}