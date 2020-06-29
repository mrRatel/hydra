package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.common.vo.WebResult;
import com.ratel.hydra.system.po.AccessLog;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.query.accessLog.AccessLogQuery;
import com.ratel.hydra.system.service.impl.AccessLogServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
public class AccessLogController extends BaseController<AccessLogServiceImpl, AccessLogQuery>{

    @Override
    @OperatingInfo(operation = "获取访问日志")
    public WebResult getById(Long id) {
        return super.getById(id);
    }

    @Override
    @OperatingInfo(operation = "删除访问日志")
    public WebResult delById(Long id) {
        return super.delById(id);
    }

    @Override
    @OperatingInfo(operation = "保存访问日志")
    public WebResult addOrUpdate(AccessLogQuery po) {
        return super.addOrUpdate(po);
    }

    @Override
    @OperatingInfo(operation = "批量删除访问日志")
    public WebResult batchDel(List<Long> ids) {
        return super.batchDel(ids);
    }

    @Override
    @OperatingInfo(operation = "分页获取访问日志")
    public WebResult page(PageQuery<AccessLogQuery> query, AccessLogQuery po) {
        return super.page(query, po);
    }
}
