package com.common.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.common.R;
import com.common.utils.IntentUtils;
import com.common.utils.WebViewUtil;

/**
 * author miekoz on 2016/3/15.
 * email  meikoz@126.com
 */
public class WebViewActivity extends BaseActivity {

    private static final int PROGRESS_RATIO = 1000;
    ProgressBar webviewPb;
    WebView webview;
    EasyToolBar toolbar;

    public static void start(Context context, String url, String title, boolean isShow) {
        if (TextUtils.isEmpty(url)) throw new IllegalArgumentException("url must not be empty");
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    public static void start(Context context, String url, String title) {
        start(context, url, title, false);
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_webview;
    }

    @Override
    protected void onInitData() {
        webviewPb = (ProgressBar) this.findViewById(R.id.webview_pb);
        webview = (WebView) this.findViewById(R.id.webview);
        toolbar = (EasyToolBar) this.findViewById(R.id.toolbar);
        initTitleBar(TextUtils.isEmpty(getUrlTitle()) ? "" : getUrlTitle());
        this.enableJavascript();
        this.enableCaching();
        this.enableCustomClients();
        this.enableAdjust();
        this.zoomedOut();
        this.webview.loadUrl(this.getUrl());

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void enableJavascript() {
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }

    private void enableCaching() {
        this.webview.getSettings().setAppCachePath(getFilesDir() + getPackageName() + "/cache");
        this.webview.getSettings().setAppCacheEnabled(true);
        this.webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
    }

    private void enableAdjust() {
        this.webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        this.webview.getSettings().setLoadWithOverviewMode(true);
    }

    private void zoomedOut() {
        this.webview.getSettings().setLoadWithOverviewMode(true);
        this.webview.getSettings().setUseWideViewPort(true);
        this.webview.getSettings().setSupportZoom(true);
    }

    private String getUrl() {
        return IntentUtils.getStringExtra(this.getIntent(), "url");
    }

    private String getUrlTitle() {
        return IntentUtils.getStringExtra(this.getIntent(), "title");
    }

    private void enableCustomClients() {
        this.webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            /**
             * @param view The WebView that is initiating the callback.
             * @param url  The url of the page.
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url.contains("www.vmovier.com")) {
                    WebViewUtil.injectCss(WebViewActivity.this, WebViewActivity.this.webview, "vmovier.css");
                } else if (url.contains("video.weibo.com")) {
                    WebViewUtil.injectCss(WebViewActivity.this, WebViewActivity.this.webview, "weibo.css");
                } else if (url.contains("m.miaopai.com")) {
                    WebViewUtil.injectCss(WebViewActivity.this, WebViewActivity.this.webview, "miaopai.css");
                }
            }
        });
        this.webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                WebViewActivity.this.webviewPb.setProgress(progress);
                setProgress(progress * PROGRESS_RATIO);
                if (progress >= 80) {
                    WebViewActivity.this.webviewPb.setVisibility(View.GONE);
                } else {
                    WebViewActivity.this.webviewPb.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.webview != null) this.webview.destroy();
    }
}
