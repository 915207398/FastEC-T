package com.ctbu.latte.ec.sign;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ctbu.latte.delegates.LatteDelegate;
import com.ctbu.latte.ec.R;
import com.ctbu.latte.ec.R2;
import com.ctbu.latte.ec.database.User;
import com.ctbu.latte.net.RestClient;
import com.ctbu.latte.net.callback.IError;
import com.ctbu.latte.net.callback.ISuccess;
import com.ctbu.latte.net.callback.ServerResponse;
import com.ctbu.latte.util.log.LatteLogger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenting on 2017/11/12.
 */

public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;
    @BindView(R2.id.edit_sign_up_question)
    TextInputEditText mQuestion = null;
    @BindView(R2.id.edit_sign_up_answer)
    TextInputEditText mAnswer = null;

    private ISignListener mISignListener = null;
    private User user;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ISignListener) {
            mISignListener = (ISignListener) context;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            LatteLogger.json("PushReceiver", JSON.toJSONString(user));
            RestClient.builder()
                    .url("mobile/user/register.do")
                    .raw(JSON.toJSONString(user))
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            ServerResponse serverResponse=SignHandler.responseFlag(response);
                            if(serverResponse.getStatus()==0){
                                Toast.makeText(getContext(), serverResponse.getMsg(), Toast.LENGTH_LONG).show();
                                mISignListener.onSignUpSuccess();
                            }else if(serverResponse.getStatus()==1){
                                Toast.makeText(getContext(), serverResponse.getMsg(), Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getContext(), serverResponse.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            LatteLogger.i("USER_PROFILE", msg);
                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        getSupportDelegate().start(new SignInDelegate());
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();
        final String question = mQuestion.getText().toString();
        final String answer = mAnswer.getText().toString();
        boolean isPass = true;
        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }
        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }
        if (question.isEmpty()) {
            mQuestion.setError("请输入问题");
            isPass = false;
        } else {
            mQuestion.setError(null);
        }
        if (answer.isEmpty()) {
            mAnswer.setError("请输入答案");
            isPass = false;
        } else {
            mAnswer.setError(null);
        }
        user = new User(name, password, email, phone, question, answer);
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
