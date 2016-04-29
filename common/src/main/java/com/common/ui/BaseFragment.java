package com.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.api.OkHttpApi;

import butterknife.ButterKnife;

/**
 * author miekoz on 2016/3/16.
 * email  meikoz@126.com
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView ==null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
            ButterKnife.bind(this, rootView);

        ViewGroup parentView = (ViewGroup) rootView.getParent();
        if (parentView !=null) parentView.removeView(rootView);

        ButterKnife.bind(this, rootView);
        onInitData();
        return rootView;
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitData();

    public String getName() {
        return BaseFragment.class.getName();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        //取消请求
        OkHttpApi.cancelRequest(getName());
    }
}
