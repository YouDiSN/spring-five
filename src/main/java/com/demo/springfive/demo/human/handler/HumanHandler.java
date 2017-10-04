package com.demo.springfive.demo.human.handler;

import com.demo.springfive.demo.human.domain.Human;
import com.demo.springfive.demo.human.service.IHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Created by xiwang on 10/3/17.
 * HumanHandler
 */
@Controller
public class HumanHandler {

    private final IHumanService humanService;

    @Autowired
    public HumanHandler(IHumanService humanService) {
        this.humanService = humanService;
    }

    public Mono<ServerResponse> getHumanById(ServerRequest request) {
        String humanId = request.pathVariable("id");
        Mono<Human> humanMono = humanService.getHumanById(humanId)
                .filter(Optional::isPresent)
                .map(Optional::get);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(humanMono, Human.class);
    }

}
