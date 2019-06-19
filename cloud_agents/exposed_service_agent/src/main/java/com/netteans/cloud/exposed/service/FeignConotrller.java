package com.netteans.cloud.exposed.service;

import com.netteans.cloud.exposed.service.config.RemoteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class FeignConotrller {
    private final static UUID INSTANCE_UUID = UUID.randomUUID();

    @Autowired
    private FeignService feignService;

    @Autowired
    private RemoteConfig remoteConfig;

    @RequestMapping(value = {"/id/{id}", "/id"}, method = {RequestMethod.GET, RequestMethod.DELETE})
    public Object remoteInstance(@PathVariable Integer id) {
        return feignService.byId(id);
    }

    @RequestMapping(value = {"/name/{name}", "/name"}, method = {RequestMethod.GET, RequestMethod.DELETE})
    public Object remoteInstance(@PathVariable String name) {
        return feignService.byName(name);
    }

    @GetMapping(value = "/instance")
    public Object instance() {
        return INSTANCE_UUID.toString() + " serve @ port " + remoteConfig.getPort() + " git: " + remoteConfig.getGit() + " ver: " + remoteConfig.getVersion();
    }

    @GetMapping(value = "/timeout/{time}")
    public Object timeoutTest(@PathVariable int time) {
        return feignService.timeout(time);
    }
}
