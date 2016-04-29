package com.allyn.lives.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allyn.lives.R;

/**
 * Created by Administrator on 2016/3/22.
 */
public class MainFragment extends BaseFragment {

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        return v;
    }
//    @OnClick(R.id.button)
//    public void start(View v) {
//        mmyView.stopAnim();
//        mmyView.startAnim(4000);
//    }
}
