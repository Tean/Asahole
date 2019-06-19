package com.netteans.cloud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeforBootStrap implements InitializingBean {
    private final static Logger logger = LoggerFactory.getLogger(BeforBootStrap.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("before run");
    }
}
