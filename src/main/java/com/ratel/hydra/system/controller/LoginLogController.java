package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.common.pojo.WebResult;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.query.loginLog.LoginLogQuery;
import com.ratel.hydra.system.service.impl.LoginLogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Slf4j
@RestController
@RequestMapping("loginLog")
public class LoginLogController extends BaseController<LoginLogServiceImpl,LoginLogQuery> {

    @Override
    @OperatingInfo(operation = "获取登录日志")
    public WebResult getById(Long id) {
        return super.getById(id);
    }

    @Override
    @OperatingInfo(operation = "删除登录日志")
    public WebResult delById(Long id) {
        return super.delById(id);
    }

    @Override
    @OperatingInfo(operation = "保存登录日志")
    public WebResult addOrUpdate(@RequestBody LoginLogQuery po) {
        return super.addOrUpdate(po);
    }

    @Override
    @OperatingInfo(operation = "批量删除登录日志")
    public WebResult batchDel(@RequestBody List<Long> ids) {
        return super.batchDel(ids);
    }

    @Override
    @OperatingInfo(operation = "分页获取登录日志")
    public WebResult page(PageQuery<LoginLogQuery> query, LoginLogQuery po) {
        return super.page(query, po);
    }
}
