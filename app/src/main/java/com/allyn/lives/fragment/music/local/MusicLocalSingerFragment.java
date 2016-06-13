package com.allyn.lives.fragment.music.local;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allyn.lives.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicLocalSingerFragment extends Fragment {

    public static MusicLocalSingerFragment newInstance() {
        MusicLocalSingerFragment fragment = new MusicLocalSingerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_music_local_singer, container, false);
    }

}
