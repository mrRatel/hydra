package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.vo.WebResult;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.user.UserAdd;
import com.ratel.hydra.system.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("add")
    @ApiOperation("新增用户")
    public WebResult add(@RequestBody @Valid UserAdd add){
        service.add(add);
        return WebResultFactory.ok(null,null);
    }
}
