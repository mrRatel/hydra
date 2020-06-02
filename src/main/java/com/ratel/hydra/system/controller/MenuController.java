package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.vo.WebResult;
import com.ratel.hydra.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController{

    @Autowired
    private MenuService service;

    @GetMapping("/list")
    public WebResult list(){
        return WebResultFactory.ok(service.findMenuTreeList(currentUser()));
    }
}
