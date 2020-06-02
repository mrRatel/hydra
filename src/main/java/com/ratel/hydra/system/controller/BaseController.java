package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.constant.ExceptionEnum;
import com.ratel.hydra.common.execption.SystemException;
import com.ratel.hydra.system.po.User;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;

/**
 * @author ratel
 * @date 2020-05-25
 */
public class BaseController {

    public User currentUser(){
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        if (principal == null){
            throw new SystemException(ExceptionEnum.AUTH1008);
        }
        return principal;
    }
}
