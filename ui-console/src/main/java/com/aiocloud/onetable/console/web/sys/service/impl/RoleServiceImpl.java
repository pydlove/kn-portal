package com.aiocloud.onetable.console.web.sys.service.impl;

import com.aiocloud.onetable.console.web.sys.service.RoleService;
import com.aiocloud.onetable.console.web.sys.vo.RoleVO;
import com.aiocloud.onetable.mysql.sys.mapper.SysRoleMapper;
import com.aiocloud.onetable.mysql.sys.po.SysRolePO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @description: RoleServiceImpl.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-02-22 22:08 
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRolePO> implements RoleService {


    private final SysRoleMapper sysRoleMapper;

    @Override
    public List<RoleVO> getAllRole() {

        List<SysRolePO> sysRolePOS = Optional.ofNullable(sysRoleMapper.selectAll()).orElse(new ArrayList<>());
        return sysRolePOS.stream().map(sysRolePO -> new RoleVO(sysRolePO.getId(), sysRolePO.getRoleName())).collect(Collectors.toList());
    }
}
