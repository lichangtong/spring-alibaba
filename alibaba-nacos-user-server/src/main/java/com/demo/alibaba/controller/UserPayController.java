package com.demo.alibaba.controller;

import com.demo.alibaba.request.UserType;
import com.demo.alibaba.result.ApiResult;
import com.demo.alibaba.service.IUserPayService;
import com.demo.alibaba.service.UserPayServiceStrategyFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.List;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: UserPayController
 * @Description: 支付统一处理类
 * @Author: lichangtong
 * @Date: 2020-09-23 10:26
 */

@RestController
@RequestMapping("/user/pay")
public class UserPayController {
    /**
     * @return
     * @throws
     * @author lichangtong
     * @version 1.0
     * @date 2020/9/23 10:30
     * @description 下单接口
     */

    @PostMapping("/v1/create")
    public ApiResult createOrder(@RequestBody UserType userType) {
        IUserPayService userPayService = UserPayServiceStrategyFactory.getUserPayService(userType.getUserType());
        userPayService.unifiedOrder();
        userPayService.queryOrderStatus();
        userPayService.queryPayType();
        return new ApiResult();
    }

    /**
     * @return
     * @throws
     * @author lichangtong
     * @version 1.0
     * @date 2020/9/23 10:30
     * @description 查询订单状态接口
     */

    @PostMapping("/v1/query")
    public ApiResult queryOrder(String userType) {
        IUserPayService userPayService = UserPayServiceStrategyFactory.getUserPayService(userType);
        userPayService.queryOrderStatus();
        return new ApiResult();
    }

    @PostMapping("/v1/upload")
    public ApiResult upload(MultipartRequest request) {
        List<MultipartFile> files = request.getFiles("files");
        for (MultipartFile file : files) {
            System.out.println(file.getOriginalFilename());
        }
        return new ApiResult();
    }
}
