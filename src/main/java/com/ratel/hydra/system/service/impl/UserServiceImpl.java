package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ratel.hydra.system.mapper.UserMapper;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.service.MenuService;
import com.ratel.hydra.system.service.RoleService;
import com.ratel.hydra.system.service.UserService;
import com.ratel.hydra.system.vo.SavePremissionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Service
public class UserServiceImpl extends IBaseServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Override
    public User baseGetById(Long id) {
        return getById(id);
    }

    @Override
    public void baseDelById(Long id) {
        removeById(id);
    }

    @Override
    public void baseAddOrUpdate(User po) {
        saveOrUpdate(po);
    }

    @Override
    public IPage<User> basePage(PageQuery query) {
        User user = (User)query.getQuery();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().orderByDesc(User::getModifyTime);
        if (user != null){
            if (StringUtils.isNoneBlank(user.getUsername())) {
                wrapper.likeRight(User::getUsername,user.getUsername());
            }
            if (StringUtils.isNoneBlank(user.getRealName())){
                wrapper.likeRight(User::getRealName,user.getRealName());
            }
        }
        return page(query.getPage(), wrapper);
    }

    @Override
    public void batchDelByIds(List<Long> ids) {
        removeByIds(ids);
    }


    @Override
    public User getByUsername(String username) {
        return getOne(new LambdaQueryWrapper<User>().eq(User::getUsername,username));
    }

    @Override
    public void savePremission(SavePremissionVO premission) {
        //获取当前没有的角色
        List<Long> addRoleIds = roleService.getCurrentlyNotExistRolesForCurrentUser(premission.getCurrentUser().getId(),premission.getRoleIds());
        //todo 增加对应角色关系
        //获取当前用户需要删除的角色
        List<Long>  delRoleIds = roleService.getNeedDeleteRolesByCurrentUser(premission.getCurrentUser().getId(),premission.getRoleIds());
    }
}
