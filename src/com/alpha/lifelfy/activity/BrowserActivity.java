/**
 * 
 */
package com.alpha.lifelfy.activity;

import com.alpha.weather.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * @author Simon
 * @category 百度搜索
 */
public class BrowserActivity extends Activity {
    private WebView webView;
    private final String URL = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        webView = (WebView) findViewById(R.id.wv_baidu);
        webView.loadUrl(URL);

    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
     */
    // @Override
    // public boolean onKeyDown(int keyCode, KeyEvent event) {
    // // TODO Auto-generated method stub
    // if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
    // webView.goBack();
    // return true;
    // } else {
    // return false;
    // }
    // }

}
