package com.allyn.lives.utils;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by C on 9/3/2016.
 * https://github.com/nukc
 * <p>
 * this is the middleman object
 */
public class RxBus {

    private static RxBus mInstance;
    private final Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

    public static RxBus getDefault() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }

        return mInstance;
    }

    public void post(Object o) {
        bus.onNext(o);
    }


    public <T extends Object> Observable<T> toObserverable(final Class<T> eventType) {
        return bus.filter(new Func1<Object, Boolean>() {
            @Override
            public Boolean call(Object o) {
                return eventType.isInstance(o);
            }
        }).cast(eventType);
    }



    //发送
//    RxBus.getDefault().post(new OnTransfeDatarEvent());


    //接收
//    RxBus.getDefault()
//            .toObserverable(OnTransfeDatarEvent.class)
//    .subscribe(new Action1<OnTransfeDatarEvent>() {
//        @Override
//        public void call(OnTransfeDatarEvent onInstallEvent) {
//            loadApps();
//        }
//    });


//    public class OnTransfeDatarEvent {
//
//        public  int amount;
//        public  String packageNamee;
//        public  boolean isInstall;
//
//        public int getAmount() {
//            return amount;
//        }
//
//        public void setAmount(int amount) {
//            this.amount = amount;
//        }
//
//        public String getPackageNamee() {
//            return packageNamee;
//        }
//
//        public void setPackageNamee(String packageNamee) {
//            this.packageNamee = packageNamee;
//        }
//
//        public boolean isInstall() {
//            return isInstall;
//        }
//
//        public void setInstall(boolean install) {
//            isInstall = install;
//        }
//    }
}
