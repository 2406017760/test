package com.example.myapplication_web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public String webUrl, webUrl1;
    public WebView webView;
    public FrameLayout flVideoContainer;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        flVideoContainer = findViewById(R.id.flVideoContainer);
        textView = findViewById(R.id.textView);
        Intent intent = this.getIntent();
        webUrl = intent.getStringExtra("url");
        if (webUrl.startsWith("www.")) {
            webUrl1 = "https://" + webUrl;
        } else if (webUrl.startsWith("https://")) {
            webUrl1 = webUrl;
        } else if (webUrl.startsWith("http://")) {
            webUrl1 = webUrl;
        } else {
            webUrl1 = "https://m.baidu.com/s?from=1012852q&word=" + webUrl + "&rsv_bp=0&ch=&tn=baidu&bar=&rsv_spt=3&ie=utf-8&rsv_sug3=3&rsv_sug=0&rsv_sug4=95&rsv_sug1=1&inputT=1001";
        }
        init();
//           Uri uri = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//        startActivity(intent);
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                // TODO: 2017-5-6 处理下载事件
                textView.setVisibility(View.VISIBLE);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                downloadByBrowser(url);
            }
        });


    }


    private void downloadByBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private void init() {
        webView = (WebView) findViewById(R.id.webview);
        //local load
//        webView.loadUrl("www.baidu.com");
        //web load
        webView.loadUrl(webUrl1);
        //覆盖webview默认通过第三方或者是系统浏览器打开网页的行为，使得网页在webview中打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, String url) {

                return false;

            }
            //webviewclient帮助webview去处理一些页面控制和请求通知
        });
        //设置启用javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportZoom(true);


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    setTitle("沙雕浏览器（不留痕迹）");
                    setProgressBarIndeterminateVisibility(false);
                    setProgressBarVisibility(false);
                } else {
                    setProgressBarIndeterminateVisibility(true);
                    setProgressBarVisibility(true);
                    setProgress(newProgress * 100);
                    setTitle("   等等嘛...都已经加载" + newProgress + "%了");
                }
            }


        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}