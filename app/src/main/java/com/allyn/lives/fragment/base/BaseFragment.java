package com.allyn.lives.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.allyn.lives.netwoarks.LiveApi;
import com.allyn.lives.netwoarks.LiveRetrofit;
import com.allyn.lives.utils.StatusBarCompat;

/**
 * Created by Administrator on 2016/4/6.
 */
public class BaseFragment extends Fragment {

    public static final LiveApi liveRetrofit = new LiveRetrofit().getliveService();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarCompat.setStatusBarColor(getActivity());
    }
}
