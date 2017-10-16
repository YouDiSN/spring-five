package com.demo.springfive.demo.human.service;

import com.demo.springfive.demo.human.domain.Human;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 *
 * @author xiwang
 * @date 10/1/17
 * IHumanService
 */
public interface IHumanService {

    /**
     * get human
     *
     * @param id human id
     * @return Mono Optional Human
     */
    Mono<Optional<Human>> getHumanById(String id);

}
