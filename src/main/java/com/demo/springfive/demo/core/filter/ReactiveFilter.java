package com.demo.springfive.demo.core.filter;

import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Created by xiwang on 10/4/17.
 * ReactiveFilter
 */
public interface ReactiveFilter extends HandlerFilterFunction<ServerResponse, ServerResponse> {
}
