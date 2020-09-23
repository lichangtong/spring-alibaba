package com.demo.alibaba.filter;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.IOUtils;
import com.alibaba.fastjson.JSON;
import com.demo.alibaba.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: MyUrlFilter
 * @Description: filter
 * @Author: lichangtong
 * @Date: 2020-09-21 14:14
 */
@Component
@Slf4j
@RefreshScope
public class MyUrlFilter implements Filter {

    @Value("#{'${ignored.filter.path:}'.empty ? null : '${ignored.filter.path:}'.split(',')}")
    private List<String> ignoredFilterPath;

    @Autowired
    private RedissonClient redissonClient;

    private String redisKeyPrefix = "user:key:appId:%s:string";

    /**
     * 返回给前端数据
     *
     * @param response
     * @param json
     * @throws Exception
     */
    private void returnJson(ServletResponse response, String json) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            log.error("response error", e);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    private Map<String, Object> getRequestBody(HttpServletRequest request) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String body = IOUtils.read(reader);

            Map<String, Object> map = JSON.toJavaObject(JSON.parseObject(body), Map.class);
            System.out.println("返回json数据");
            System.out.println(body);
            System.out.println("结束");
            return map;
        } catch (Exception e) {
            System.out.println("错误信息");
        }
        return null;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyUrlFilter 初始化了！！！");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("token");
        String sign = request.getHeader("sign");
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        String md = HttpMethod.GET.name();
        log.info("客户端请求TOKEN：{}", token);
        log.info("客户端请求SIGN：{}", sign);
        log.info("请求方式：{} 请求地址：{}", method, requestUrl);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        if (CollectionUtils.isNotEmpty(ignoredFilterPath)) {
            if (ignoredFilterPath.contains(requestUrl)) {
                log.info("放开请求限制，不做权限校验：{}", requestUrl);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        } else if (multipartResolver.isMultipart(request)) {
            log.info("是否为文件上传request");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else if (HttpMethod.GET.name().equals(method)) {
            returnResult(servletResponse, "不支持的请求方式：" + method + ",服务只支持：" + HttpMethod.POST.name() + "方式", 400);
            return;
        } else {
            MyHttpServletRequestWrapper myHttp = new MyHttpServletRequestWrapper(request);
            //验签
            if (verifySignature(myHttp)) {
                filterChain.doFilter(myHttp, servletResponse);
            } else {
                returnResult(servletResponse, "签名验证失败，请确认签名是否正确", 400);
                return;
            }
        }
/*
        if (multipartResolver.isMultipart(request)) {
            log.info("是否为文件上传request");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.info("token：{}", token);
            log.info("sign：{}", sign);
            boolean conEqual = requestUrl.contentEquals("/user-server/user/query2");
            log.info("请求内容是否相等：{}", conEqual);
            if (requestUrl.endsWith("/user-server/user/query2")) {
                returnResult(servletResponse, request.getRequestURI() + " 不允许访问", 400);
                return;
            } else {

                MyHttpServletRequestWrapper myHttp = new MyHttpServletRequestWrapper(request);
                Map<String, Object> map = getRequestBody(myHttp);


                filterChain.doFilter(myHttp, servletResponse);
            }
        }*/
    }

    @Override
    public void destroy() {
        log.info("MyUrlFilter 过滤器销毁了！！！");
    }

    /**
     * 验证签名
     *
     * @param request
     * @return
     */
    private boolean verifySignature(HttpServletRequest request) {
        Map<String, Object> map = getRequestBody(request);
        if (map == null || map.size() <= 0) {
            return false;
        } else {
            /**
             * 升序
             */
            SortedMap<String, Object> temps = new TreeMap<>();
            temps.putAll(map);
            System.out.println(JSON.toJSONString(temps));
            String appId = temps.get("appId").toString();
            Object appIdKey = redissonClient.getBucket(String.format(redisKeyPrefix, appId)).get();
            return !Objects.isNull(appIdKey);
        }
    }

    /**
     * @author lichangtong
     * @version 1.0
     * @date 2020/9/23 15:05
     * @description filter中统一结果返回方法
     */

    private void returnResult(ServletResponse servletResponse, String message, int code) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(code);
        apiResult.setMessage(message);
        returnJson(servletResponse, JSON.toJSONString(apiResult));
    }
}
