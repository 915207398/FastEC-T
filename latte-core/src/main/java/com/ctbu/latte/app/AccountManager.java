package com.ctbu.latte.app;

import com.ctbu.latte.util.storage.LattePreference;

/**
 * Created by chenting on 2017/11/14.
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSign() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSign()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
