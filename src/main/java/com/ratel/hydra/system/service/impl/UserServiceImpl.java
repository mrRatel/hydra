package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.system.mapper.UserMapper;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Service
public class UserServiceImpl extends IBaseServiceImpl<UserMapper,User> implements UserService {

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
}
