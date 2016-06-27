package com.allyn.lives.model;

import com.allyn.lives.bean.BooksBean;
import com.allyn.lives.bean.BooksClassifyBean;
import com.allyn.lives.bean.BooksDetailBean;
import com.allyn.lives.netwoarks.Invoking;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/22.
 */
public class BooksModel {

    /***
     * @param S
     */
    public static void getDataClassify(Subscriber<BooksClassifyBean> S) {
        Observable.create(new Observable.OnSubscribe<BooksClassifyBean>() {
            @Override
            public void call(final Subscriber<? super BooksClassifyBean> subscriber) {
                Invoking.BooksClassifyRetrofit.getBooksClassifyData()
                        .subscribe(new Action1<BooksClassifyBean>() {
                            @Override
                            public void call(BooksClassifyBean imageClassifyBean) {
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
     * @param page
     * @param typeId
     * @param size
     * @param S
     */
    public static void getBooksList(final int page, final int size, final int typeId, Subscriber<BooksBean> S) {

        Observable.create(new Observable.OnSubscribe<BooksBean>() {
            @Override
            public void call(final Subscriber<? super BooksBean> subscriber) {
                Invoking.BooksClassifyRetrofit.getBooksList(page, size, typeId).subscribe(new Action1<BooksBean>() {
                    @Override
                    public void call(BooksBean imageBean) {
                        subscriber.onNext(imageBean);
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

    public static void getBooksDetails(final int id,Subscriber<BooksDetailBean> S) {

        Observable.create(new Observable.OnSubscribe<BooksDetailBean>() {
            @Override
            public void call(final Subscriber<? super BooksDetailBean> subscriber) {
                Invoking.BooksClassifyRetrofit.getBookDetails(id).subscribe(new Action1<BooksDetailBean>() {
                    @Override
                    public void call(BooksDetailBean imageBean) {
                        subscriber.onNext(imageBean);
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
