package com.common;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * author miekoz on 2016/3/17.
 * email  meikoz@126.com
 */
public class EasyApp extends Application{
    public Gson gson;
    private static EasyApp ourInstance = new EasyApp();
    public static EasyApp getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        this.initGson();

        LogLevel logLevel;
        if (Config.DEBUG)logLevel =LogLevel.FULL;
        else logLevel=LogLevel.NONE;

        Logger.init().methodOffset(2).methodCount(2).logLevel(logLevel);

        //Secure, simple key-value storage for android
        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSqliteStorage(this))
                .build();
    }

    private void initGson() {
        this.gson = new GsonBuilder()
                .setDateFormat(Constant.GANK_DATA_FORMAT)
                .create();
    }
}
