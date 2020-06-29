package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.common.vo.WebResult;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.service.impl.RoleServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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
public class RoleController extends BaseController<RoleServiceImpl,Role>{
    @Override
    @OperatingInfo(operation = "获取角色")
    public WebResult getById(Long id) {
        return super.getById(id);
    }

    @Override
    @OperatingInfo(operation = "删除角色")
    public WebResult delById(Long id) {
        return super.delById(id);
    }

    @Override
    @OperatingInfo(operation = "保存角色")
    public WebResult addOrUpdate(Role po) {
        return super.addOrUpdate(po);
    }

    @Override
    @OperatingInfo(operation = "批量删除角色")
    public WebResult batchDel(List<Long> ids) {
        return super.batchDel(ids);
    }

    @Override
    @OperatingInfo(operation = "分页获取角色")
    public WebResult page(PageQuery<Role> query, Role po) {
        return super.page(query, po);
    }
}
