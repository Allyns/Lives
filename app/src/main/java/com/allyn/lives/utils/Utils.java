package com.allyn.lives.utils;

import android.content.Context;

/**
 * Created by C on 25/2/2016.
 * Nukc
 */
public class Utils {

    public static int convertDpToPixel(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}
