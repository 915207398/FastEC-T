package com.ctbu.latte.delegates.bottom;

/**
 * Created by chenting on 2017/11/15.
 */

public final class BottomTabBean {

    private final CharSequence ICON;
    private final CharSequence TITLE;
    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
