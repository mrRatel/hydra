package com.ratel.hydra.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author ratel
 * @date 2020-07-07
 */
@Data
@ConfigurationProperties(
        prefix = "hydra.shiro",
        ignoreUnknownFields = false
)
public class ShiroProperty {
    /** 不拦截的URL */
    private List<String> exclude;

    private String tokenName;

    private String rememberMeTokenName;
}
