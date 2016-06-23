package com.allyn.lives.model;

import android.util.Log;

import com.allyn.lives.bean.ImageBean;
import com.allyn.lives.bean.ImageClassifyBean;
import com.allyn.lives.netwoarks.Invoking;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/22.
 */
public class ImageModel {

    /***
     *
     * @param S
     */
    public static void getDataClassify(Subscriber<ImageClassifyBean> S) {
        Observable.create(new Observable.OnSubscribe<ImageClassifyBean>() {
            @Override
            public void call(final Subscriber<? super ImageClassifyBean> subscriber) {
                Invoking.ImageClassifyRetrofit.getImageClassifyData()
                        .subscribe(new Action1<ImageClassifyBean>() {
                            @Override
                            public void call(ImageClassifyBean imageClassifyBean) {
                                subscriber.onCompleted();
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


    /***
     *
     * @param page
     * @param typeId
     * @param size
     * @param S
     */
    public static void getImageList(final int page, final int typeId,final int size, Subscriber<ImageBean> S) {

        Observable.create(new Observable.OnSubscribe<ImageBean>() {
            @Override
            public void call(final Subscriber<? super ImageBean> subscriber) {
                Invoking.ImageClassifyRetrofit.getImageList(page, size, typeId).subscribe(new Action1<ImageBean>() {
                    @Override
                    public void call(ImageBean imageBean) {
                        subscriber.onCompleted();
                        subscriber.onNext(imageBean);
                        for (ImageBean.ListEntity listEntity : imageBean.getList()) {
                            Log.i("list----------->",listEntity.getName());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        subscriber.onError(throwable);
                    }
                });
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(S);

    }
}
