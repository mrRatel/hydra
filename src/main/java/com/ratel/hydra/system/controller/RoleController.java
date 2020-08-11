package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.pojo.WebResult;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.service.RoleService;
import com.ratel.hydra.system.service.impl.RoleServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@RestController
@RequestMapping("role")
@Api(tags = "角色")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @GetMapping("get/{id}")
    @OperatingInfo(operation = "获取角色")
    public WebResult getById(@PathVariable("id") Long id) {
        return WebResultFactory.ok(roleService.getById(id));
    }

    @PostMapping("del/{id}")
    @OperatingInfo(operation = "删除角色")
    public WebResult delById(@PathVariable("id") Long id) {
        roleService.delById(id);
        return WebResultFactory.ok("操作成功");
    }

    @PostMapping("addOrUpdate")
    @OperatingInfo(operation = "保存角色")
    public WebResult addOrUpdate(@RequestBody Role po) {
        roleService.addOrUpdate(po);
        return WebResultFactory.ok("操作成功");
    }

    @PostMapping("batchDel")
    @OperatingInfo(operation = "批量删除角色")
    public WebResult batchDel(@RequestBody List<Long> ids) {
        roleService.batchDelByIds(ids);
        return WebResultFactory.ok("操作成功");
    }

    @GetMapping("page")
    @OperatingInfo(operation = "分页获取角色")
    public WebResult page(PageQuery<Role> query, Role po) {
        return WebResultFactory.ok(roleService.page(query.setQuery(po)));
    }
}
