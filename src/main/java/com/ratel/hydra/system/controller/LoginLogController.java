package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.system.query.loginLog.LoginLogQuery;
import com.ratel.hydra.system.service.impl.LoginLogServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Slf4j
@RestController
@RequestMapping("loginLog")
@Api(tags = "登录日志")
@OperatingInfo(tag = "登录日志")
public class LoginLogController extends BaseController<LoginLogServiceImpl,LoginLogQuery> {

}
