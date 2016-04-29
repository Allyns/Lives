package com.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Base64;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.InputStream;

/**
 * author miekoz on 2016/3/15.
 * email  meikoz@126.com
 */
public class WebViewUtil {

    // Inject CSS method: read style.css from assets folder
    // Append stylesheet to document head
    @SuppressLint("SetJavaScriptEnabled")
    public static void injectCss(Context context, WebView webView, String filename) {
        try {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setDatabaseEnabled(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setSaveFormData(false);
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            webView.getSettings().setLoadWithOverviewMode(false);
            webView.getSettings().setUseWideViewPort(true);
            InputStream inputStream = context.getAssets().open(filename);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            webView.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    // Tell the browser to BASE64-decode the string into your script !!!
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style)" +
                    "})()");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
