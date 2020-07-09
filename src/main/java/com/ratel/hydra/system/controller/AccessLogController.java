package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.pojo.WebResult;
import com.ratel.hydra.system.po.AccessLog;
import com.ratel.hydra.system.query.AccessLogAdd;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.query.accessLog.AccessLogQuery;
import com.ratel.hydra.system.service.AccessLogService;
import com.ratel.hydra.system.service.impl.AccessLogServiceImpl;
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
@RequestMapping("/accessLog")
@OperatingInfo(tag = "访问日志")
public class AccessLogController extends BaseController{
    @Autowired
    private AccessLogService accessLogService;

    @PostMapping("addOrUpdate")
    @OperatingInfo(operation = "保存访问日志")
    public WebResult addOrUpdate(@RequestBody AccessLogAdd po) {
        accessLogService.add(po);
        return WebResultFactory.ok("操作成功");
    }

    @GetMapping("page")
    @OperatingInfo(operation = "分页获取访问日志")
    public WebResult page(PageQuery<AccessLogQuery> query, AccessLogQuery po) {
        return WebResultFactory.ok(accessLogService.page(query.setQuery(po)));
    }
}
