package com.ratel.hydra.system.service;

import com.ratel.hydra.common.properties.CaptchaProperty;
import com.wf.captcha.base.Captcha;

import javax.servlet.http.HttpServletResponse;

public interface CaptchaService {

    void setHeader(HttpServletResponse response, String type);

    Captcha createCaptcha(CaptchaProperty property);
}
