package com.allyn.lives.model;

import android.util.Log;

import com.allyn.lives.model.bean.ImageClassifyBean;
import com.allyn.lives.netwoarks.Invoking;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/22.
 */
public class ImageClassifyModel {

    public static void getdata(Subscriber<ImageClassifyBean> S) {
        Observable.create(new Observable.OnSubscribe<ImageClassifyBean>() {
            @Override
            public void call(final Subscriber<? super ImageClassifyBean> subscriber) {
                Invoking.imageClassifyRetrofit.getImageClassifyData()
                        .subscribe(new Action1<ImageClassifyBean>() {
                            @Override
                            public void call(ImageClassifyBean imageClassifyBean) {
                                subscriber.onNext(imageClassifyBean);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                subscriber.onError(throwable);
                            }
                        });
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(S);
    }
}
