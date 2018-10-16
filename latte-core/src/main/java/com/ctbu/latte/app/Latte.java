package com.ctbu.latte.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;


/**
 * Created by chenting on 2017/11/7.
 */

public final class Latte {

    //初始化用方法
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }
    //用来重新写入和加载Configurator里面的值
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }
    //调用Configurator里面初始化后键入枚举类里面的值。
    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }
     //调用全局上下文
    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }
}
