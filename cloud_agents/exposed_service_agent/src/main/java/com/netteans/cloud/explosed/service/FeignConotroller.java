package com.netteans.cloud.explosed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class FeignConotroller {
    private final static UUID INSTANCE_UUID = UUID.randomUUID();

    @Autowired
    private FeignService remoteService;

    @Value("${version}")
    private String version;

    @Value("${git.version}")
    private String git;

    @Value("${server.port}")
    private String port;

    @Value("${spring.profiles.active}")
    private String profiles;

    @RequestMapping(value = {"/get/{id}", "/get"}, method = {RequestMethod.GET, RequestMethod.DELETE})
    public Object remoteInstance(@PathVariable Integer id) {
        return remoteService.inst(id);
    }

    @GetMapping(value = "/instance")
    public Object instance() {
        return INSTANCE_UUID.toString() + " serve @ port " + port + " profiles: " + profiles + " git: " + git + " ver: " + version;
    }

    @GetMapping(value = "/timeout")
    public Object timeoutTest() {
        return remoteService.timeout();
    }
}
