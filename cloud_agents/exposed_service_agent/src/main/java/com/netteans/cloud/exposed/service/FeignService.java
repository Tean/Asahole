package com.netteans.cloud.exposed.service;

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

@FeignClient(value = "appback", path = "/", fallbackFactory = FallBackMethodsFactory.class)
public interface FeignService {

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Object byId(@PathVariable("id") Integer id);

    @RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
    Object byName(@PathVariable("name") String name);

    @RequestMapping(value = "/timeout/{time}", method = RequestMethod.GET)
    Object timeout(@PathVariable("time") int time);
}

@Component
class FallBackMethodsFactory implements FallbackFactory<FeignService> {

    private Logger logger = LoggerFactory.getLogger(FallBackMethodsFactory.class);

    @Override
    public FeignService create(Throwable throwable) {
        DemoUser demoUser = new DemoUser();
        demoUser.setEmail("timeout");
        demoUser.setPassword("timeout");
        demoUser.setName("timeout");
        demoUser.setServiceInstance("timeout");
        return new FeignService() {

            @Override
            public ResponseEntity byId(Integer id) {
                logger.error("inst {} fallback", this);
                return new ResponseEntity(demoUser, HttpStatus.GATEWAY_TIMEOUT);
            }

            @Override
            public Object byName(String name) {
                logger.error("inst {} fallback", this);
                return new ResponseEntity(demoUser, HttpStatus.GATEWAY_TIMEOUT);
            }

            @Override
            public ResponseEntity timeout(int time) {
                logger.info("timeout {} fallback", this);
                return new ResponseEntity("quest: " + time, HttpStatus.GATEWAY_TIMEOUT);
            }
        };
    }
}