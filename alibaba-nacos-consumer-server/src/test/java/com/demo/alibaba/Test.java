package com.demo.alibaba;


import java.util.*;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: Test
 * @Description: test
 * @Author: lichangtong
 * @Date: 2020-10-22 10:16
 */

public class Test {
    public static void main(String[] args) {
        System.out.println("2020-10-22 00:33:33".substring(0, 10));
//        int i = 0;
//        System.out.println(++i);
//        System.out.println(i);
/*        System.out.println(DateUtil.parse("20201022").toDateStr());
        System.out.println(DateUtil.parse("20200831 2:42","yyyyMMdd HH:mm").toString());
        System.out.println(DateUtil.offsetMinute(new Date(),-15));*/
        testSign();
    }

    public static void testSign() {
//        String timestamp = String.valueOf(System.currentTimeMillis());
//        String signKey = "8a3c2551b75bde07";
//        String appId="302";
//        String noncestr=genRandomNum();
//
//        System.out.println("X-Auth-Ts:" + timestamp);
//        System.out.println("X-Auth-Noncestr:" + noncestr);
//        Map<String, Object> req = new HashMap<String, Object>();
//
//        //加密参数
//        req.put("x-auth-appid", appId);
//        req.put("x-auth-ts", timestamp);
//        req.put("x-auth-noncestr", noncestr);
//
//        List<String> ignoreList = new ArrayList<String>();
//        ignoreList.add("sign");
//        ignoreList.add("t");//H5附加在URL后面的时间戳，不参与计算签名
//
//        String signParams = toSortedSequence(req, ignoreList);
//        signParams=signParams.substring(0,signParams.length()-1)+signKey;
//        String mySign = Digests.md5(signParams);
//        System.out.println("X-Auth-Sign:" + mySign.substring(5,15));
    }


    public static String genRandomNum() {
        int maxNum = 36;
        int i;
        int count = 0;
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer();
        Random r = new Random();
        while (count < 8) {
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    /**
     * 序列化规则，按字段值首字母升序排列，字段名字段值首位相连(去空格)
     *
     * @param params     原参数
     * @param ignoreKeys 忽略的参数
     * @return 返回字符串
     */
    public static String toSortedSequence(Map<String, Object> params, List<String> ignoreKeys) {
        List<String> keyNames = generalFormSequence(params, ignoreKeys);
        Collections.sort(keyNames);

        StringBuilder buffer = new StringBuilder();
        /*for (String keyName : keyNames) {
            Object value = params.get(keyName);
            if (StringUtils.isNotBlank(value)) {
                buffer.append(keyName).append("=").append(value.toString().trim()).append("&");
            }
        }*/
        return buffer.toString();
    }

    /**
     * 返回一个Map.key的序列
     *
     * @param map        map
     * @param ignoreKeys 忽略的key
     * @return 返回的key list
     */
    private static List<String> generalFormSequence(Map<String, Object> map, List<String> ignoreKeys) {
        List<String> keyNames = new ArrayList<>();
        for (String keyName : map.keySet()) {
            if (ignoreKeys != null && ignoreKeys.contains(keyName)) {
                continue;
            }
            keyNames.add(keyName);
        }
        return keyNames;
    }
}
