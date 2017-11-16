package com.ctbu.latte.ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ctbu.latte.delegates.LatteDelegate;
import com.ctbu.latte.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by chenting on 2017/11/16.
 */

public class GoodsDetailDelegate extends LatteDelegate {

    public static GoodsDetailDelegate create() {
//        final Bundle args = new Bundle();
//        args.putInt(ARG_GOODS_ID, goodsId);
//        final GoodsDetailDelegate delegate = new GoodsDetailDelegate();
//        delegate.setArguments(args);
//        return delegate;
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        super.onBindView(savedInstanceState, rootView);
    }

    //跳转动画
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
