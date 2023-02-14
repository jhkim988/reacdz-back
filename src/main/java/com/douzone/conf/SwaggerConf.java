package com.douzone.conf;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConf {
    private String version;
    private String title;
    private final String TITLE_FIX = "DZ project 04 API";

    @Bean
    public Docket apiV1() {
        version = "V1";
        title = TITLE_FIX + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.douzone"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(getApiInfo(title, version))
                .securitySchemes(Collections.singletonList(getApiKey()))
                .enable(true);
    }

    private ApiInfo getApiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "Swagger API Docs",
                version,
                "dz04.com",
                new Contact("dz04", "dz04.com", "an6791@naver.com"),
                "Licenses",
                "reacdz.com",
                new ArrayList<>()
        );
    }

    private ApiKey getApiKey () {
        return new ApiKey("jwtToken", "X-AUTH-TOKEN", "header");
    }
}