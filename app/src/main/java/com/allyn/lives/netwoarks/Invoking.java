package com.allyn.lives.netwoarks;

/**
 * Created by Administrator on 2016/6/22.
 */
public class Invoking {

    public static final LiveApi translateRetrofit = new LiveRetrofit(IPConfig.TranslationApkUrl).getliveService();

    public static final LiveApi ImageClassifyRetrofit = new LiveRetrofit(IPConfig.ImageClassifyApiUrl).getliveService();

//    public static final LiveApi ImageClassList = new LiveRetrofit(IPConfig.ImageList).getliveService();

}
