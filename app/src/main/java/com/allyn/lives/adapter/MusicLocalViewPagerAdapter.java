package com.allyn.lives.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.allyn.lives.R;
import com.allyn.lives.app.MainApp;
import com.allyn.lives.fragment.music.local.MusicLocalLikeFragment;
import com.allyn.lives.fragment.music.local.MusicLocalListFragment;

/**
 * Created by apple on 16/6/11.
 */
public class MusicLocalViewPagerAdapter extends FragmentPagerAdapter {

    String[] tab_title;

    public MusicLocalViewPagerAdapter(FragmentManager manager) {
        super(manager);
        tab_title = MainApp.getContexts().getResources().getStringArray(R.array.local_music_tab);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = MusicLocalListFragment.newInstance();
                break;
            case 1:
                fragment = MusicLocalLikeFragment.newInstance();
                break;
//            case 2:
//                fragment = MusicLocalSingerFragment.newInstance();
//                break;
        }
        return fragment;

    }

    @Override
    public int getCount() {
        return tab_title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab_title[position];
    }
}
