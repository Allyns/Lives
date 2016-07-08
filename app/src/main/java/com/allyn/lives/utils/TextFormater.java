package com.allyn.lives.utils;

import android.annotation.SuppressLint;

import java.text.DecimalFormat;

public class TextFormater {

	@SuppressLint("DefaultLocale")
	public static String dataSizeFormat(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;

		if (size >= gb) {
			return String.format("%.1f GB", (float) size / gb);
		} else if (size >= mb) {
			float f = (float) size / mb;
			return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
		} else if (size >= kb) {
			float f = (float) size / kb;
			return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
		} else
			return String.format("%d B", size);
	}

	public static String numberFormat(long number) {
		DecimalFormat df = new DecimalFormat("0.0");

		if (number >= 10000){
			if (number % 10000 < 1000)
				return number / 10000 + "万次";
			return df.format((float) number / 10000) + "万次";
		}else if (number >= 100000000){
			if (number % 100000000 < 10000000)
				return number / 100000000 + "亿次";
			return df.format((float) number / 100000000) + "亿次";
		}else {
			return number + "次";
		}
	}

	public static String getCacheSize(long cacheSize){
		double size = (cacheSize + 0.00) / (1024 * 1024);

		DecimalFormat df = new DecimalFormat("0.00");
		String sSize = df.format(size);
		return sSize + " M";
	}
}
