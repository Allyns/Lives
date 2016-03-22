package com.allyn.lives.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allyn.lives.R;

/**
 * Created by Administrator on 2016/3/22.
 */
public class TVFragment extends Fragment {

    public static TVFragment newInstance() {
        TVFragment fragment = new TVFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tv, container, false);
        return v;
    }
}
