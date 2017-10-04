package com.demo.springfive.demo.core.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by xiwang on 10/4/17.
 * CoreHandler
 */
@Controller
public class CoreHandlerImpl implements CoreHandler {

    @Override
    public Mono<ServerResponse> defaultBadRequest(ServerRequest request) {
        return ServerResponse.status(HttpStatus.NOT_FOUND).build();
    }

}
