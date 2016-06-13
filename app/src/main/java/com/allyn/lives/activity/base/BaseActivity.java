package com.allyn.lives.activity.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/3/29.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
//        StatusBarCompat.setStatusBarColor(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
