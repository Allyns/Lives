package com.common.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.common.R;

/**
 * author miekoz on 2016/3/18.
 * email  meikoz@126.com
 */
public abstract class ReplaceActivity extends BaseActivity {
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_hosting;
    }

    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, false);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment == null || fragment == getSupportFragmentManager().findFragmentById(R.id.container))
            return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    protected void onInitData() {

    }
}
