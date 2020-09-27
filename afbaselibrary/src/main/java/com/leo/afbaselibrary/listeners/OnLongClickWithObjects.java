package com.leo.afbaselibrary.listeners;

import android.view.View;
/**
 * email：1632365610@qq.com
 * */
public abstract class OnLongClickWithObjects implements View.OnLongClickListener {
    private Object[] objects;

    public OnLongClickWithObjects(Object... objects) {
        this.objects = objects;
    }

    @Override
    public boolean onLongClick(View v) {
        return onLongClick(v, objects);
    }

    public abstract boolean onLongClick(View view, Object[] objects);
}
