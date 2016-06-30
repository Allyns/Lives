package com.allyn.lives.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {

    private final Binder binder = new LiteBinder();

    public MusicService() {

    }

    public class LiteBinder extends Binder {

       public  MusicService getService() {
            return MusicService.this;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String test() {
        return "调用service方法成功";
    }
}
