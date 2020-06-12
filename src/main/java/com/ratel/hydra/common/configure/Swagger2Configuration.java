package com.ratel.hydra.common.configure;

import com.ratel.hydra.common.properties.HydraProperties;
import com.ratel.hydra.common.properties.Swagger2Property;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Configuration
@EnableSwagger2
//@EnableWebMvc
public class Swagger2Configuration {

    @Autowired
    private HydraProperties properties;
    @Bean
    public Docket swaggerApi() {
        Swagger2Property swagger = properties.getSwagger2Property();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(swagger)).enable(true);
    }

    private ApiInfo apiInfo(Swagger2Property swagger) {
        return new ApiInfo(
                swagger.getTitle(),
                swagger.getDescription(),
                swagger.getVersion(),
                "",
                new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
                swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
    }
}
