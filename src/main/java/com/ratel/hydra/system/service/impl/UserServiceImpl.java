package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.common.constant.ExceptionEnum;
import com.ratel.hydra.common.execption.SystemException;
import com.ratel.hydra.system.mapper.UserMapper;
import com.ratel.hydra.system.mapper.UserRoleRelationMapper;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.po.UserRoleRelation;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.query.user.SavePremissionRequest;
import com.ratel.hydra.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Service
@Transactional()
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;


    @Override
    public User getById(Long id) {
        return getById(id);
    }

    @Override
    public void delById(Long id) {
        removeById(id);
    }

    @Override
    public void addOrUpdate(User po) {
        saveOrUpdate(po);
    }

    @Override
    public IPage<User> page(PageQuery query) {
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
    public void savePremission(SavePremissionRequest savePremissionRequest) {
        log.debug("execute savePremission");
        User user = savePremissionRequest.getUser();
        if (user == null){
             throw new SystemException(ExceptionEnum.AUTH1008);
        }
        //绑定角色
        List<Long> addRoleIds = savePremissionRequest.getAddRoleIds();
        if (!CollectionUtils.isEmpty(addRoleIds)) {
            List<UserRoleRelation> userRoleRelations = new ArrayList<>();
            addRoleIds.forEach(id -> userRoleRelations.add(new UserRoleRelation().setUserId(user.getId()).setRoleId(id)));
            userRoleRelationMapper.insertList(userRoleRelations);
        }

        //解绑角色
        List<Long> delRoleIds = savePremissionRequest.getDelRoleIds();
        if (!CollectionUtils.isEmpty(delRoleIds)) {
            userRoleRelationMapper.delete(new LambdaQueryWrapper<UserRoleRelation>().in(UserRoleRelation::getId, delRoleIds));
        }

       /* //绑定权限
        List<Long> addPremissionIds = savePremissionRequest.getAddPremissionIds();

        //解绑权限
        List<Long> delPremissionIds = savePremissionRequest.getDelPremissionIds();*/
    }
}
