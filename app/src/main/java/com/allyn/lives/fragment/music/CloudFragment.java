package com.allyn.lives.fragment.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allyn.lives.R;
import com.allyn.lives.fragment.base.BaseFragment;

/**
 * Created by apple on 16/6/8.
 */
public class CloudFragment extends BaseFragment {

    public static CloudFragment newInstance() {
        CloudFragment fragment = new CloudFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        return v;
    }
}