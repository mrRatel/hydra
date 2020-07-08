package com.ratel.hydra.system.controller;

import com.alibaba.fastjson.JSON;
import com.ratel.hydra.common.mapstruct.MenuTreeStruct;
import com.ratel.hydra.common.properties.RoleProperty;
import com.ratel.hydra.common.properties.ViewUrlProperty;
import com.ratel.hydra.common.utils.WebUtil;
import com.ratel.hydra.system.dto.MenuTree;
import com.ratel.hydra.system.po.Menu;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.service.MenuService;
import com.ratel.hydra.system.service.RoleService;
import com.ratel.hydra.system.service.UserService;
import com.ratel.hydra.system.vo.MenuVO;
import com.ratel.hydra.system.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ratel
 * @date 2020-05-25
 */
@Slf4j
@Controller
@RequestMapping
public class ViewController extends BaseController{
    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuTreeStruct menuTreeStruct;

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

    @GetMapping("/view/authorize/{userId}")
    @RequiresRoles(RoleProperty.ROLE_CODER)
    public String authorize(Model model,@PathVariable("userId") Long userId){
        List<RoleVO> roleVOs = roleService.getRoleVOs(userId);
        model.addAttribute("roles",roleVOs);
        List<MenuVO> menuVOS = menuService.getMenuVOS(userId);
        model.addAttribute("premission",menuVOS);
        model.addAttribute("menuArray",menuVOS.stream().filter(MenuVO::isHave).map(MenuVO::getId).collect(Collectors.toList()).toString());
        model.addAttribute("roleArray",roleVOs.stream().filter(RoleVO::isHave).map(RoleVO::getId).collect(Collectors.toList()).toString());
        return ViewUrlProperty.USER_AUTHORIZE;
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

    @GetMapping("/view/permission")
    public String permissionAddView(Model view){
        List<Menu> list = menuService.list();
        List<Role> roles = roleService.list();
        view.addAttribute("list",list);
        view.addAttribute("roles",roles);
        return ViewUrlProperty.PERMISSION;
    }
}
