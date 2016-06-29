package com.allyn.lives.app;

import android.app.Application;
import android.app.LauncherActivity;
import android.content.Context;
import android.test.mock.MockApplication;

import com.jude.beam.Beam;
import com.jude.utils.JUtils;
import com.litesuits.orm.LiteOrm;

/**
 * Created by Administrator on 2016/3/25.
 */
public class MainApp extends Application {

    static MainApp mainApp;
    LiteOrm liteOrm;

    @Override
    public void onCreate() {
        mainApp = this;
        super.onCreate();
        JUtils.initialize(this);
        Beam.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (liteOrm != null) {
            liteOrm.close();
        }
    }

    public static MainApp getContexts() {
        return mainApp;
    }

    public LiteOrm getLiteOrm() {
        if (liteOrm == null) {
            liteOrm = LiteOrm.newCascadeInstance(this, "live.db");
        }
        return liteOrm;
    }
}
