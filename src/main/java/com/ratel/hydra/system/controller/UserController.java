package com.ratel.hydra.system.controller;

import com.alibaba.fastjson.JSON;
import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.common.constant.ExceptionEnum;
import com.ratel.hydra.common.execption.SystemBusinessException;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.mapstruct.LoginLogStruct;
import com.ratel.hydra.common.properties.CaptchaProperty;
import com.ratel.hydra.common.properties.PermissionProperty;
import com.ratel.hydra.common.properties.RoleProperty;
import com.ratel.hydra.common.properties.ShiroProperty;
import com.ratel.hydra.common.utils.IpUtil;
import com.ratel.hydra.common.utils.JwtTokenUtil;
import com.ratel.hydra.common.utils.WebUtil;
import com.ratel.hydra.common.pojo.WebResult;
import com.ratel.hydra.security.token.JwtToken;
import com.ratel.hydra.system.po.LoginLog;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.query.user.SavePremissionRequest;
import com.ratel.hydra.system.query.user.UserLoginRequest;
import com.ratel.hydra.system.service.LoginLogService;
import com.ratel.hydra.system.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@RestController
@RequestMapping("user")
@Api(tags = "用户")
public class UserController extends BaseController{

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private LoginLogStruct loginLogStruct;

    @Autowired
    private ShiroProperty shiroProperty;
    
    @Autowired
    private UserService userService;

    @PostMapping("register")
    @OperatingInfo(operation = "注册")
    public WebResult add(@RequestBody @Valid User add,HttpServletRequest request){
        add.setRegisterFrom(WebUtil.getOperatingSystem(request));
        userService.addOrUpdate(add);
        UsernamePasswordToken token = new UsernamePasswordToken(add.getUsername(), add.getPassword(),false);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return WebResultFactory.ok("/index.html","注册成功");
    }

    @PostMapping("login")
    @OperatingInfo(operation = "登录")
    public WebResult login(@RequestBody @Valid UserLoginRequest userLoginRequest, HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        String captcha = (String)session.getAttribute(CaptchaProperty.CAPTCHA);
        String uCaptcha = userLoginRequest.getCaptcha();
        if (!uCaptcha.equals(captcha)){
            session.removeAttribute(CaptchaProperty.CAPTCHA);
            throw new SystemBusinessException(ExceptionEnum.AUTH1007);
        }
        //登录校验
        JwtToken token = new JwtToken(userLoginRequest.getUsername(), userLoginRequest.getPassword());
//        UsernamePasswordToken token = new UsernamePasswordToken(userLoginRequest.getUsername(), userLoginRequest.getPassword(), userLoginRequest.isRememberMe());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        //
//        HttpServletResponse response = WebUtils.getHttpResponse(subject);

        //添加 Token
        Map<String, Object> payLoad = new HashMap<>(5);
        payLoad.put("uid", 1);
        payLoad.put("exp", null);
        payLoad.put("user", JSON.toJSONString(subject.getPrincipal()));
        Cookie cookie = new Cookie(shiroProperty.getTokenName(),JwtTokenUtil.generatorToken(payLoad));
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);

        //记录登录日志
        LoginLog loginLog = loginLogStruct.toLoginLog(currentUser(), request);
        loginLog.setLocation(IpUtil.getCityInfo(loginLog.getIp()));
        loginLogService.add(loginLog);
        return WebResultFactory.ok("/index","登录成功");
    }

    @GetMapping("get/{id}")
    @OperatingInfo(operation = "获取用户")
    public WebResult getById(@PathVariable("id")Long id) {
        return WebResultFactory.ok(userService.getById(id));
    }

    @PostMapping("del/{id}")
    @OperatingInfo(operation = "删除用户")
    @RequiresPermissions(PermissionProperty.USER_DEL)
    public WebResult delById(@PathVariable("id")Long id) {
        userService.delById(id);
        return WebResultFactory.ok("操作成功");
    }

    @PostMapping("addOrUpdate")
    @OperatingInfo(operation = "保存用户")
    public WebResult addOrUpdate(@RequestBody User po) {
        userService.addOrUpdate(po);
        return WebResultFactory.ok("操作成功");
    }

    @PostMapping("batchDel")
    @OperatingInfo(operation = "批量删除用户")
    public WebResult batchDel(@RequestBody List<Long> ids) {
        userService.batchDelByIds(ids);
        return WebResultFactory.ok("操作成功");
    }

    @GetMapping("page")
    @OperatingInfo(operation = "分页获取用户")
    @RequiresRoles(RoleProperty.ROLE_CODER)
    public WebResult page(PageQuery<User> query, User po) {
        return WebResultFactory.ok(userService.page(query.setQuery(po)));
    }

    @OperatingInfo(operation = "保存权限")
    @RequiresRoles(RoleProperty.ROLE_CODER)
    public WebResult savePremission(@NotNull(message = "请求参数不能为空") SavePremissionRequest savePremissionRequest) {
        savePremissionRequest.setUser(currentUser());
        userService.savePremission(savePremissionRequest);
        return WebResultFactory.ok();
    }
}
