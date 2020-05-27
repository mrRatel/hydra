package com.ratel.hydra.system.controller;

import com.ratel.hydra.system.po.User;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;

/**
 * @author ratel
 * @date 2020-05-25
 */
public class BaseController {

    public User currentUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }
}
