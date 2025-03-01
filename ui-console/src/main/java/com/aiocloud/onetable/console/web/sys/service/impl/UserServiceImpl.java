package com.aiocloud.onetable.console.web.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.onetable.console.base.common.PageRequest;
import com.aiocloud.onetable.console.base.common.PaginationResult;
import com.aiocloud.onetable.console.base.exception.BadRequestException;
import com.aiocloud.onetable.console.base.exception.ErrorCode;
import com.aiocloud.onetable.console.config.security.JwtTokenGenerator;
import com.aiocloud.onetable.console.config.security.JwtTokenProperties;
import com.aiocloud.onetable.console.constant.SystemConstant;
import com.aiocloud.onetable.console.web.sys.UserPwdTool;
import com.aiocloud.onetable.console.web.sys.dto.LoginDTO;
import com.aiocloud.onetable.console.web.sys.dto.UserAddDTO;
import com.aiocloud.onetable.console.web.sys.dto.UserDTO;
import com.aiocloud.onetable.console.web.sys.dto.UserUpdateDTO;
import com.aiocloud.onetable.console.web.sys.enums.DeleteFlagEnum;
import com.aiocloud.onetable.console.web.sys.service.MenuService;
import com.aiocloud.onetable.console.web.sys.service.UserService;
import com.aiocloud.onetable.console.web.sys.vo.MenuVO;
import com.aiocloud.onetable.console.web.sys.vo.UserInfoVO;
import com.aiocloud.onetable.console.web.sys.vo.UserPageVO;
import com.aiocloud.onetable.console.web.table.service.TableInfoService;
import com.aiocloud.onetable.mysql.sys.mapper.SysRoleMapper;
import com.aiocloud.onetable.mysql.sys.mapper.SysUserMapper;
import com.aiocloud.onetable.mysql.sys.po.SysRolePO;
import com.aiocloud.onetable.mysql.sys.po.SysUserPO;
import com.aiocloud.onetable.mysql.table.dto.TableUserRelDTO;
import com.aiocloud.onetable.mysql.table.mapper.TableUserRelMapper;
import com.aiocloud.onetable.mysql.table.po.TableUserRelPO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Permission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *
 * @description: UserServiceImpl.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-01-21 15:24 
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUserPO> implements UserService {

    private static final Map<String, Long> USER_ID_MAP = new ConcurrentHashMap<>();

    private final MenuService menuService;
    private final TableUserRelMapper tableUserRelMapper;
    private final SysUserMapper sysUserMapper;
    private final SysRoleMapper sysRoleMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final JwtTokenProperties jwtTokenProperties;

    @Override
    public List<Permission> getPermissionByUsername(String username) {
        return null;
    }

    @Override
    public List<Permission> getPermissionByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<Permission> getPermissionByUser(User user) {
        return null;
    }

    @Override
    public UserInfoVO doLogin(LoginDTO login) {

        String username = login.getUsername();
        SysUserPO sysUser = Optional.ofNullable(sysUserMapper.selectByUsername(username)).orElse(new SysUserPO());
        String desPassword = UserPwdTool.doPasswordDeAesCBC(login.getRandomId(), login.getUserPwd());
        boolean matches = passwordEncoder.matches(desPassword, sysUser.getUserPassword());
        if (matches) {

            String token = jwtTokenGenerator.generateToken(username, jwtTokenProperties.getIssuer(), jwtTokenProperties.getAudience());

            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setToken(token);
            userInfoVO.setUserId(sysUser.getId());
            userInfoVO.setUsername(sysUser.getUserName());

            List<MenuVO> menuVOList = menuService.selectAccessMenus(username);
            userInfoVO.setMenuList(menuVOList);

            return userInfoVO;
        } else {
            throw new BadRequestException(ErrorCode.USER_OR_PASSWORD_ERROR);
        }

    }

    @Override
    public Long getCurrentUserId() {

        // Get HttpServletRequest
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String username = jwtTokenGenerator.getUserName(request);

        boolean userIdExist = USER_ID_MAP.containsKey(username);
        if (userIdExist) {
            return USER_ID_MAP.get(username);
        }

        // Query user id by username
        SysUserPO sysUser = Optional.ofNullable(sysUserMapper.selectByUsername(username)).orElse(new SysUserPO());
        USER_ID_MAP.put(username, sysUser.getId());

        return sysUser.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long insertUser(UserAddDTO user) {

        SysUserPO userPO = sysUserMapper.selectByUsername(user.getUserName());
        if (null != userPO) {
            throw new BadRequestException(ErrorCode.USER_EXIST);
        }

        String desPassword = UserPwdTool.doPasswordDeAesCBC(user.getRandomId(), user.getUserPwd());
        String pwd = passwordEncoder.encode(desPassword);

        SysUserPO sysUserPO = new SysUserPO();
        sysUserPO.setUserName(user.getUserName());
        sysUserPO.setRoleId(user.getRoleId());
        sysUserPO.setUserPassword(pwd);
        sysUserMapper.insertSelective(sysUserPO);

        Long userId = sysUserPO.getId();
        saveUserTableRel(user.getTableIds(), userId);

        return userId;
    }

    @Override
    public int deleteUserById(Long id) {

        SysUserPO sysUserPO = new SysUserPO();
        sysUserPO.setId(id);
        sysUserPO.setDeleteFlag(DeleteFlagEnum.DELETED.getCode());
        return sysUserMapper.updateByPrimaryKeySelective(sysUserPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateUser(UserUpdateDTO user) {

        Long userId = user.getUserId();

        SysUserPO sysUserPO = new SysUserPO();
        sysUserPO.setId(userId);
        sysUserPO.setUserName(user.getUserName());
        sysUserPO.setRoleId(user.getRoleId());

        if (StrUtil.isNotEmpty(user.getUserPwd())) {
            String desPassword = UserPwdTool.doPasswordDeAesCBC(user.getRandomId(), user.getUserPwd());
            String pwd = passwordEncoder.encode(desPassword);
            sysUserPO.setUserPassword(pwd);
        }

        tableUserRelMapper.deleteByUserId(userId);
        saveUserTableRel(user.getTableIds(), userId);

        return sysUserMapper.updateByPrimaryKeySelective(sysUserPO);
    }

    @Override
    public PaginationResult<UserPageVO> getUserPage(UserDTO userDTO, PageRequest pageRequest) {

        Page<SysUserPO> page = new Page<>(pageRequest.getPage(), pageRequest.getRows());
        QueryWrapper<SysUserPO> queryWrapper = new QueryWrapper<>();

        if (StrUtil.isNotEmpty(userDTO.getUserName())) {
            queryWrapper.lambda().eq(SysUserPO::getUserName, userDTO.getUserName());
        }

        queryWrapper.lambda().eq(SysUserPO::getDeleteFlag, DeleteFlagEnum.UNDELETE.getCode());
        queryWrapper.lambda().orderByDesc(SysUserPO::getCreateTime);

        Page<SysUserPO> result = sysUserMapper.selectPage(page, queryWrapper);
        List<SysUserPO> records = Optional.ofNullable(result.getRecords()).orElse(new ArrayList<>());
        List<UserPageVO> userPages = BeanUtil.copyToList(records, UserPageVO.class);

        List<SysRolePO> roles = Optional.ofNullable(sysRoleMapper.selectAll()).orElse(new ArrayList<>());
        Map<Long, String> roleMap = roles.stream().collect(Collectors.toMap(SysRolePO::getId, SysRolePO::getRoleName, (a, b) -> b));

        List<TableUserRelDTO> tableUserRelDTOS = Optional.ofNullable(tableUserRelMapper.selectAllUserTable()).orElse(new ArrayList<>());
        Map<Long, List<String>> userTableNamesMap = new HashMap<>();
        Map<Long, List<Long>> userTableIdsMap = new HashMap<>();
        for (TableUserRelDTO tableUserRelDTO : tableUserRelDTOS) {
            String tableComment = "【" + tableUserRelDTO.getTableComment() + "】";
            List<String> tableNames = userTableNamesMap.computeIfAbsent(tableUserRelDTO.getUserId(), k -> new ArrayList<>());
            List<Long> tableIds = userTableIdsMap.computeIfAbsent(tableUserRelDTO.getUserId(), k -> new ArrayList<>());
            if (!tableNames.contains(tableComment)) {
                tableNames.add(tableComment);
            }

            if (!tableIds.contains(tableUserRelDTO.getTableId())) {
                tableIds.add(tableUserRelDTO.getTableId());
            }
        }

        userPages.forEach(vo -> {
            vo.setRoleName(roleMap.getOrDefault(vo.getRoleId(), null));

            List<String> tableNames = userTableNamesMap.getOrDefault(vo.getId(), new ArrayList<>());
            vo.setTableNames(String.join(SystemConstant.SEPARATOR_COMMA, tableNames));

            List<Long> tableIds = userTableIdsMap.getOrDefault(vo.getId(), new ArrayList<>());
            vo.setTableIds(tableIds);
        });

        return new PaginationResult<>(result.getTotal(), userPages);
    }

    private void saveUserTableRel(List<Long> user, Long userId) {

        List<Long> tableIds = Optional.ofNullable(user).orElse(new ArrayList<>());
        for (Long tableId : tableIds) {
            TableUserRelPO tableUserRelPO = new TableUserRelPO();
            tableUserRelPO.setUserId(userId);
            tableUserRelPO.setTableId(tableId);
            tableUserRelPO.setCreateUid(getCurrentUserId());
            tableUserRelPO.setUpdateUid(getCurrentUserId());
            tableUserRelMapper.insertSelective(tableUserRelPO);
        }
    }
}
