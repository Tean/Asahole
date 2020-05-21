package com.netteans.examples.aop;

import com.netteans.common.config.IServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAspectJAutoProxy
@EnableSwagger2
@Configuration
public class Configurator {

    @Bean
    public IServiceConfig serviceConfig() {
        return new IServiceConfig() {
            private String termsOfServiceUrl;
            private String contractName;
            private String contractUrl;
            private String contractEmail;
            private String description;
            private String packageName = "com.netteans";
            private String title;
            private String version;
            private String host = "docs";

            @Override
            public String getTermsOfServiceUrl() {
                return termsOfServiceUrl;
            }

            @Override
            public String getContractName() {
                return contractName;
            }

            @Override
            public String getContractUrl() {
                return contractUrl;
            }

            @Override
            public String getContractEmail() {
                return contractEmail;
            }

            @Override
            public String getDescription() {
                return description;
            }

            @Override
            public String getPackageName() {
                return packageName;
            }

            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public String getVersion() {
                return version;
            }

            @Override
            public String getHost() {
                return host;
            }
        };
    }
}
