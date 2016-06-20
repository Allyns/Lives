package com.allyn.lives.fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.activity.base.BaseActivity;
import com.allyn.lives.fragment.base.BaseFragment;
import com.allyn.lives.model.bean.TranslationBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;


/**
 * A simple {@link Fragment} subclass.
 */
public class TranslationFragment extends BaseFragment {


    public static TranslationFragment newInstance() {
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
        View v = inflater.inflate(R.layout.fragment_translation, container, false);
        ButterKnife.bind(this, v);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });
        return v;
    }

    public void getDate() {
        Subscription subscription = BaseActivity.liveRetrofit.getTranslationData("allynlive", "650717794", "data", "json", "1.1", mContent.getText().toString()).subscribe(new Action1<TranslationBean>() {
            @Override
            public void call(TranslationBean translationBean) {
                mMsg.setText(translationBean.getQuery());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Snackbar.make(mSubmit, "失败", Snackbar.LENGTH_SHORT).show();
            }
        });
        addSubscription(subscription);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
