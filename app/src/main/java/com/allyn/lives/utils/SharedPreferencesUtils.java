package com.allyn.lives.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by C on 17/3/2016.
 * https://github.com/nukc
 */
public class SharedPreferencesUtils {
    private static final String NAME = "uMarket";

    private static final String KEY_IS_FIRST_OPEN = "isFirstOpen";

    public static final String KEY_SMART_INSTALL ="smartInstall";
    public static final String KEY_WIFI_LOCK = "wifiLock";
    public static final String KEY_IS_IGNORE_UPDATER = "isIgnoreUpdate";

    private static void putBoolean(Context context, String key, boolean value){
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putBoolean(key, value);
        editor.apply();
    }

    private static boolean getBoolean(Context context, String key, boolean defValue){
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void setIsNotFirstOpen(Context context){
        putBoolean(context, KEY_IS_FIRST_OPEN, false);
    }

    public static boolean getIsFirstOpen(Context context){
        return getBoolean(context, KEY_IS_FIRST_OPEN, true);
    }

    public static void setSmartInstall(Context context, boolean isChecked){
        putBoolean(context, KEY_SMART_INSTALL, isChecked);
    }

    public static void setWifiLock(Context context, boolean isChecked){
        putBoolean(context, KEY_WIFI_LOCK, isChecked);
    }

    public static boolean getSmartInstall(Context context){
        return getBoolean(context, KEY_SMART_INSTALL, false);
    }

    public static boolean getWifiLock(Context context){
        return getBoolean(context, KEY_WIFI_LOCK, false);
    }

    public static void setIgnoreUpdate(Context context, boolean isChecked){
        putBoolean(context, KEY_IS_IGNORE_UPDATER, isChecked);
    }

    public static boolean getIsIgnoreUpdate(Context context){
        return getBoolean(context, KEY_IS_IGNORE_UPDATER, false);
    }
}
