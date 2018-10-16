package com.ctbu.latte.ec.sign;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.ctbu.latte.delegates.LatteDelegate;
import com.ctbu.latte.ec.R;
import com.ctbu.latte.ec.R2;
import com.ctbu.latte.net.RestClient;
import com.ctbu.latte.net.callback.IError;
import com.ctbu.latte.net.callback.ISuccess;
import com.ctbu.latte.net.callback.ServerResponse;
import com.ctbu.latte.util.log.LatteLogger;
import com.ctbu.latte.wechat.LatteWeChat;
import com.ctbu.latte.wechat.callbacks.IWeChatSignInCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by CaiPengFei on 2017/11/13.
 */

public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ISignListener){
            mISignListener= (ISignListener) context;
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {
        LatteWeChat
                .getInstance()
                .onSignSuccess(new IWeChatSignInCallback() {
                    @Override
                    public void onSignInSuccess(String userInfo) {
                        Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
                    }
                })
                .signIn();
    }
    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()){
            RestClient.builder()
                    .url("mobile/user/login.do")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            ServerResponse serverResponse=SignHandler.responseFlag(response);
                            if(serverResponse.getStatus()==0){
                                Toast.makeText(getContext(), serverResponse.getMsg(), Toast.LENGTH_LONG).show();
                                SignHandler.onSignIn(response, mISignListener);
                            }else if(serverResponse.getStatus()==1){
                                Toast.makeText(getContext(), serverResponse.getMsg(), Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getContext(), serverResponse.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        }
                    })

                    .build()
                    .post();
        }
    }
    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }
    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码错误");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }
}
