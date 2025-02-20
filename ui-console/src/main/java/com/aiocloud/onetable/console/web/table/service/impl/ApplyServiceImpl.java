package com.aiocloud.onetable.console.web.table.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.onetable.console.base.common.PageRequest;
import com.aiocloud.onetable.console.base.common.PaginationResult;
import com.aiocloud.onetable.console.web.sys.service.UserService;
import com.aiocloud.onetable.console.web.table.dto.ApplyDTO;
import com.aiocloud.onetable.console.web.table.dto.BatchApplyDTO;
import com.aiocloud.onetable.console.web.table.enums.ApplyStatusEnum;
import com.aiocloud.onetable.console.web.table.service.ApplyService;
import com.aiocloud.onetable.console.web.table.vo.ApplyVO;
import com.aiocloud.onetable.mysql.table.mapper.ApplyMapper;
import com.aiocloud.onetable.mysql.table.mapper.TableInfoMapper;
import com.aiocloud.onetable.mysql.table.po.ApplyPO;
import com.aiocloud.onetable.mysql.table.po.TableInfoPO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final TableInfoMapper tableInfoMapper;
    private final UserService userService;

    @Override
    public int submitApply(ApplyDTO applyDTO) {
        ApplyPO applyPO = BeanUtil.copyProperties(applyDTO, ApplyPO.class);

        // Get the user's ID from the token.
        Long userId = userService.getCurrentUserId();
        applyPO.setUserId(userId);
        applyPO.setApplyStatus(ApplyStatusEnum.PENDING.getCode());

        return applyMapper.insertSelective(applyPO);
    }

    @Override
    public PaginationResult<ApplyVO> getAllApplies(ApplyDTO applyDTO, PageRequest pageRequest) {

        Page<ApplyPO> applyPage = new Page<>(pageRequest.getPage(), pageRequest.getRows());
        QueryWrapper<ApplyPO> queryWrapper = new QueryWrapper<>();

        if (StrUtil.isNotEmpty(applyDTO.getUsername())) {
            queryWrapper.lambda().eq(ApplyPO::getUsername, applyDTO.getUsername());
        }

        queryWrapper.lambda().orderByDesc(ApplyPO::getCreateTime);

        Page<ApplyPO> result = applyMapper.selectPage(applyPage, queryWrapper);
        List<ApplyPO> records = Optional.ofNullable(result.getRecords()).orElse(new ArrayList<>());
        List<ApplyVO> applies = BeanUtil.copyToList(records, ApplyVO.class);

        List<TableInfoPO> tables = Optional.ofNullable(tableInfoMapper.selectAll()).orElse(new ArrayList<>());
        Map<Long, String> tableMap = tables.stream().collect(Collectors.toMap(TableInfoPO::getId, TableInfoPO::getTableName, (a, b) -> b));

        applies.forEach(applyVO -> {
            ApplyStatusEnum applyStatusEnum = ApplyStatusEnum.fromCode(applyVO.getApplyStatus());
            applyVO.setChApplyStatus(null != applyStatusEnum ? applyStatusEnum.getDescription() : null);
            applyVO.setTableName(tableMap.getOrDefault(applyVO.getTableId(), null));
        });
        return new PaginationResult<>(result.getTotal(), applies);
    }

    @Override
    public Integer updateApply(ApplyDTO applyDTO) {

        ApplyPO applyPO = new ApplyPO();
        applyPO.setId(applyDTO.getId());
        applyPO.setApplyStatus(applyDTO.getApplyStatus());

        return applyMapper.updateByPrimaryKeySelective(applyPO);
    }

    @Override
    public Integer batchUpdateApply(BatchApplyDTO batchApplyDTO) {

        List<Long> ids = batchApplyDTO.getIds();
        if (CollUtil.isNotEmpty(ids)) {
            List<ApplyPO> batchApplies = new ArrayList<>();
            for (Long id : ids) {
                ApplyPO applyPO = new ApplyPO();
                applyPO.setId(id);
                applyPO.setApplyStatus(batchApplyDTO.getApplyStatus());
                batchApplies.add(applyPO);
            }

            return applyMapper.batchUpdate(batchApplies);
        }

        return null;
    }
}