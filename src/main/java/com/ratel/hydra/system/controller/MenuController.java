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
public class MenuController extends BaseController<MenuServiceImpl,Menu> {

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

    @Override
    @OperatingInfo(operation = "获取菜单")
    public WebResult getById(Long id) {
        return super.getById(id);
    }

    @Override
    @OperatingInfo(operation = "删除菜单")
    public WebResult delById(Long id) {
        return super.delById(id);
    }

    @Override
    @OperatingInfo(operation = "保存菜单")
    public WebResult addOrUpdate(@RequestBody Menu po) {
        User user = currentUser();
        if (NumberUtils.isZero(po.getId())) {
            po.setCreator(user.getId());
        }
        po.setModifier(user.getId());
        return super.addOrUpdate(po);
    }

    @Override
    @OperatingInfo(operation = "批量删除菜单")
    public WebResult batchDel(@RequestBody List<Long> ids) {
        return super.batchDel(ids);
    }

    @Override
    @OperatingInfo(operation = "分页获取菜单")
    public WebResult page(PageQuery<Menu> query, Menu po) {
        return super.page(query, po);
    }

    @GetMapping("permission")
    @OperatingInfo(operation = "获取所有菜单")
    @RequiresRoles(RoleProperty.ROLE_CODER)
    public WebResult permission(){
        return WebResultFactory.ok(menuTreeStruct.toMenuTree(service.list()));
    }
}
