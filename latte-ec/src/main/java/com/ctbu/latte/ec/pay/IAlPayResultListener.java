package com.ctbu.latte.ec.pay;

/**
 * Created by CaiPengFei on 2017/11/19.
 */

public interface IAlPayResultListener {
    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
