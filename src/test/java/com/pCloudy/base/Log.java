package com.pCloudy.base;

public class Log extends UserBaseTest{

	public static boolean isPrint = false;
	
	public static void printMust(String message) {
		System.out.println(message);
	}
	
	public static void printDebug(String message) {
		if(isPrint) {
			System.out.println(message);
		}
	}
	
}
