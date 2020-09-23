package com.demo.alibaba.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: MyHttpServletRequestWrapper
 * @Description: RequestBody 封装
 * @Author: lichangtong
 * @Date: 2020-09-23 12:10
 */
@Slf4j
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] requestBody = null;//用于将流保存下来

    public MyHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        try {
            requestBody = StreamUtils.copyToByteArray(request.getInputStream());
        } catch (IOException e) {
            log.error("读取requestBody 信息错误！！！");
            e.printStackTrace();
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (requestBody == null) {
            requestBody = new byte[0];
        }
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public void setReadListener(ReadListener listener) {
                // do nothing
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public boolean isFinished() {
                return false;
            }
        };
    }


}

