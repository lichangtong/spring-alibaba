package com.demo.alibaba;

import com.alibaba.fastjson.JSON;
import com.demo.alibaba.result.ApiResult;

import java.util.HashMap;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: Test
 * @Description:
 * @Author: lichangtong
 * @Date: 2020-11-05 11:24
 */

public class Test {
	public static void main(String[] args) {
/*        System.out.println(SystemCodeMessageEnum.SYSTEM_ERROR.getCode());
        System.out.println(SystemCodeMessageEnum.SYSTEM_ERROR.getMessage());

        SystemCodeMessageEnum.SYSTEM_DB_ERROR.getCode();

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("a", "字符串A");
        objectMap.put("b", "字符串b");
        objectMap.put("c", "字符串c");
        objectMap.put("d", "字符串D");
        System.out.println(JSON.toJSONString(new ResultResponse.Builder<>().success(objectMap).build()));*/

		ApiResult build = ApiResult.builder().code(10001).message("abcde").data(new HashMap<>()).build();
		System.out.println(JSON.toJSONString(build));

	}
}
