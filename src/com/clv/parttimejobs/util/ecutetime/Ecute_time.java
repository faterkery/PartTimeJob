package com.clv.parttimejobs.util.ecutetime;

public class Ecute_time {

	public static String getTime(long time) {
		String str = "";
		time = time / 1000;
		int s = (int) (time % 60);
		int m = (int) (time / 60 % 60);
		int h = (int) (time / 3600);
		str = h + "–° ±" + m + "∑÷" + s + "√Î";
		return str;
	}
}
