package com.ratel.hydra.system.controller;

import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.service.impl.RoleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@RestController
@RequestMapping("role")
public class RoleController extends BaseController<RoleServiceImpl,Role>{

}
