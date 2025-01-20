package com.backend.testing;

public class A {
	public static A a() {
		System.out.println("execute mtd a");
		return new A();
		}

	public static A b() {
		System.out.println("execute mtd b");
		return new A();
	}

	public static B c() {
		System.out.println("execute mtd c");
		return new B();
	}

}
