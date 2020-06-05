package com.ratel.hydra.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.vo.WebResult;
import com.ratel.hydra.system.po.LoginLog;
import com.ratel.hydra.system.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Slf4j
@RequestMapping("loginLog")
public class LoginLogController {

    @Autowired
    private LoginLogService service;

    @GetMapping("page")
    public WebResult page(Page<LoginLog> page){
        return WebResultFactory.ok(service.page(page));
    }
}
