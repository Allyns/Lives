package com.allyn.lives.netwoarks;

import com.allyn.lives.model.bean.ImageClassifyBean;
import com.allyn.lives.model.bean.TranslationBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/17.
 */
public interface LiveApi {

    /****
     * 翻译结果
     *
     * @param keyfrom
     * @param key
     * @param type
     * @param doctype
     * @param version
     * @param q
     * @return
     */
    @GET("openapi.do")
    rx.Observable<TranslationBean> getTranslationData(@Query("keyfrom") String keyfrom, @Query("key") String key, @Query("type") String type, @Query("doctype") String doctype, @Query("version") String version, @Query("q") String q);

    /***
     * 图书分类
     *
     * @return
     */
    @GET("http://www.tngou.net/api/book/classify")
    Observable<ImageClassifyBean> getImageClassifyData();
}
