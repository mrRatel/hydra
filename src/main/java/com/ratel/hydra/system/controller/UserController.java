package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.common.constant.ExceptionEnum;
import com.ratel.hydra.common.execption.SystemException;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.properties.CaptchaProperty;
import com.ratel.hydra.common.utils.WebUtil;
import com.ratel.hydra.common.vo.WebResult;
import com.ratel.hydra.system.query.user.UserAdd;
import com.ratel.hydra.system.query.user.UserLogin;
import com.ratel.hydra.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


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
    @OperatingInfo(operation = "注册")
    public WebResult add(@RequestBody @Valid UserAdd add,HttpServletRequest request){
        service.add(add.setRegisterFrom(WebUtil.getBrowers(request)));
        UsernamePasswordToken token = new UsernamePasswordToken(add.getUsername(), add.getPassword(),false);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return WebResultFactory.ok("/index.html","注册成功");
    }

    @PostMapping("login")
    @OperatingInfo(operation = "登录")
    public WebResult login(@RequestBody @Valid UserLogin userLogin, HttpServletRequest request){
        HttpSession session = request.getSession();
        String captcha = (String)session.getAttribute(CaptchaProperty.CAPTCHA);
        String uCaptcha = userLogin.getCaptcha();
        if (!uCaptcha.equals(captcha)){
            session.removeAttribute(CaptchaProperty.CAPTCHA);
            throw new SystemException(ExceptionEnum.AUTH1007);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userLogin.getUsername(), userLogin.getPassword(),userLogin.isRememberMe());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return WebResultFactory.ok("/index.html","登录成功");
    }
}
