package com.allyn.lives.model;

import com.allyn.lives.bean.TranslationBean;
import com.allyn.lives.netwoarks.Invoking;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 16/6/17.
 */
public class TranslationModel {
    public static void getData(final String content, Subscriber<TranslationBean> S) {
        Observable.create(new Observable.OnSubscribe<TranslationBean>() {
            @Override
            public void call(final Subscriber<? super TranslationBean> subscriber) {
                Invoking.translateRetrofit.getTranslationData("allynlive", "650717794", "data", "json", "1.1", content)
                        .subscribe(new Action1<TranslationBean>() {
                            @Override
                            public void call(TranslationBean translationBean) {
                                subscriber.onCompleted();
                                subscriber.onNext(translationBean);
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
