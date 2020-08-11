package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.vo.RoleVO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "role") //缓存名字
public interface RoleService {

    @CacheEvict(key = "#p0") //表示用参数列表 第一参数作为缓存的 key
    void delById(Long id);

    @Cacheable(key = "#p0.query") //表示用参数列表 第一参数的query对象作为缓存的 key
    IPage<Role> page(PageQuery<Role> query);

    @Cacheable(key = "#p0") //表示用参数列表 第一参数作为缓存的 key
    Role getById(Long id);

    @CachePut(key = "#p0") //表示用参数列表 第一参数作为缓存的 key
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
     * @param id      用户id
     * @param roleIds 当前所拥有的所有角色
     * @return java.util.List<java.lang.Long>
     * @Description 获取需要删除的角色
     * @Author ratel
     * @Date 2020/7/5
     **/
    List<Long> getNeedDeleteRolesByCurrentUser(Long id, List<Long> roleIds);
}
