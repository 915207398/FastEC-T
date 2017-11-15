package com.ctbu.latte.ec.mian.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ctbu.latte.delegates.bottom.BottomItemDelegate;
import com.ctbu.latte.ec.R;

/**
 * Created by chenting on 2017/11/15.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
      return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        super.onBindView(savedInstanceState, rootView);
    }
}
