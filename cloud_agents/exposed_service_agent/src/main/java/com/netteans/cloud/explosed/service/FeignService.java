package com.netteans.cloud.explosed.service;

import com.netteans.domain.DemoUser;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-zookeeper", path = "/", fallbackFactory = FallBackMethodsFactory.class)
public interface FeignService {

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Object inst(@PathVariable("id") Integer id);

    @RequestMapping(value = "/timeout", method = RequestMethod.GET)
    Object timeout();
}

@Component
class FallBackMethodsFactory implements FallbackFactory<FeignService> {

    public Logger logger = LoggerFactory.getLogger(FallBackMethodsFactory.class);

    @Override
    public FeignService create(Throwable throwable) {
        return new FeignService() {

            @Override
            public ResponseEntity inst(Integer id) {
                logger.error("inst {} fallback", this);
                DemoUser demoUser = new DemoUser();
                demoUser.setEmail("timeout");
                demoUser.setPassword("timeout");
                demoUser.setName("timeout");
                return new ResponseEntity(demoUser, HttpStatus.GATEWAY_TIMEOUT);
            }

            @Override
            public ResponseEntity timeout() {
                logger.info("timeout {} fallback", this);
                return new ResponseEntity(Integer.MIN_VALUE, HttpStatus.GATEWAY_TIMEOUT);
            }
        };
    }
}