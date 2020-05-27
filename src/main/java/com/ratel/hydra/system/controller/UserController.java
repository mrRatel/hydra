package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.vo.WebResult;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.user.UserAdd;
import com.ratel.hydra.system.query.user.UserLogin;
import com.ratel.hydra.system.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.ratel.hydra.common.constant.ExceptionEnum.AUTH1005;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends BaseController{

    @Autowired
    private UserService service;


    @PostMapping("add")
    @ApiOperation("新增用户")
    public WebResult add(@RequestBody @Valid UserAdd add){
        service.add(add);
        return WebResultFactory.ok(null,null);
    }

    @PostMapping("login")
    @ApiOperation("登录")
    public WebResult login(@RequestBody @Valid UserLogin userLogin){
        UsernamePasswordToken token = new UsernamePasswordToken(userLogin.getUsername(), userLogin.getPassword(),userLogin.isRememberMe());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return WebResultFactory.ok("/admin/index.html","登录成功");
    }
}
