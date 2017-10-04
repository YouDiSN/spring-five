package com.demo.springfive.demo.core.route;

import com.demo.springfive.demo.Constants;
import com.demo.springfive.demo.core.filter.ReactiveFilter;
import com.demo.springfive.demo.core.handler.CoreHandler;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

/**
 * Created by xiwang on 10/4/17.
 * RouterFunctionBuilder
 */
class RouterFunctionBuilder {

    static RouterFunctionBuilder create(CoreHandler coreHandler) {
        return new RouterFunctionBuilder(coreHandler);
    }

    private final CoreHandler coreHandler;

    private RouterFunction<ServerResponse> router;

    private RouterFunctionBuilder(CoreHandler coreHandler) {
        this.coreHandler = coreHandler;
        init();
    }

    private void init() {
        this.router = RouterFunctions
                .route(contentType(APPLICATION_JSON_UTF8).and(HEAD("/")), coreHandler::defaultBadRequest)
                .andRoute(OPTIONS("/"), coreHandler::defaultBadRequest);
    }

    RouterFunctionBuilder filter(List<ReactiveFilter> filters) {
        this.router = Objects.requireNonNull(filters)
                .stream()
                .reduce(this.router, RouterFunction::filter, RouterFunction::and);
        return this;
    }

    RouterFunctionBuilder request(List<SubRoute> subRoutes) {
        this.router = Objects.requireNonNull(subRoutes)
                .stream()
                .map(subRoute -> {
                    String path = subRoute.getPath();
                    RouterFunction<ServerResponse> subRouterFunction = subRoute.getRouterFunction();
                    return nest(path(getFullPath(path)), subRouterFunction);
                })
                .reduce(this.router, RouterFunction::and);
        return this;
    }

    RouterFunction<ServerResponse> build() {
        return router;
    }

    private String getFullPath(String path) {
        return Constants.API + path;
    }
}
