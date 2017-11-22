package com.ctbu.fastec.example;

import android.app.Application;
import android.support.annotation.Nullable;

import com.ctbu.latte.app.Latte;
import com.ctbu.fastec.example.event.TestEvent;
import com.ctbu.latte.ec.database.DatabaseManager;
import com.ctbu.latte.ec.icon.FontEcModule;
import com.ctbu.latte.net.interceptors.DebugInterceptor;
import com.ctbu.latte.net.rx.AddCookieInterceptor;
import com.ctbu.latte.util.callback.CallbackManager;
import com.ctbu.latte.util.callback.CallbackType;
import com.ctbu.latte.util.callback.IGlobalCallback;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.jpush.android.api.JPushInterface;

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


        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            JPushInterface.stopPush(Latte.getApplicationContext());
                        }
                    }
                });
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
