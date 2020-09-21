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
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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
public class MyUrlFilter implements javax.servlet.Filter {

    @Value("#{'${ignored.filter.path:}'.empty ? null : '${ignored.filter.path:}'.split(',')}")
    private List<String> ignoredFilterPath;

    @Autowired
    private RedissonClient redissonClient;

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
        log.info("请求方法method:{}", method);
        if (CollectionUtils.isNotEmpty(ignoredFilterPath)) {
            boolean bo = ignoredFilterPath.contains(requestUrl);
            if (bo) {
                log.info("放开请求限制，不做权限校验：{}", requestUrl);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }


        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        if (multipartResolver.isMultipart(request)) {
            log.info("是否为文件上传request");
            MultipartHttpServletRequest multiRequest = multipartResolver.resolveMultipart(request);
            List<MultipartFile> multipartFiles = multiRequest.getFiles("files");
            for (MultipartFile item : multipartFiles) {
                log.info("原始文件名:{}", item.getOriginalFilename());
                log.info("文件大小:{}", item.getBytes().length + "B");
                item.transferTo(new File("D:\\" + item.getOriginalFilename()));
            }
        } else {
            log.info("token：{}", token);
            log.info("sign：{}", sign);
            boolean conEqual = requestUrl.contentEquals("/user-server/user/query2");
            log.info("请求内容是否相等：{}", conEqual);
            if (requestUrl.endsWith("/user-server/user/query2")) {
                ApiResult apiResult = new ApiResult();
                apiResult.setCode(400);
                apiResult.setMessage(request.getRequestURI() + " 不允许访问");
                returnJson(servletResponse, JSON.toJSONString(apiResult));
                return;
            } else {
                Map<String, Object> map = getRequestBody(request);
                SortedMap<String, Object> temps = new TreeMap<>();//升序
                temps.putAll(map);
                System.out.println(JSON.toJSONString(temps));
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {
        log.info("MyUrlFilter 过滤器销毁了！！！");
    }
}
