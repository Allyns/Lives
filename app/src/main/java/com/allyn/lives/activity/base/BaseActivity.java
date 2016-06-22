package com.allyn.lives.activity.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.allyn.lives.netwoarks.LiveApi;
import com.allyn.lives.netwoarks.LiveRetrofit;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/3/29.
 */
public class BaseActivity extends AppCompatActivity {




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
