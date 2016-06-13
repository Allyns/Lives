package com.allyn.lives.fragment.music.local;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allyn.lives.R;
import com.allyn.lives.adapter.MusicLocalViewPagerAdapter;
import com.allyn.lives.fragment.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by apple on 16/6/11.
 */
public class MusicLocalFragment extends BaseFragment {

    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;


    public static  MusicLocalFragment newInstance() {
        MusicLocalFragment localFragment = new MusicLocalFragment();
        return localFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.freagment_local_music_main, container, false);
        ButterKnife.bind(this, v);
        initView();
        return v;
    }

    private void initView() {
        MusicLocalViewPagerAdapter viewpageradapter = new MusicLocalViewPagerAdapter(getChildFragmentManager());
        mViewpager.setAdapter(viewpageradapter);
        mViewpager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewpager);
    }
}
