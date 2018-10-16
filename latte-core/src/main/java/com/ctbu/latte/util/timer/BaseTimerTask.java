package com.ctbu.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by chenting on 2017/11/10.
 */
// TODO: 2017/11/10 百度去了解TimerTask.
public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    //会自动被执行到
    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
