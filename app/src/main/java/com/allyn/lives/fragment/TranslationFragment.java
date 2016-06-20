package com.allyn.lives.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.activity.DetailsActivity;
import com.allyn.lives.activity.base.BaseActivity;
import com.allyn.lives.fragment.base.BaseFragment;
import com.allyn.lives.model.bean.TranslationBean;
import com.facebook.stetho.common.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.observables.AsyncOnSubscribe;
import rx.schedulers.Schedulers;


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
    @Bind(R.id.newactivity)
    Button newactivity;
    @Bind(R.id.msg)
    TextView mMsg;
    @Bind(R.id.web_msg)
    TextView web_msg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_translation, container, false);
        ButterKnife.bind(this, v);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        newactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DetailsActivity.class));
            }
        });

        return v;
    }

    public void getData() {
        Observable.create(new Observable.OnSubscribe<TranslationBean>() {
            @Override
            public void call(final Subscriber<? super TranslationBean> subscriber) {
                Subscription subscription = BaseActivity.liveRetrofit.getTranslationData("allynlive", "650717794", "data", "json", "1.1", mContent.getText().toString()).subscribe(new Action1<TranslationBean>() {
                    @Override
                    public void call(TranslationBean translationBean) {
                        subscriber.onNext(translationBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Snackbar.make(mSubmit, "翻译失败" + throwable.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });
                addSubscription(subscription);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<TranslationBean>() {
                    @Override
                    public void onNext(TranslationBean translationBean) {
                        mMsg.setText(translationBean.getQuery() + "   翻译结果 :  " + translationBean.getTranslation() + "\n\n" + "网络译义"
                        );
                        for (TranslationBean.WebEntity webEntity : translationBean.getWeb()) {
                            web_msg.setText("原文：" + webEntity.getKey()  + "   译文：" + webEntity.getValue()+ "\n");
                        }
                    }

                    @Override
                    public void onCompleted() {
                        mMsg.setText("翻译中");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
