package com.allyn.lives.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.allyn.lives.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TranslationFragment extends Fragment {


    public TranslationFragment newInstance() {
        TranslationFragment translationFragment = new TranslationFragment();
        return translationFragment;
    }

    @Bind(R.id.content)
    EditText mContent;
    @Bind(R.id.submit)
    Button mSubmit;
    @Bind(R.id.msg)
    TextView mMsg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.fragment_translation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
