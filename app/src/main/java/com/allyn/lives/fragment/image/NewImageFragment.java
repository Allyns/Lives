package com.allyn.lives.fragment.image;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allyn.lives.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewImageFragment extends Fragment {


    public static NewImageFragment newInstance() {
        NewImageFragment newImageFragment = new NewImageFragment();
        return newImageFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_image, container, false);
    }

}
