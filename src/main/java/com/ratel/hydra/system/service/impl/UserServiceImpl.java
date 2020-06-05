package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.common.constant.ExceptionEnum;
import com.ratel.hydra.common.execption.SystemException;
import com.ratel.hydra.system.mapper.UserMapper;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.user.UserAdd;
import com.ratel.hydra.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void add(UserAdd add) {

        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, add.getUsername()));
        if (user != null){
            throw new SystemException(ExceptionEnum.USER1001);
        }
        User po = new User();
        BeanUtils.copyProperties(add,po);
        save(po);
    }

    @Override
    public User getByUsername(String username) {
        return getOne(new LambdaQueryWrapper<User>().eq(User::getUsername,username));
    }
}
