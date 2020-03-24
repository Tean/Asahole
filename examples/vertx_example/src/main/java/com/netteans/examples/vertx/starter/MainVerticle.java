package com.netteans.vertx.example.starter;

import io.vertx.core.*;
import io.vertx.core.json.JsonObject;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!");
        }).listen(config().getInteger("http.port", 8080), http -> {
            if (http.succeeded()) {
                startFuture.complete();
                System.out.println(String.format("HTTP server started on http://localhost:%d", 8081));
            } else {
                startFuture.fail(http.cause());
            }
        });
    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(
                new MainVerticle(),
                new DeploymentOptions().setConfig(new JsonObject().put("http.port", 8081)),
                stringAsyncResult -> {
                    System.out.println(stringAsyncResult.toString());
                });
    }

}
