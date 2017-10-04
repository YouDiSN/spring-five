package com.demo.springfive.demo.core.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * Created by xiwang on 10/3/17.
 * DemoRouteConfiguration
 */
@Configuration
public class DemoRouteConfiguration {

    private final List<SubRoute> subRoutes;

    @Autowired
    public DemoRouteConfiguration(List<SubRoute> subRoutes) {
        this.subRoutes = subRoutes;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        RouterFunction<ServerResponse> router = RouterFunctions
                .route(RequestPredicates.HEAD("/"), this::defaultBadRequestHandler)
                .andRoute(RequestPredicates.OPTIONS("/"), this::defaultBadRequestHandler);
        return this.subRoutes.stream()
                .map(subRoute -> {
                    String path = subRoute.getPath();
                    RouterFunction<ServerResponse> subRouterFunction = subRoute.getRouterFunction();
                    return RouterFunctions.nest(RequestPredicates.path(path), subRouterFunction);
                })
                .reduce(router, RouterFunction::and);
    }

    private Mono<ServerResponse> defaultBadRequestHandler(ServerRequest request) {
        request.bodyToMono(Map.class).log();
        return ServerResponse.status(HttpStatus.NOT_FOUND).build();
    }

}
