package com.allyn.lives.netwoarks;

import com.allyn.lives.app.MainApp;
import com.allyn.lives.model.bean.TranslationBean;
import com.allyn.lives.utils.NetworkUtils;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/6/17.
 */
public class LiveRetrofit {

    LiveApi liveApi;

    public LiveRetrofit(String Ip) {
        File cacheFile = new File(MainApp.getContexts().getExternalCacheDir(), "UMarketCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (NetworkUtils.isNetworkAvailable(MainApp.getContexts().getApplicationContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isNetworkAvailable(MainApp.getContexts().getApplicationContext())) {
                    int maxAge = 0 * 60;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        Retrofit.Builder retrofit = new Retrofit.Builder();
//        retrofit.client(client)
        retrofit.baseUrl(Ip)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        liveApi = retrofit.build().create(LiveApi.class);
    }

    public LiveApi getliveService() {
        return liveApi;
    }
}
