package com.ratel.hydra.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ratel
 * @date 2020-05-21
 */

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:props/hydra.properties"})
@ConfigurationProperties(prefix = "hydra")
//@EnableConfigurationProperties(HydraProperties.class)
public class HydraProperties {
    private Swagger2Property swagger2Property = new Swagger2Property();
    private CaptchaProperty captchaProperty = new CaptchaProperty();
}
