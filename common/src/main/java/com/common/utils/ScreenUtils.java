package com.common.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class ScreenUtils {

	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static int dp2px(Context context, int dp) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return (int) ((dp * displayMetrics.density) + 0.5);
	}

	public static int dp2px(Context context, double dp) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return (int) ((dp * displayMetrics.density) + 0.5);
	}

	public static int getScreenWidth(Context context){
		DisplayMetrics dm = new DisplayMetrics();
		//((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		display.getMetrics(dm);

		return dm.widthPixels;
	}

	/**
	 * 获取屏幕的高
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context){
		return context.getResources().getDisplayMetrics().heightPixels;
	}
}
