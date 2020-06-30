package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.properties.RoleProperty;
import com.ratel.hydra.common.properties.ViewUrlProperty;
import com.ratel.hydra.common.utils.WebUtil;
import com.ratel.hydra.system.service.MenuService;
import com.ratel.hydra.system.service.RoleService;
import com.ratel.hydra.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ratel
 * @date 2020-05-25
 */
@Slf4j
@Controller
@RequestMapping
public class ViewController{
    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String redirectIndex(){
        return  WebUtil.getRedirectUrl(ViewUrlProperty.INDEX);
    }

    @GetMapping("/index")
    public String index(ModelAndView model){
         return ViewUrlProperty.INDEX;
    }

    @GetMapping("/login")
    public String login(ModelAndView model){

         return ViewUrlProperty.LOGIN;
    }

    @GetMapping("/view/role/{id}")
    public ModelAndView roleGetById(@PathVariable("id") Long id, ModelAndView view){
        view.addObject("data",roleService.baseGetById(id));
        view.setViewName(ViewUrlProperty.ROLE_EDIT);
        return  view;
    }

    @GetMapping("/view/role/add")
    public String roleAddView(){
        return ViewUrlProperty.ROLE_ADD;
    }

    @GetMapping("/view/user/{id}")
    @RequiresRoles(RoleProperty.ROLE_CODER)
    public ModelAndView userGetById(@PathVariable("id") Long id, ModelAndView view){
        view.addObject("data",userService.baseGetById(id));
        view.setViewName(ViewUrlProperty.USER_EDIT);
        return  view;
    }

    @GetMapping("/view/user/add")
    @RequiresRoles(RoleProperty.ROLE_CODER)
    public String userAddView(){
        return ViewUrlProperty.USER_ADD;
    }

    @GetMapping("/view/permission/{id}")
    public ModelAndView permissionAddView(@PathVariable("id") Long id, ModelAndView view){
        view.setViewName(ViewUrlProperty.PERMISSION_EDIT);
        view.addObject("data",menuService.findById(id));
        return  view;
    }

    @GetMapping("/view/permission/add")
    public String permissionAddView(){
        return ViewUrlProperty.PERMISSION_ADD;
    }
}
