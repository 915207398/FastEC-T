package com.ctbu.latte.ui.camera;

import android.net.Uri;

/**
 * Created by chenting on 2017/11/22.
 * 存储一些中间值
 */

public final class CameraImageBean {
    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
