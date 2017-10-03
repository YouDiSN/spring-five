package com.demo.springfive.demo.repository;

import com.demo.springfive.demo.domain.Human;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xiwang on 10/1/17.
 * HumanRepository
 */
@Repository
public interface HumanRepository extends ReactiveMongoRepository<Human, String> {
}
