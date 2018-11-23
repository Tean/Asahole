package com.netteans.cloud.explosed.service;

import com.netteans.domain.DemoUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-zookeeper", path = "/", fallback = FallBackMethods.class)
public interface FeignService {

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    DemoUser inst(@PathVariable("id") Integer id);
}

class FallBackMethods {

}