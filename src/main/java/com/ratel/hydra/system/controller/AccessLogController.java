package com.ratel.hydra.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.vo.WebResult;
import com.ratel.hydra.system.po.AccessLog;
import com.ratel.hydra.system.query.AccessLogAdd;
import com.ratel.hydra.system.query.AccessLogListQuery;
import com.ratel.hydra.system.service.AccessLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@RestController
@RequestMapping("/accessLog")
@Api(tags = "访问日志相关接口")
public class AccessLogController extends BaseController{

    @Autowired
    private AccessLogService service;

    @Valid
    @GetMapping("/list")
    @ApiOperation("获取访问记录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "ratel", required = true),
//            @ApiImplicitParam(name = "password", value = "密码", defaultValue = "1234", required = true)
//    }
//    )
    public WebResult list(Page<AccessLog> page, AccessLog accessLog) {
        return WebResultFactory.ok(service.list(page,accessLog), "获取访问记录成功");
    }

    @Valid
    @PostMapping("/add")
    @ApiOperation("新增访问记录")
    public WebResult add(@RequestBody AccessLogAdd add){
        service.add(add);
        return WebResultFactory.ok(null,"");
    }
}
