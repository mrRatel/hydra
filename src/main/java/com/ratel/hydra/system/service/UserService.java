package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.PageQuery;

import java.util.List;

public interface UserService extends IBaseServiceImpl<User>{

    /**
     * @Description 通过用户名获取用户
     * @Author      ratel
     * @Date        2020-05-24
     * @param       username
     * @return      com.ratel.hydra.system.po.User
     **/
    User getByUsername(String username);

    
    User baseGetById(Long id);

    
    void baseDelById(Long id);

    
    void baseAddOrUpdate(User po);

    
    IPage<User> basePage(PageQuery<User> query);

    
    void batchDelByIds(List<Long> ids);
}
