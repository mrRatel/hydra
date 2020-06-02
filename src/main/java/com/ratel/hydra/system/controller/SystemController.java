package com.ratel.hydra.system.controller;
import com.ratel.hydra.system.dto.HomeInfo.HomeInfoBean;
import com.ratel.hydra.system.dto.HomeInfo.LogoInfoBean;
import com.google.common.collect.Lists;

import com.ratel.hydra.common.utils.WebUtil;
import com.ratel.hydra.system.dto.HomeInfo;
import com.ratel.hydra.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ratel
 * @date 2020-05-24
 */
@Controller
public class SystemController extends BaseController{
    @Autowired
    private MenuService menuService;
    @GetMapping("/")
    public String index(){
        return WebUtil.getRedirectUrl("/admin/index.html");
    }

    @ResponseBody
    @GetMapping("/home")
    public HomeInfo home(){
        HomeInfo homeInfo = new HomeInfo();
        HomeInfoBean homeInfoBean = new HomeInfoBean();
        homeInfoBean.setTitle("首页");
        homeInfoBean.setHref("page/welcome-1.html?t=1");

        homeInfo.setHomeInfo(homeInfoBean);
        LogoInfoBean logoInfoBean = new LogoInfoBean();
        logoInfoBean.setTitle("HydraAdmin");
        logoInfoBean.setImage("images/logo.png");
        logoInfoBean.setHref("");

        homeInfo.setLogoInfo(logoInfoBean);
        homeInfo.setMenuInfo( menuService.findMenuTreeList(currentUser()));
        return homeInfo;
    }
}
