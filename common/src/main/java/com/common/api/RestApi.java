package com.common.api;

import com.common.Constant;
import com.common.EasyApp;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * author miekoz on 2016/3/17.
 * email  meikoz@126.com
 */
public class RestApi {

    private static RestApi mInstance;
    private Retrofit retrofit;

    public static RestApi getIns(){
        if (mInstance == null){
            synchronized (RestApi.class){
                if (mInstance == null) mInstance = new RestApi();
            }
        }
        return mInstance;
    }

    public RestApi(){

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(7676, TimeUnit.MILLISECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.interceptors().add(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(EasyApp.getInstance().gson))
                .client(okHttpClient)
                .build();
    }

    public  <T> T createService(Class<T> clz){
        return retrofit.create(clz);
    }
}
