package com.ctbu.latte.wechat.templates;

import android.widget.Toast;

import com.ctbu.latte.activities.ProxyActivity;
import com.ctbu.latte.delegates.LatteDelegate;
import com.ctbu.latte.wechat.BaseWXPayEntryActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;

/**
 * Created by CaiPengFei on 2017/11/15.
 */

public class WXPayEntryTemplate extends BaseWXPayEntryActivity{

    @Override
    protected void onPaySuccess() {
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayFail() {
        Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayCancel() {
        Toast.makeText(this, "支付取消", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}
