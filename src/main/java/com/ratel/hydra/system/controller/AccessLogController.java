package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.system.po.AccessLog;
import com.ratel.hydra.system.query.accessLog.AccessLogQuery;
import com.ratel.hydra.system.service.impl.AccessLogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@RestController
@RequestMapping("/accessLog")
@OperatingInfo(tag = "访问日志")
public class AccessLogController extends BaseController<AccessLogServiceImpl, AccessLogQuery>{


}
