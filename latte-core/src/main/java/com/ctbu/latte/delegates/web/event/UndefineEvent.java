package com.ctbu.latte.delegates.web.event;

import com.ctbu.latte.util.log.LatteLogger;

/**
 * Created by chenting on 2017/11/17.
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
