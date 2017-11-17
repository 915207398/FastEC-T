package com.ctbu.latte.delegates.web.event;

import android.widget.Toast;

/**
 * Created by chenting on 2017/11/17.
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(),params,Toast.LENGTH_LONG).show();
        System.out.println(params+"-----------------------------xxoo");
        return null;
    }
}
