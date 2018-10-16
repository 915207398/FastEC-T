package com.ctbu.latte.ec.launcher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.ctbu.latte.app.AccountManager;
import com.ctbu.latte.app.IUserChecker;
import com.ctbu.latte.delegates.LatteDelegate;
import com.ctbu.latte.ec.R;
import com.ctbu.latte.ec.main.EcBottomDelegate;
import com.ctbu.latte.ui.launcher.ILauncherListener;
import com.ctbu.latte.ui.launcher.OnLauncherFinishTag;
import com.ctbu.latte.ui.launcher.LauncherHolderCreator;
import com.ctbu.latte.ui.launcher.ScrollLauncherTag;
import com.ctbu.latte.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * Created by CaiPengFei on 2017/11/11.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    private void initBanner() {
        INTEGERS.add(R.mipmap.girl_00);
        INTEGERS.add(R.mipmap.girl_01);
        INTEGERS.add(R.mipmap.girl_02);
        INTEGERS.add(R.mipmap.girl_03);
        INTEGERS.add(R.mipmap.girl_04);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)//加载图片
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})//设置底部圆球
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//设置底部圆球位置
                .setOnItemClickListener(this)
//                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                    @Override
//                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                    }
//
//                    @Override
//                    public void onPageSelected(int position) {
//
//                    }
//
//                    @Override
//                    public void onPageScrollStateChanged(int state) {
//
//                    }//到最后一页自动跳转到登录页面
//
//                })
                .setCanLoop(false);//设置不可以循环
    }


    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }


    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登录

            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
