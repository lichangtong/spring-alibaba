package com.demo.alibaba.test;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: Circle
 * @Description: 子类
 * @Author: lichangtong
 * @Date: 2020-11-19 17:16
 */

public class Circle extends Sharp {
	public String name = "Circle";

	public Circle() {
		System.out.println("Circle is create");
	}

	public static void printType() {
		System.out.println("Circle" + " printType");
	}

	public void printName() {
		System.out.println("printName:" + name);
	}
}
