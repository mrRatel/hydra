package com.ratel.hydra.common.properties;

import lombok.Data;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Data
public class Swagger2Property {
    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String author;
    private String url;
    private String email;
    private String license;
    private String licenseUrl;
}
