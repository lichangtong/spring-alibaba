package com.demo.alibaba.test;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: Sharp
 * @Description: 父类
 * @Author: lichangtong
 * @Date: 2020-11-19 17:14
 */

public class Sharp {
	public String name = "sharp";

	public Sharp() {
		System.out.println("Sharp is create");
	}

	public static void printType() {
		System.out.println("sharp" + " printType");
	}

	public void printName() {
		System.out.println("printName:" + name);
	}
}
