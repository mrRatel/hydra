package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.system.mapper.RoleMapper;
import com.ratel.hydra.system.po.Role;
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
}
