package com.allyn.lives.app;

import android.app.Application;
import android.content.Context;
import android.test.mock.MockApplication;

import com.jude.beam.Beam;
import com.jude.utils.JUtils;

/**
 * Created by Administrator on 2016/3/25.
 */
public class MainApp extends Application {

    static MainApp mainApp;

    @Override
    public void onCreate() {
        mainApp = this;
        super.onCreate();
        JUtils.initialize(this);
        Beam.init(this);
    }


    public static MainApp getContexts() {
        return mainApp;
    }
}
