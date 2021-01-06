package com.demo.alibaba;


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

//		ApiResult build = ApiResult.builder().code(10001).message("abcde").data(new HashMap<>()).build();
//		System.out.println(JSON.toJSONString(build));
//		Integer ab = null;
//		String abcd = "";
//
//
//		System.out.println(ObjectUtils.isEmpty(ab));
//		System.out.println(ObjectUtils.isNull(ab));
//		System.out.println(ObjectUtils.isNull(abcd));
//		System.out.println(ObjectUtils.isEmpty(abcd));

		/*String a = "ab";
		String b = "aa";
		String c = "ac";

		System.out.println(maximum(a,b,c));
		System.out.println(maximum(10,11,10));*/
//		int [] arr = new int[5];
//		arr[0] = 1;
//		arr[1] = 7;
//		arr[2] = 3;
//		arr[3] = 6;
//		arr[4] = 5;
//		System.out.println(Arrays.binarySearch(arr, 2));
		/*String time = "023012";
		System.out.println(time.length());
		System.out.println(time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4, 6));*/

//		DateUtil.parse("20201207", "yyyyMMdd").toDateStr()
		speak(2);
	}

	public static void speak(int ok) {
		if (ok == 1) {
			System.out.println("ok");
		} else {
			if (ok == 2) {
				return;
			}
		}

		System.out.println("kkkkkkkkkkkkkkkkkk");
	}

	public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
		T max = x; // 假设x是初始最大值
		if (y.compareTo(max) > 0) {
			max = y; //y 更大
		}
		if (z.compareTo(max) > 0) {
			max = z; // 现在 z 更大
		}
		return max; // 返回最大对象
	}
}
