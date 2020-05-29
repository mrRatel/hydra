package com.ratel.hydra.common.properties;

import com.ratel.hydra.common.constant.ImageType;
import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * 验证码
 * @author ratel
 * @date 2020-05-29
 */
@Data
//@SpringBootConfiguration
//@PropertySource("classpath:props/captcha.properties")
//@ConfigurationProperties(prefix = "authcode")
//@EnableConfigurationProperties(CaptchaProperty.class)
public class CaptchaProperty {

    public static final String CAPTCHA = "captcha";
    /**
     * 验证码有效时间，单位秒
     */
    private Long time = 120L;
    /**
     * 验证码类型，可选值 png和 gif
     */
    private String type = ImageType.PNG;
    /**
     * 图片宽度，px
     */
    private Integer width = 130;
    /**
     * 图片高度，px
     */
    private Integer height = 48;
    /**
     * 验证码位数
     */
    private Integer length = 4;
    /**
     * 验证码值的类型
     * 1. 数字加字母
     * 2. 纯数字
     * 3. 纯字母
     */
    private Integer charType = 2;
}
