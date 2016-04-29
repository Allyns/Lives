package com.common.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.common.R;

import butterknife.ButterKnife;

/**
 * author miekoz on 2016/3/15.
 * email  meikoz@126.com
 */
public abstract class BaseActivity extends FragmentActivity {

    protected EasyToolBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        this.onInitData();
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitData();

    protected void initTitleBar(String title) {
        findTitleBar();
        mTitleBar.setTitle(title);
    }

    private void findTitleBar() {
        mTitleBar = (EasyToolBar) findViewById(R.id.toolbar);
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
