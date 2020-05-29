package com.ratel.hydra.system.service.impl;

import com.ratel.hydra.common.constant.ImageType;
import com.ratel.hydra.common.properties.CaptchaProperty;
import com.ratel.hydra.system.service.CaptchaService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ratel
 * @date 2020-05-29
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    /**
     * @Description 设置响应头
     * @Author      ratel
     * @Date        2020-05-29
     * @param       response
     * @param       type
     * @return      void
     **/
    @Override
    public void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, ImageType.GIF)) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }

    @Override
    public Captcha createCaptcha(CaptchaProperty property) {
        Captcha captcha = null;
        if (StringUtils.equalsIgnoreCase(property.getType(), ImageType.GIF)) {
            captcha = new GifCaptcha(property.getWidth(), property.getHeight(), property.getLength());
        } else {
            captcha = new SpecCaptcha(property.getWidth(), property.getHeight(), property.getLength());
        }
        captcha.setCharType(property.getCharType());
        return captcha;
    }
}
