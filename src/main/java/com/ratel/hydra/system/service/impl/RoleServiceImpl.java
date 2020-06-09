package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.system.mapper.RoleMapper;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        return page(query.getPage());
    }
}
