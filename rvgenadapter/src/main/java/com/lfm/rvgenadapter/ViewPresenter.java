package com.lfm.rvgenadapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mogwai on 13/10/15.
 */
public abstract class ViewPresenter<E> {
    private View.OnClickListener onClickListener;
    private E data;

    public abstract void initViewPresenter(Context context, ViewGroup parent, Bundle params);

    public abstract View getView();

    public abstract void refresh();

    public void swapData(E data) {
        if(this.data != data) {
            this.data = data;
            refresh();
        }
    }

    public E getData() {
        return data;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }
}