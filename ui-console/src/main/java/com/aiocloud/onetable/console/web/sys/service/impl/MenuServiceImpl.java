package com.aiocloud.onetable.console.web.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.onetable.console.web.sys.service.MenuService;
import com.aiocloud.onetable.console.web.sys.service.UserService;
import com.aiocloud.onetable.console.web.sys.vo.MenuVO;
import com.aiocloud.onetable.mysql.sys.mapper.SysMenuMapper;
import com.aiocloud.onetable.mysql.sys.po.SysMenuPO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @description: MenuServiceImpl.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-21 22:37 
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuPO> implements MenuService {

    private final SysMenuMapper sysMenuMapper;

    @Override
    public List<MenuVO> selectAccessMenus(String username) {

        if (StrUtil.isEmpty(username)) {
            return new ArrayList<>();
        }

        List<SysMenuPO> menuPOList = Optional.ofNullable(sysMenuMapper.selectByUserName(username)).orElse(new ArrayList<>());
        return BeanUtil.copyToList(menuPOList, MenuVO.class);
    }
}
