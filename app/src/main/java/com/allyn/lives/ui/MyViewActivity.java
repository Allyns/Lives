package com.allyn.lives.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.allyn.lives.R;
import com.allyn.lives.view.MyView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyViewActivity extends AppCompatActivity {

    @Bind(R.id.myView)
    MyView mmyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        setContentView(R.layout.activity_my_view);
        mmyView.startAnim(1000);
    }
}
