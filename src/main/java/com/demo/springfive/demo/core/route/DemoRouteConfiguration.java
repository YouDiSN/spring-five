package com.demo.springfive.demo.core.route;

import com.demo.springfive.demo.human.handler.HumanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.List;

/**
 * Created by xiwang on 10/3/17.
 * DemoRouteConfiguration
 */
@Configuration
public class DemoRouteConfiguration {

    private final List<SubRoute> subRoutes;
    private final HumanHandler humanHandler;

    @Autowired
    public DemoRouteConfiguration(List<SubRoute> subRoutes, HumanHandler humanHandler) {
        this.subRoutes = subRoutes;
        this.humanHandler = humanHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> init() {
        return RouterFunctions
                .route(RequestPredicates.GET("/humans/{id}"), humanHandler::getHumanById);
    }

}
