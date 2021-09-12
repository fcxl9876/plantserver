package com.jeesite.modules.util;

public class MyTimeFormat {
	public static String myFormat(int count) {
		int s = count*5;
		int m =  s/60;
		int ss = (s%3600)%60;
		return m+"分"+ss+"秒";
	}
}