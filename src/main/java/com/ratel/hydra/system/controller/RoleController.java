package com.ratel.hydra.system.controller;

import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
//@RestController
@RequestMapping("role")
public class RoleController extends BaseController<RoleService,Role>{

/*    @GetMapping("get/{id}")
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
    public WebResult page(PageQuery<Role,Role> query,Role role){
        return WebResultFactory.ok(service.page(query.setQuery(role)));
    }*/
}
