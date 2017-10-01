package com.demo.springfive.demo.repository;

import com.demo.springfive.demo.domain.Human;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by xiwang on 10/1/17.
 * HumanRepository
 */
public interface HumanRepository extends ReactiveMongoRepository<Human, String> {
}
