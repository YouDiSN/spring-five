package com.demo.springfive.demo.human.route;

import com.demo.springfive.demo.core.route.SubRoute;
import com.demo.springfive.demo.human.handler.HumanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Created by xiwang on 10/3/17.
 * HumanRoute
 */
@Component
public class HumanRoute implements SubRoute {

    private final HumanHandler humanHandler;

    @Autowired
    public HumanRoute(HumanHandler humanHandler) {
        this.humanHandler = humanHandler;
    }

    @Override
    public String getPath() {
        return "/humans";
    }

    @Override
    public RouterFunction<ServerResponse> getRouterFunction() {
        return RouterFunctions
                .route(RequestPredicates.GET("/{id}"), humanHandler::getHumanById);
    }
}
