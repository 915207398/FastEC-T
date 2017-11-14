package com.ctbu.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctbu.latte.app.AccountManager;
import com.ctbu.latte.ec.database.DatabaseManager;
import com.ctbu.latte.ec.database.UserProfile;


/**
 * Created by CaiPengFei on 2017/11/14.
 */

public class SignHandler {

    public static void onSignIn(String response,ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        //主键重复自动替换
        DatabaseManager.getInstance().getmDao().insertOrReplace(profile);
        //登录成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

    public static void onSignUp(String response,ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
//        DatabaseManager.getInstance().getmDao().insert(profile);
        //主键重复自动替换
          DatabaseManager.getInstance().getmDao().insertOrReplace(profile);
        //已经注册并登录成功
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
