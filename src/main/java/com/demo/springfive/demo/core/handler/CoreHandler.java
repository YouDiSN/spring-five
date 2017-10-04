package com.demo.springfive.demo.core.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by xiwang on 10/4/17.
 * CoreHandler
 */
public interface CoreHandler {

    Mono<ServerResponse> defaultBadRequest(ServerRequest request);

}
