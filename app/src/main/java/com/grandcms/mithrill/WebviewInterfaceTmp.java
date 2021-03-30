package com.grandcms.mithrill;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class WebviewInterfaceTmp {

    WebView webView;

    public WebviewInterfaceTmp(WebView webView) {
        this.webView = webView;
    }

    @JavascriptInterface
    public void goBack() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            System.exit(0);
        }
    }
}
