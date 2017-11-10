package com.ctbu.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.ctbu.latte.delegates.LatteDelegate;
import com.ctbu.latte.ec.R;
import com.ctbu.latte.ui.launcher.LauncherHolderCreator;

import java.util.ArrayList;

/**
 * Created by CaiPengFei on 2017/11/11.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener{

    private ConvenientBanner<Integer> mConvenientBanner=null;
    private static final ArrayList<Integer> INTEGERS =new ArrayList<>();

    private void initBanner(){
        INTEGERS.add(R.mipmap.girl_00);
        INTEGERS.add(R.mipmap.girl_01);
        INTEGERS.add(R.mipmap.girl_02);
        INTEGERS.add(R.mipmap.girl_03);
        INTEGERS.add(R.mipmap.girl_04);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_narmal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }
    @Override
    public Object setLayout() {
        mConvenientBanner=new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
       initBanner();
    }


    @Override
    public void onItemClick(int position) {

    }
}
