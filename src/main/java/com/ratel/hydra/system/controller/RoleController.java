package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.converter.PageQueryParamConvert;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.vo.WebResult;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@RestController
@RequestMapping("role")
public class RoleController extends BaseController{
    @Autowired
    private RoleService service;

    @GetMapping("get/{id}")
    public WebResult getById(@PathVariable("id") Long id){
        return WebResultFactory.ok(service.getById(id));
    }

    @PostMapping("del/{id}")
    public WebResult delById(@PathVariable("id") Long id){
        service.delById(id);
        return WebResultFactory.ok();
    }

    @PostMapping("addOrUpdate")
    public WebResult addOrUpdate(@RequestBody Role role){
        service.addOrUpdate(role);
        return WebResultFactory.ok(null,"操作成功");
    }

    @PostMapping("batchDel")
    public WebResult batchDel(@RequestBody List<Long> ids){
        service.batchDelByIds(ids);
        return WebResultFactory.ok(null,"操作成功");
    }

    @GetMapping("page")
    public WebResult page(PageQuery<Role> query){
        return WebResultFactory.ok(service.page(PageQueryParamConvert.convert(query)));
    }
}
