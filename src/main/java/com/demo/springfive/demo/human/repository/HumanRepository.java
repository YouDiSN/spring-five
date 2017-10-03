package com.demo.springfive.demo.human.repository;

import com.demo.springfive.demo.human.domain.Human;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xiwang on 10/1/17.
 * HumanRepository
 */
@Repository
public interface HumanRepository extends ReactiveMongoRepository<Human, String> {
}
