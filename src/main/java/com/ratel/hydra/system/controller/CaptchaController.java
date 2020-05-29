package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.constant.ExceptionEnum;
import com.ratel.hydra.common.execption.SystemException;
import com.ratel.hydra.common.properties.CaptchaProperty;
import com.ratel.hydra.common.properties.HydraProperties;
import com.ratel.hydra.system.service.CaptchaService;
import com.wf.captcha.base.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ratel
 * @date 2020-05-29
 */
@Slf4j
@Controller
@RequestMapping("captcha")
public class CaptchaController {
    @Autowired
    private HydraProperties hydraProperties;
    @Autowired
    private CaptchaService service;

    @GetMapping("one")
    public void generateAuthCode(HttpServletRequest request, HttpServletResponse response){
        CaptchaProperty captchaProperty = hydraProperties.getCaptchaProperty();
        service.setHeader(response, captchaProperty.getType());
        Captcha captcha = service.createCaptcha(captchaProperty);
        HttpSession session = request.getSession();
        session.setAttribute(CaptchaProperty.CAPTCHA,captcha.text().toLowerCase());
        try {
            captcha.out(response.getOutputStream());
        } catch (Exception e) {
            log.error("返回验证码IO异常",e);
            throw new SystemException(ExceptionEnum.SYS1002);
        }
    }
}
