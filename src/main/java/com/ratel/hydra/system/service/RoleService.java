package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.vo.RoleVO;

import java.util.List;

 public interface RoleService{
     Role getById(Long id);

     void delById(Long id);

     IPage<Role> page(PageQuery<Role> query);

     void addOrUpdate(Role role);


     void batchDelByIds(List<Long> ids);

     List<Role> list(User user);

     List<Role> list();

     List<RoleVO> getRoleVOs(Long userId);

     void addUserRoleRelation(List<Long> roleIds, Long id);

     /*
      * @Description 获取当前用户没有的角色
      * @Author      ratel
      * @Date        2020/7/5
      * @param       id 用户id
      * @param       roleIds 角色列表
      * @return      java.util.List<java.lang.Long>
      **/
     List<Long> getCurrentlyNotExistRolesForCurrentUser(Long id, List<Long> roleIds);

     /**
      * @Description 获取需要删除的角色
      * @Author      ratel
      * @Date        2020/7/5
      * @param       id 用户id
      * @param       roleIds 当前所拥有的所有角色
      * @return      java.util.List<java.lang.Long>
      **/
     List<Long> getNeedDeleteRolesByCurrentUser(Long id, List<Long> roleIds);
 }
