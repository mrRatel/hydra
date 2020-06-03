package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.vo.WebResult;
import com.ratel.hydra.system.dto.MenuTree;
import com.ratel.hydra.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;


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
        return WebResultFactory.ok(service.findMenuListByPage(currentUser()));
    }

    @PostMapping("batchInsert")
    public WebResult batchInsert(@RequestBody List<MenuTree> list){
        service.batchInsert(list,0L);
        return WebResultFactory.ok(null);
    }

    @GetMapping("/get/{id}")
    public WebResult getById(@PathVariable("id") @NotBlank(message = "菜单id不能为空") Long id){
        return WebResultFactory.ok(service.findById(id));
    }
}
