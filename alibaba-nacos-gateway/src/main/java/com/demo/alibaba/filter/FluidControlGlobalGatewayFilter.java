package com.demo.alibaba.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class FluidControlGlobalGatewayFilter implements GlobalFilter, Ordered {
    /**
     * @return
     * @throws
     * @Param
     * @description gateway直接信息返回
     * @author lichangtong
     * @date 2020/9/4 13:23
     */

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        List<String> tokens = exchange.getRequest().getHeaders().get("Token");
        if (!CollectionUtils.isEmpty(tokens) && tokens.size() > 0) {
            String token = tokens.get(0);
            log.info("token:{}", token);
            if (StringUtils.isNoneEmpty(token)) {

            } else {
                Map<String, Object> bodyMap = new HashMap<>();
                bodyMap.put("key", "李四");
                byte[] bytes = null;
                try {
                    bytes = new ObjectMapper().writeValueAsBytes(bodyMap);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
                DataBuffer buffer = response.bufferFactory().wrap(bytes);
                return response.writeWith(Flux.just(buffer));
            }
        }
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return -1000;
    }
}
