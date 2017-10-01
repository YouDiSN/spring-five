package com.demo.springfive.demo.service.impl;

import com.demo.springfive.demo.domain.Human;
import com.demo.springfive.demo.repository.HumanRepository;
import com.demo.springfive.demo.service.IHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Created by xiwang on 10/1/17.
 * HumanServiceImpl
 */
@Service
public class HumanServiceImpl implements IHumanService {

    private final HumanRepository humanRepository;

    @Autowired
    public HumanServiceImpl(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @Override
    public Mono<Optional<Human>> getHumanById(String id) {
        return humanRepository.findById(id)
                .cache()
                .map(Optional::ofNullable)
                .log("get human by id, id = " + id);
    }
}
