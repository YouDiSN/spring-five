package com.demo.springfive.demo.core.route;

import com.demo.springfive.demo.core.filter.ReactiveFilter;
import com.demo.springfive.demo.core.handler.CoreHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by xiwang on 10/3/17.
 * DemoRouteConfiguration
 */
@Configuration
public class DemoRouteConfiguration {

    private final List<SubRoute> subRoutes;
    private final List<ReactiveFilter> filters;
    private final CoreHandler coreHandler;

    @Autowired
    public DemoRouteConfiguration(List<SubRoute> subRoutes, List<ReactiveFilter> filters, CoreHandler coreHandler) {
        this.subRoutes = subRoutes;
        this.filters = filters;
        this.coreHandler = coreHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctionBuilder.create(coreHandler)
                .request(subRoutes)
                .filter(filters)
                .build();
    }

}
