package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.query.PageQuery;

public interface RoleService {

    Role getById(Long id);

    void delById(Long id);

    void addOrUpdate(Role role);

    IPage<Role> page(PageQuery<Role> query);

}
