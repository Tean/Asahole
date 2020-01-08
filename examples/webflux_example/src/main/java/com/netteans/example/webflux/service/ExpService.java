package com.netteans.example.webflux.service;

import com.netteans.example.webflux.entity.ExpPub;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ExpService<T> {
    private final Queue<T> tqueue = new LinkedBlockingQueue<>();

    @SuppressWarnings("unchecked")
    public Mono<Void> save(Publisher<ExpPub> expStream) {
        return Mono.from(expStream).then();
    }

    @SuppressWarnings("unchecked")
    public Mono<T> get() {
        return Mono.just((T) new Object());
    }
}
