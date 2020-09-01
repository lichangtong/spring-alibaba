package com.alibaba.demo.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class FluidControlGlobalGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //        请求信息
        ServerHttpRequest request = exchange.getRequest();
        //        返回信息
        ServerHttpResponse response = exchange.getResponse();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1000;
    }
}
