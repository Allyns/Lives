package com.allyn.lives.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.allyn.lives.R;
import com.allyn.lives.activity.ConstMainActivity;
import com.allyn.lives.bean.AlbumBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Bind(R.id.button)
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, v);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), ConstMainActivity.class));
//                Ttest("allyn",123456,"hahahha",0.22);
                Objecttest("allyn",123456,"hahahha",0.22);
            }
        });
        return v;
    }
    //泛型测试
    public <T> void  Ttest(T... pag){
        Toast.makeText(getActivity(), pag[0]+"", Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), pag[1]+"", Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), pag[2]+"", Toast.LENGTH_SHORT).show();
        for (T t : pag) {
            Toast.makeText(getActivity(), t+"", Toast.LENGTH_SHORT).show();
        }
    }
    public  void  Objecttest(Object... pag){
        Toast.makeText(getActivity(), pag[0]+"", Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), pag[1]+"", Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), pag[2]+"", Toast.LENGTH_SHORT).show();
        for (Object t : pag) {
            Toast.makeText(getActivity(), t+"", Toast.LENGTH_SHORT).show();
        }
    }

}
