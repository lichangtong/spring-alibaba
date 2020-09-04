package com.alibaba.demo.filter;

import com.alibaba.fastjson.JSON;
import com.demo.alibaba.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class FluidControlGlobalGatewayFilter implements GlobalFilter, Ordered {
    /**
     * 去掉空格,换行和制表符
     *
     * @param str
     * @return
     */
    private static String formatStr(String str) {
        if (str != null && str.length() > 0) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            return m.replaceAll("");
        }
        return str;
    }

    /**
     * 读取body内容
     *
     * @param serverHttpRequest
     * @return
     */
    public static String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        //获取请求体
        StringBuilder sb = new StringBuilder();
        serverHttpRequest.getBody().subscribe(buffer -> {
            byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
            DataBufferUtils.release(buffer);
            String bodyString = new String(bytes, StandardCharsets.UTF_8);
            sb.append(bodyString);
        });
        return formatStr(sb.toString());
    }

    /**
    * @Param
    * @description   gateway直接信息返回
    * @author lichangtong
    * @date 2020/9/4 13:23
    * @return
    * @throws
    */

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        ServerHttpResponse httpResponse = exchange.getResponse();
        ServerHttpRequest httpRequest = exchange.getRequest();
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(400);
        apiResult.setMessage("错误");
        byte[] bits = JSON.toJSONString(apiResult).getBytes();

        httpResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        DataBuffer buffer = httpResponse.bufferFactory().wrap(bits);

        return httpResponse.writeWith(Mono.just(buffer));
        //        请求信息

       /* ServerHttpRequest request = exchange.getRequest();
        InetSocketAddress inetSocketAddress = request.getRemoteAddress();
        String hostAddress = inetSocketAddress.getAddress().getHostAddress();
        String hostName = inetSocketAddress.getHostName();
        log.info("请求地址IP,{}, hostName:{}", hostAddress, hostName);
        String methodValue = request.getMethodValue();
        log.info("method:{}", methodValue);


        String body = resolveBodyFromRequest(exchange.getRequest());
        System.out.println(body);
        log.info("body:{}",body);
        MultiValueMap<String, String> multiValueMap = request.getQueryParams();
        System.out.println(JSON.toJSONString(multiValueMap));
        //        返回信息
        ServerHttpResponse response = exchange.getResponse();
        return chain.filter(exchange);*/
    }

    @Override
    public int getOrder() {
        return -1000;
    }
}
