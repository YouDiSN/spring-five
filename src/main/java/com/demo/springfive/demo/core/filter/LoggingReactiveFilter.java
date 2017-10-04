package com.demo.springfive.demo.core.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by xiwang on 10/4/17.
 * LoggingReactiveFilter
 */
@Component
public class LoggingReactiveFilter implements ReactiveFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<ServerResponse> filter(ServerRequest request, HandlerFunction<ServerResponse> next) {
        LogInfoHolder holder = new LogInfoHolder(request);
        return next.handle(request)
                .doOnSuccessOrError((serverResponse, throwable) -> this.doLog(serverResponse, throwable, holder));
    }

    private void doLog(ServerResponse serverResponse, Throwable throwable, LogInfoHolder holder) {
        Optional.ofNullable(throwable).ifPresent(exception -> logger.error("{}", exception));
        String path = holder.path;
        long start = holder.start;
        long end = System.currentTimeMillis();
        String method = holder.method;
        List<MediaType> types = holder.mediaTypeList;
        String statusCode = serverResponse.statusCode().toString();
        Map body = holder.body;
        logger.debug("{}|{}|{}|{}|{}|{}|{}", statusCode, path, method, body, start, end, types);
    }

    private static class LogInfoHolder {
        private String path;
        private long start;
        private String method;
        private List<MediaType> mediaTypeList;
        private Map body;

        private LogInfoHolder(ServerRequest request) {
            this.path = request.path();
            this.start = System.currentTimeMillis();
            this.method = Optional.ofNullable(request.method())
                    .map(HttpMethod::name)
                    .orElse("Unknown");
            this.mediaTypeList = request.headers().accept();
            request.bodyToMono(Map.class).doOnSuccessOrError((map, throwable) -> this.body = map);
        }
    }

}
