package com.ratel.hydra.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.vo.RoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    /**
     * @param id
     * @return java.util.List<com.ratel.hydra.system.po.Role>
     * @Description 通过用户id获取当前用户所有角色
     * @Author ratel
     * @Date 2020/6/30
     **/
    List<Role> selectListByUserId(Long id);

    /**
     * @param userId
     * @return java.util.List<com.ratel.hydra.system.vo.RoleVO>
     * @Description 获取所有角色
     * @Author ratel
     * @Date 2020/7/5
     **/
    List<RoleVO> selectRoleVOs(@Param("userId") Long userId);

    /*
     * @Description 获取当前用户没有的角色  roleIds == null 时，角色列表为有所有角色
     * @Author      ratel
     * @Date        2020/7/5
     * @param       id 用户id
     * @param       roleIds 角色列表
     * @return      java.util.List<java.lang.Long>
     **/
    List<Long> selectCurrentlyNotExistRolesForCurrentUser(@Param("id") Long id, @Param("roleIds") List<Long> roleIds);

    List<Long> selectNeedDeleteRolesByCurrentUser(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);
}