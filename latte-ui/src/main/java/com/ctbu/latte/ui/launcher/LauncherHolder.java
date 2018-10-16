package com.ctbu.latte.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by CaiPengFei on 2017/11/11.
 */

public class LauncherHolder implements Holder<Integer> {

    private AppCompatTextView mImageView = null;

    //创建容器
    @Override
    public View createView(Context context) {
        mImageView = new AppCompatTextView(context);
        return mImageView;
    }

    //更新时加载数据
    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
