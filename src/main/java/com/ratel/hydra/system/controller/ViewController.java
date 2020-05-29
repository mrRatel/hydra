package com.ratel.hydra.system.controller;

import com.alibaba.fastjson.JSON;
import com.ratel.hydra.system.dto.MenuTree;
import com.ratel.hydra.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author ratel
 * @date 2020-05-25
 */
@Slf4j
@Controller
@RequestMapping("view")
public class ViewController extends BaseController {
    @Autowired
    private MenuService menuService;

    @GetMapping("index")
    public ModelAndView index(ModelAndView view){
        List<MenuTree> menuTreeList = menuService.findMenuTreeList(currentUser());
        log.debug("menuTree:{}", JSON.toJSONString(menuTreeList));
        view.addObject("menuTree",menuTreeList);
        view.setViewName("view/index");
        return view;
    }
}
