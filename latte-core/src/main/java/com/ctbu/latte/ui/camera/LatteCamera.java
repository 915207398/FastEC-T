package com.ctbu.latte.ui.camera;

import android.net.Uri;

import com.ctbu.latte.delegates.PermissionCheckerDelegate;
import com.ctbu.latte.util.file.FileUtil;

/**
 * Created by chenting on 2017/11/22.
 * 照相机调用类
 */

public class LatteCamera {
    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
