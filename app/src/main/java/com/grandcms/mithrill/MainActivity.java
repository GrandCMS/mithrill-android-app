package com.grandcms.mithrill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    Context context;

    String[] exceptions = {
            "html/szabalykonyv.html"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        //walk("html");

        WebView myWebView = findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!inExceptions(url)) {

                    url = url.replace("file:///android_asset/", "");
                    String html = readFileAsString("html/header.html") + readFileAsString(url) + readFileAsString("html/footer.html");
                    view.loadDataWithBaseURL("file:///android_asset/html/", html , "text/html", "UTF-8", null);

                    return true;
                }
                return false;
            }
        });

        initWebSetting(myWebView);
        myWebView.addJavascriptInterface(new WebviewInterface(), "Android");

        //myWebView.loadUrl("file:///android_asset/html/index.html");

        String html = readFileAsString("html/header.html") + readFileAsString("html/index.html") + readFileAsString("html/footer.html");
        myWebView.loadDataWithBaseURL("file:///android_asset/html/", html , "text/html", "UTF-8", null);
    }

    private boolean inExceptions(String file) {

        for(int i=0;i<exceptions.length;i++) {
            if (file.endsWith(exceptions[i])) return true;
        }

        return false;
    }

    public String loadFile(String file) {

        String html = readFileAsString(file);
        Pattern ptrn = Pattern.compile("<import>(\\S+)</import>");
        Matcher mtchr = ptrn.matcher(html);
        while (mtchr.find()) {
            html = html.replace("<import>"+mtchr.group(1)+"</import>", readFileAsString("html/"+mtchr.group(1)));
        }

        return html;
    }

    public String readFileAsString(String file) {
        String data = "";

        try {
            InputStream stream = getAssets().open(file);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            data = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return data;
    }

    // Commonly used WebViewSetting
    public static void initWebSetting(WebView webView) {

        WebSettings setting = webView.getSettings();

        /* Suggested settings for webview */

        setting.setJavaScriptEnabled(true);
        setting.setAllowFileAccess(true);
        setting.setAllowFileAccessFromFileURLs(true);
        setting.setAllowUniversalAccessFromFileURLs(true);
        setting.setAppCacheEnabled(true);
        setting.setDatabaseEnabled(true);
        setting.setDomStorageEnabled(true);
        setting.setCacheMode(WebSettings.LOAD_DEFAULT);
        setting.setAppCachePath(webView.getContext().getCacheDir().getAbsolutePath());
        setting.setUseWideViewPort(false);
        setting.setLoadWithOverviewMode(false);

        /* My code */
        setting.setMediaPlaybackRequiresUserGesture(false);
    }

    public class WebviewInterface {
        @JavascriptInterface
        public void searchFromFiles(String val) {
            Toast.makeText(context, val, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void javaMehod(String val) {
            //Log.i(TAG, val);
            Toast.makeText(context, val, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void goBack() {
            //if (mWebView.canGoBack()) {
                //mWebView.goBack();
            //} else {
                //finish();
                Toast.makeText(context, "Nem tudok viszamenni", Toast.LENGTH_SHORT).show();
            //}
        }
    }

    public void walk(String path) {

        // Az asseteket nem lehet fájlként kezelni bammeg!

        String[] listFiles = {};

        try {
            listFiles  = getAssets().list(path);
        } catch (IOException e) {
            // Handle exceptions here
        }

        for (String f : listFiles) {

            if (f.indexOf(".") == -1) {
                Log.d("walk", "Dir: "+path+'/' + f);
                walk(path+'/'+f);
            }
            else {
                Log.d("walk", "File: "+path+'/' + f);
            }

        }

    }
}
