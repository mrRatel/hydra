package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.utils.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ratel
 * @date 2020-05-24
 */
@Controller
public class SystemController extends BaseController{

    @GetMapping("/")
    public String index(){
        return WebUtil.getRedirectUrl("/admin/index.html");
    }
}
