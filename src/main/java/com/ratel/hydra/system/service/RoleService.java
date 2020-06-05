package com.ratel.hydra.system.service;

import com.ratel.hydra.system.po.Role;

public interface RoleService {

    Role getById(Long id);

    void delById(Long id);

    void addOrUpdate(Role role);
}
