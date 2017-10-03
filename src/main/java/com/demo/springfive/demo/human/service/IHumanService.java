package com.demo.springfive.demo.human.service;

import com.demo.springfive.demo.human.domain.Human;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Created by xiwang on 10/1/17.
 * IHumanService
 */
public interface IHumanService {

    Mono<Optional<Human>> getHumanById(String id);

}
