package com.allyn.lives.view.widgets;

import android.content.Context;
import android.text.TextUtils;

import java.util.Date;

/**
 * 记录列表上次更新时间Utils
 * Created by liukun on 15/4/3.
 */
public class CommonRefreshDateUtils {
    /**
     * 获取当前使用View的class的最后更新时间
     *
     * @param currentClassName 当前调用到的className
     * @param mContext
     * @return
     */
    public synchronized static String getLastRefreshDate(Context mContext, String currentClassName) {
        String lastRefreshDate = mContext.getSharedPreferences("COMMONLIST", Context.MODE_PRIVATE).getString(currentClassName,"");
        if (TextUtils.isEmpty(lastRefreshDate)) {
            return "刚刚";
        }
        return CommonDateUtils.getDate(lastRefreshDate, new Date());
    }

    /**
     * 保存最后刷新时间
     *
     * @param mContext
     * @param currentClassName
     */
    public synchronized static void saveLastRefreshDate(Context mContext, String currentClassName) {
        mContext.getSharedPreferences("COMMONLIST", Context.MODE_PRIVATE).edit().putString(currentClassName, CommonDateUtils.getSimpleDateFormatDefault().format(new Date()));
    }
}
