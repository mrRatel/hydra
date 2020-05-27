package com.ratel.hydra.system.controller;

import com.ratel.hydra.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ratel
 * @date 2020-05-25
 */
@Slf4j
@Controller
@RequestMapping("menu")
public class MenuViewController extends BaseController{

    private static final String VIEW_PREFIX = "/admin/";
    private static final String VIEW_SUFFIX = ".html";

    @Autowired
    private MenuService menuService;

    @GetMapping("view")
    public ModelAndView view(ModelAndView view){
        view.addObject("data",menuService.findMenuTreeList(currentUser()));
        view.setViewName(VIEW_PREFIX + "menu/menu" + VIEW_SUFFIX);
        return view;
    }




}
