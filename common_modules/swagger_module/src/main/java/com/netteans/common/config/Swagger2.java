package com.netteans.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Tean on 2016/6/16.
 */

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Autowired
    IServiceConfig serviceConfig;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(serviceConfig.getPackageName()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(serviceConfig.getTitle())
                .description(serviceConfig.getDescription())
                .termsOfServiceUrl(serviceConfig.getTermsOfServiceUrl())
                .contact(new Contact(serviceConfig.getContractName(), serviceConfig.getContractUrl(), serviceConfig.getContractEmail()))
                .version(serviceConfig.getVersion())
                .build();
    }

}
