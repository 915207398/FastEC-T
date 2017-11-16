package com.ctbu.latte.delegates;

/**
 * Created by chenting on 2017/11/8.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {
    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
