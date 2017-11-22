package com.ctbu.latte.util.callback;

import android.support.annotation.Nullable;

/**
 * Created by chenting on 2017/11/22.
 */

public interface IGlobalCallback<T> {
    void executeCallback(@Nullable T args);
}
