package com.demo.alibaba.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: MyFilter
 * @Description: 过滤body 数据
 * @Author: lichangtong
 * @Date: 2020-09-24 18:24
 */
@Component
@Slf4j
public class MyFilter implements GlobalFilter, Ordered {

    boolean bo = false;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerRequest serverRequest = new DefaultServerRequest(exchange);

        // mediaType
        MediaType mediaType = exchange.getRequest().getHeaders().getContentType();
        HttpMethod method = exchange.getRequest().getMethod();
        log.info("客户端请求方式：{}", method.name());


        if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {
            try {
                // read & modify body
                Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).flatMap(body -> {
                    if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {
                        Map<String, Object> bodyMap = decodeBody(body);
                        // TODO decrypt & auth
                        log.info("JSON 数据：{}", JSON.toJSONString(bodyMap));
                        bo = true;
                        return Mono.just(body);
                    }
                    return Mono.empty();
                });
                BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
                HttpHeaders headers = new HttpHeaders();
                headers.putAll(exchange.getRequest().getHeaders());
                headers.remove(HttpHeaders.CONTENT_LENGTH);
                CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
                return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
                    ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(exchange.getRequest()) {
                        @Override
                        public HttpHeaders getHeaders() {
                            long contentLength = headers.getContentLength();
                            HttpHeaders httpHeaders = new HttpHeaders();
                            httpHeaders.putAll(super.getHeaders());
                            if (contentLength > 0) {
                                httpHeaders.setContentLength(contentLength);
                            } else {
                                httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                            }
                            return httpHeaders;
                        }

                        @Override
                        public Flux<DataBuffer> getBody() {
                            return outputMessage.getBody();
                        }
                    };
//                if (bo) {
//                    Map<String, Object> bodyMap = new HashMap<>();
//                    bodyMap.put("key", "李四");
//                    byte[] bytes = new byte[0];
//                    try {
//                        bytes = new ObjectMapper().writeValueAsBytes(bodyMap);
//                    } catch (JsonProcessingException e) {
//                        e.printStackTrace();
//                    }
//                    ServerHttpResponse response = exchange.getResponse();
//                    response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//                    DataBuffer buffer = response.bufferFactory().wrap(bytes);
//                    return response.writeWith(Flux.just(buffer));
//
//                } else {
                    return chain.filter(exchange.mutate().request(decorator).build());
//                }
                }));
            } catch (Exception e) {
                return writeResponse(exchange, "不支持的请求方式:" + mediaType.toString() + " " + method.name());
            }

        } else {
            return writeResponse(exchange, "不支持的请求方式:" + mediaType.toString() + " " + method.name());
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Map<String, Object> decodeBody(String body) {
        return JSON.toJavaObject(JSON.parseObject(body), Map.class);
    }

    private String encodeBody(Map<String, Object> map) {
        return map.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
    }

    /**
     * 给前端写错误信息
     *
     * @param exchange
     * @param message
     * @return
     * @throws JsonProcessingException
     */
    private Mono<Void> writeResponse(ServerWebExchange exchange, String message) {
        try {
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("code", "400");
            bodyMap.put("message", message);
            byte[] bytes = new ObjectMapper().writeValueAsBytes(bodyMap);
            ServerHttpResponse response = exchange.getResponse();
            response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            return response.writeWith(Flux.just(response.bufferFactory().wrap(bytes)));
        } catch (Exception e) {
            log.error("");
            e.printStackTrace();
        }
        return null;
    }
}
