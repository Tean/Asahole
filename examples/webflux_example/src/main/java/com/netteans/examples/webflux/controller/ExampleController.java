package com.netteans.examples.webflux.controller;

import com.netteans.examples.webflux.entity.ExpPub;
import com.netteans.examples.webflux.service.ExpService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ExampleController {

    @Autowired
    private ExpService expService;

    @PostMapping("/exp")
    Mono<Void> create(@RequestBody Publisher<ExpPub> expStream){
        return this.expService.save(expStream).then();
    }

    @GetMapping("/exp")
    Mono<String> get() {
        return this.expService.get().then();
    }

    @GetMapping("/plain")
    String getPlain() {
        return "get plain";
    }
}
