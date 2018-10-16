package com.ctbu.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctbu.latte.app.AccountManager;
import com.ctbu.latte.ec.database.DatabaseManager;
import com.ctbu.latte.ec.database.User;
import com.ctbu.latte.net.callback.ServerResponse;
import com.ctbu.latte.util.log.LatteLogger;

import java.util.Date;


/**
 * Created by CaiPengFei on 2017/11/14.
 */

public class SignHandler {

    public static void onSignIn(String response,ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final Integer id = profileJson.getInteger("id");
        final String username = profileJson.getString("username");
        final String password = profileJson.getString("password");
        final String email = profileJson.getString("email");
        final String phone = profileJson.getString("phone");
        final String question = profileJson.getString("question");
        final String answer = profileJson.getString("answer");
        final Integer role = profileJson.getInteger("answer");
        final Date createTime = profileJson.getDate("createTime");
        final Date updateTime = profileJson.getDate("updateTime");

        final User user = new User(id,username,password,email, phone, question, answer, role, createTime, updateTime);
        //主键重复自动替换
        DatabaseManager.getInstance().getDao().insertOrReplace(user);
        //登录成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

    public static ServerResponse responseFlag(String response) {
        final JSONObject Json = JSON.parseObject(response);
        final int status =Json.getInteger("status");
        final String msg =Json.getString("msg");
        LatteLogger.d("USER_RESPONSE", status);
        LatteLogger.d("USER_RESPONSE", msg);
       return new ServerResponse( status, msg);

    }
}
