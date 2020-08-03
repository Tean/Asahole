package com.netteans.example.webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class BootStrap {
    private static final Logger log = LoggerFactory.getLogger(BootStrap.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(BootStrap.class).banner((environment, sourceClass, out) -> {
            out.println(" ----        .        ---- ");
            out.println("|           / \\      |     ");
            out.println("|          /   \\      ---- ");
            out.println("|         /-----\\         |");
            out.println(" ----    /       \\    ---- ");
        }).web(WebApplicationType.REACTIVE).run(args);
    }

    @Bean
    HttpHandler httpHandler() {
        return RouterFunctions.toHttpHandler(
                RouterFunctions.route(RequestPredicates.GET("/lambdaexp"),
                        serverRequest -> ServerResponse.ok().body(BodyInserters.fromValue("done")))
        );
    }
}
