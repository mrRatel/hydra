package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.common.mapstruct.RoleStruct;
import com.ratel.hydra.system.mapper.RoleMapper;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.service.RoleService;
import com.ratel.hydra.system.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleStruct roleStruct;
    @Override
    public Role getById(Long id) {
        return super.getById(id);
    }

    @Override
    public void delById(Long id) {
        removeById(id);
    }

    @Override
    public IPage<Role> page(PageQuery query) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>().orderByDesc(Role::getModifyTime);
        if (query.getQuery() != null){
            Role role = (Role)query.getQuery();
            if (StringUtils.isNoneBlank(role.getRoleName())) {
                queryWrapper.likeRight(Role::getRoleName,role.getRoleName());
            }
            if (StringUtils.isNoneBlank(role.getRoleCode())){
                queryWrapper.eq(Role::getRoleCode,role.getRoleCode());
            }
            if (StringUtils.isNoneBlank(role.getDescription())){
                queryWrapper.like(Role::getDescription,role.getDescription());
            }
        }
        return page(query.getPage(), queryWrapper);
    }

    @Override
    public void addOrUpdate(Role role) {
        saveOrUpdate(role);
    }


    @Override
    public void batchDelByIds(List<Long> ids) {
        removeByIds(ids);
    }

    @Override
    public List<Role> list(User user) {
        return baseMapper.selectListByUserId(user.getId());
    }


    @Override
    public List<Role> list() {
        return super.list();
    }

    @Override
    public List<RoleVO> getRoleVOs(Long userId) {
        if (userId == null){
            return Collections.emptyList();
        }
        return baseMapper.selectRoleVOs(userId);
    }

    @Override
    public void addUserRoleRelation(List<Long> roleIds, Long id) {

    }

    @Override
    public List<Long> getCurrentlyNotExistRolesForCurrentUser(Long id, List<Long> roleIds) {
        if (id == null){
            return Collections.emptyList();
        }
       return baseMapper.selectCurrentlyNotExistRolesForCurrentUser(id,roleIds);
    }

    @Override
    public List<Long> getNeedDeleteRolesByCurrentUser(Long userId, List<Long> roleIds) {
        if (userId == null){
            return Collections.emptyList();
        }
        return baseMapper.selectNeedDeleteRolesByCurrentUser(userId,roleIds);
    }
}
