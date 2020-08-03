package com.netteans.example.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({ExampleConfig.class})
@EnableConfigurationProperties(ExampleConfigProperties.class)//target Properties
public class ExampleAutoConfiguration {

    @Autowired
    private ExampleConfigProperties exampleConfigProperties;

    @Bean
    @ConditionalOnMissingBean(ExampleConfig.class)
    @ConditionalOnProperty(prefix = "com.netteans", name = "enable", matchIfMissing = true)
    public ExampleConfig exampleConfig(ExampleConfigProperties exampleConfigProperties) {
        ExampleConfig exampleConfig = new ExampleConfig();
        exampleConfig.setName(exampleConfigProperties.getName());
        exampleConfig.setMail(exampleConfigProperties.getMail());
        exampleConfig.setPhone(exampleConfigProperties.getPhone());
        return exampleConfig;
    }
}
