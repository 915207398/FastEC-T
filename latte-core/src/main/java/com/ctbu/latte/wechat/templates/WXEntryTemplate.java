package com.ctbu.latte.wechat.templates;

import com.ctbu.latte.activities.ProxyActivity;
import com.ctbu.latte.delegates.LatteDelegate;
import com.ctbu.latte.wechat.BaseWXEntryActivity;
import com.ctbu.latte.wechat.LatteWeChat;

/**
 * Created by CaiPengFei on 2017/11/15.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
