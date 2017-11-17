package com.ctbu.fastec.example;

import android.app.Application;

import com.ctbu.latte.app.Latte;
import com.ctbu.fastec.example.event.TestEvent;
import com.ctbu.latte.ec.database.DatabaseManager;
import com.ctbu.latte.ec.icon.FontEcModule;
import com.ctbu.latte.net.interceptors.DebugInterceptor;
import com.ctbu.latte.net.rx.AddCookieInterceptor;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by chenting on 2017/11/7.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("http://192.168.56.1:8080/RestDataServer/data/")
                .withInterceptor(new DebugInterceptor("text", R.raw.text))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withJavascriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                //添加Cookie同步拦截器
                .withWebHost("https://www.baidu.com/")
                .withInterceptor(new AddCookieInterceptor())
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
    }
    private void initStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }
}
