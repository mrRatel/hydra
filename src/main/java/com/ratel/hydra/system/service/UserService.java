package com.ratel.hydra.system.service;

import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.user.UserAdd;

public interface UserService {
    
    /**
     * @Description 创建用户
     * @Author      ratel
     * @Date        2020-05-21
     * @param       add
     * @return      void
     **/
    void add(UserAdd add);

    /**
     * @Description 通过用户名获取用户
     * @Author      ratel
     * @Date        2020-05-24
     * @param       username
     * @return      com.ratel.hydra.system.po.User
     **/
    User getByUsername(String username);
}
