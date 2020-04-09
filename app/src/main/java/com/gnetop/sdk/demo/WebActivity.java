package com.gnetop.sdk.demo;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class WebActivity extends AppCompatActivity {

    WebView mWebView;
    WebSettings mSetting;
    String url = "http://192.168.196.82:5440/index.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web);
        initView();
    }

    private void initView() {
        mWebView = findViewById(R.id.web_View);
        initWebView(url);
    }


    /**
     * webview设置
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(String url) {
        mSetting = mWebView.getSettings();
        // 如果访问的页面中有Javascript，则WebView必须设置支持Javascript
        mSetting.setJavaScriptEnabled(true);
        mSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        mSetting.setSupportZoom(false); // 支持缩放
        mSetting.setBuiltInZoomControls(false); // 支持手势缩放
        mSetting.setDisplayZoomControls(false); // 是否显示缩放按钮

        // >= 19(SDK4.4)启动硬件加速，否则启动软件加速
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            mSetting.setLoadsImagesAutomatically(true); // 支持自动加载图片
        } else {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            mSetting.setLoadsImagesAutomatically(false);
        }

        mSetting.setUseWideViewPort(true); // 将图片调整到适合WebView的大小
        mSetting.setLoadWithOverviewMode(true); // 自适应屏幕
        mSetting.setDomStorageEnabled(true);
        mSetting.setSaveFormData(true);
        mSetting.setSupportMultipleWindows(true);
        mSetting.setAppCacheEnabled(true);
        mSetting.setCacheMode(WebSettings.LOAD_NO_CACHE); // 优先使用缓存

        mWebView.setHorizontalScrollbarOverlay(true);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setOverScrollMode(View.OVER_SCROLL_NEVER); // 取消WebView中滚动或拖动到顶部、底部时的阴影
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 取消滚动条白边效果
        mWebView.requestFocus();
        mWebView.loadUrl(url);

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mWebView.setLayerType(View.LAYER_TYPE_NONE, null);
                if (!mSetting.getLoadsImagesAutomatically()) {
                    mSetting.setLoadsImagesAutomatically(true);
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }
        });
    }
}
