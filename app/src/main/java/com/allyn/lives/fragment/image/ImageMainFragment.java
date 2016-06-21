package com.allyn.lives.fragment.image;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allyn.lives.R;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageMainFragment extends Fragment {


    public ImageMainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_main, container, false);

    }

}
