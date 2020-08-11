package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.pojo.WebResult;
import com.ratel.hydra.system.po.LoginLog;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.query.loginLog.LoginLogQuery;
import com.ratel.hydra.system.service.LoginLogService;
import com.ratel.hydra.system.service.impl.LoginLogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Slf4j
@RestController
@RequestMapping("loginLog")
@CacheConfig(cacheNames = "loginLog")
public class LoginLogController extends BaseController{
    @Autowired
    private LoginLogService loginLogService;

    @GetMapping("get/{id}")
    @OperatingInfo(operation = "获取登录日志")
    public WebResult getById(@PathVariable("id")Long id) {
        return WebResultFactory.ok(loginLogService.getById(id));
    }

    @PostMapping("del/{id}")
    @OperatingInfo(operation = "删除登录日志")
    public WebResult delById(@PathVariable("id")Long id) {
        loginLogService.delById(id);
        return WebResultFactory.ok("操作成功");
    }

    @PostMapping("addOrUpdate")
    @OperatingInfo(operation = "保存登录日志")
    public WebResult addOrUpdate(@RequestBody LoginLog po) {
        loginLogService.addOrUpdate(po);
        return WebResultFactory.ok("操作成功");
    }

    @PostMapping("batchDel")
    @OperatingInfo(operation = "批量删除登录日志")
    public WebResult batchDel(@RequestBody List<Long> ids) {
        loginLogService.batchDelByIds(ids);
        return WebResultFactory.ok("操作成功");
    }

    @GetMapping("page")
    @OperatingInfo(operation = "分页获取登录日志")
    @Cacheable(key = "#p1")
    public WebResult page(PageQuery<LoginLogQuery> page, LoginLogQuery param) {
        return WebResultFactory.ok(loginLogService.page(page.setQuery(param)));
    }
}
