package com.ctbu.fastec.example.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.ctbu.latte.delegates.web.event.Event;

/**
 * Created by chenting on 2017/11/17.
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(),getAction(),Toast.LENGTH_LONG).show();
        if (getAction().equals("test")){
            final WebView webView=getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();",null);
                }
            });
        }
        return null;
    }
}
