package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.query.user.SavePremissionRequest;

import java.util.List;

public interface UserService{

    /**
     * @Description 通过用户名获取用户
     * @Author      ratel
     * @Date        2020-05-24
     * @param       username
     * @return      com.ratel.hydra.system.po.User
     **/
    User getByUsername(String username);

    
    User getById(Long id);

    
    void delById(Long id);

    
    void addOrUpdate(User po);

    
    IPage<User> page(PageQuery<User> query);

    
    void batchDelByIds(List<Long> ids);

    void savePremission(SavePremissionRequest savePremissionRequest);
}
