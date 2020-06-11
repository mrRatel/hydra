package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.system.mapper.RoleMapper;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public Role getById(Long id) {
        return super.getById(id);
    }

    @Override
    public void delById(Long id) {
        removeById(id);
    }

    @Override
    public void addOrUpdate(Role role) {
        saveOrUpdate(role);
    }

    @Override
    public IPage<Role> page(PageQuery<Role> query) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>().orderByDesc(Role::getModifyTime);
        if (query.getQuery() != null){
            Role role = query.getQuery();
            if (StringUtils.isNoneBlank(role.getRoleName())) {
                queryWrapper.likeLeft(Role::getRoleName,role.getRoleName());
            }
            if (StringUtils.isNoneBlank(role.getRoleCode())){
                queryWrapper.eq(Role::getRoleCode,role.getRoleCode());
            }
            if (StringUtils.isNoneBlank(role.getDescription())){
                queryWrapper.like(Role::getDescription,role.getDescription());
            }
        }
        return page(query.getPage(),queryWrapper);
    }

    @Override
    public void batchDelByIds(List<Long> ids) {
        removeByIds(ids);
    }
}
