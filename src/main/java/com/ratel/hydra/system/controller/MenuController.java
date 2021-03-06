package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.mapstruct.MenuTreeStruct;
import com.ratel.hydra.common.properties.RoleProperty;
import com.ratel.hydra.common.utils.NumberUtils;
import com.ratel.hydra.common.pojo.WebResult;
import com.ratel.hydra.system.dto.MenuTree;
import com.ratel.hydra.system.po.Menu;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.service.MenuService;
import com.ratel.hydra.system.service.impl.MenuServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
@RequestMapping("menu")
@Api(tags = "菜单")
public class MenuController extends BaseController {

    @Autowired
    private MenuService service;
    @Autowired
    private MenuTreeStruct menuTreeStruct;

    @GetMapping("/list")
    @OperatingInfo(operation = "获取菜单列表")
    public WebResult list() {
        return WebResultFactory.ok(service.list(currentUser()));
    }

    @GetMapping("/menuTree")
    @OperatingInfo(operation = "获取菜单树")
    public WebResult menuTree() {
        return WebResultFactory.ok(service.findMenuTreeList(currentUser()));
    }

    @PostMapping("batchInsert")
    @OperatingInfo(operation = "批量增加菜单")
    public WebResult batchInsert(@RequestBody List<MenuTree> list) {
        service.batchInsert(list, 0L);
        return WebResultFactory.ok(null);
    }

    @GetMapping("get/{id}")
    @OperatingInfo(operation = "获取菜单")
    public WebResult getById(@PathVariable("id")Long id) {
        return WebResultFactory.ok(service.findById(id));
    }

    @PostMapping("del/{id}")
    @OperatingInfo(operation = "删除菜单")
    public WebResult delById(@PathVariable("id")Long id) {
        service.delById(id);
        return WebResultFactory.ok("操作成功");
    }

    @PostMapping("addOrUpdate")
    @OperatingInfo(operation = "保存菜单")
    public WebResult addOrUpdate(@RequestBody Menu po) {
        User user = currentUser();
        if (NumberUtils.isZero(po.getId())) {
            po.setCreator(user.getId());
        }
        po.setModifier(user.getId());
        service.addOrUpdate(po);
        return WebResultFactory.ok("操作成功");
    }

    @PostMapping("batchDel")
    @OperatingInfo(operation = "批量删除菜单")
    public WebResult batchDel(@RequestBody List<Long> ids) {
        service.delByIds(ids);
        return WebResultFactory.ok("操作成功");
    }

    @GetMapping("page")
    @OperatingInfo(operation = "分页获取菜单")
    public WebResult page(PageQuery<Menu> query, Menu po) {
        return WebResultFactory.ok(service.page(query.setQuery(po)));
    }

    @GetMapping("permission")
    @OperatingInfo(operation = "获取所有菜单")
    @RequiresRoles(RoleProperty.ROLE_CODER)
    public WebResult permission(){
        return WebResultFactory.ok(menuTreeStruct.toMenuTree(service.list()));
    }
}
