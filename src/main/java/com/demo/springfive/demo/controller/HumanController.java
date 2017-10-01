package com.demo.springfive.demo.controller;

import com.demo.springfive.demo.Constants;
import com.demo.springfive.demo.domain.Human;
import com.demo.springfive.demo.service.IHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Created by xiwang on 10/1/17.
 * HumanController
 */
@RestController
@RequestMapping(value = Constants.API, produces = Constants.APPLICATION_JSON_LATEST)
public class HumanController {

    private final IHumanService humanService;

    @Autowired
    public HumanController(IHumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping(value = "/humans/{id}")
    public ResponseEntity<Mono<Human>> getHumanById(@PathVariable("id") String id) {
        Mono<Human> humanMono = humanService.getHumanById(id)
                .map(humanSignal -> humanSignal.map(human -> {
                    human.setName(human.getName().toUpperCase());
                    return human;
                }))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .cache();
        return ResponseEntity.ok(humanMono);
    }

}
