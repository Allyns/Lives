package com.allyn.lives.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.allyn.lives.R;
import com.allyn.lives.app.MainApp;
import com.allyn.lives.fragment.image.ImageClassifyFragment;
import com.allyn.lives.fragment.image.ImageListFragment;

/**
 * Created by apple on 16/6/11.
 */
public class ImageViewPagerAdapter extends FragmentPagerAdapter {

    String[] tab_title;

    public ImageViewPagerAdapter(FragmentManager manager) {
        super(manager);
        tab_title = MainApp.getContexts().getResources().getStringArray(R.array.image_classify_tab);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = ImageClassifyFragment.newInstance();
                break;
            default:
                fragment = ImageListFragment.newInstance();
                break;
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
