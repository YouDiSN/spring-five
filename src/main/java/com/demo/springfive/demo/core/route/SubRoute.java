package com.demo.springfive.demo.core.route;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Created by xiwang on 10/3/17.
 * DemoSubRoute
 */
public interface SubRoute {

    /**
     * for the restful api, should have the prefix url
     * @return nest path
     */
    String getPath();

    RouterFunction<ServerResponse> getRouterFunction();

}
