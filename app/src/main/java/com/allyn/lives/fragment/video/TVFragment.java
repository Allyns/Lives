package com.allyn.lives.fragment.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allyn.lives.R;
import com.allyn.lives.fragment.base.BaseFragment;
import com.allyn.lives.view.MyView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/3/22.
 */
public class TVFragment extends BaseFragment {

    public static TVFragment newInstance() {
        TVFragment fragment = new TVFragment();
        return fragment;
    }

    @Bind(R.id.myView)
    MyView mmyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_my_view, container, false);
        ButterKnife.bind(this, v);
        mmyView.startAnim(4000);
        return v;
    }

    @OnClick(R.id.button)
    public void start(View v) {
        mmyView.stopAnim();
        mmyView.startAnim(4000);
    }
}
