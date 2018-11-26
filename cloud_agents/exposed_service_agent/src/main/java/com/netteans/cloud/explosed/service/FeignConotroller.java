package com.netteans.cloud.explosed.service;

import com.netteans.domain.DemoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class FeignConotroller {
    private final static UUID INSTANCE_UUID = UUID.randomUUID();

    @Autowired
    private FeignService remoteService;

    //    @Value("${server.port}")
    private String port;

    @Value("${test.version}")
    private String version;

    @RequestMapping(value = {"/get/{id}", "/get"}, method = {RequestMethod.GET, RequestMethod.DELETE})
    public DemoUser remoteInstance(@PathVariable Integer id) {
        DemoUser du = remoteService.inst(id);
        return du;
    }

    @GetMapping(value = "/instance")
    public Object instance() {
        return INSTANCE_UUID.toString() + " serve @ port " + port + " version: " + version;
    }
}
