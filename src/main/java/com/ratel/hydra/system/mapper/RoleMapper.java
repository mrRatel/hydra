package com.ratel.hydra.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ratel.hydra.system.po.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    /**
     * @Description 通过用户id获取当前用户所有角色
     * @Author      ratel
     * @Date        2020/6/30
     * @param       id
     * @return      java.util.List<com.ratel.hydra.system.po.Role>
     **/
    List<Role> selectListByUserId(Long id);
}