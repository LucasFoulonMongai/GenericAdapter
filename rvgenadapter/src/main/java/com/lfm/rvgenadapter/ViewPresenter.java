package com.lfm.rvgenadapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mogwai on 13/10/15.
 */
public abstract class ViewPresenter<E> {
    private E data;
    private int position = -1;

    public abstract void initViewPresenter(Context context, ViewGroup parent, Bundle params, View.OnClickListener onClickListener);

    public abstract View getView();

    public abstract void refresh();

    public void swapData(int position, E data) {
        if (this.data != data && this.position != position) {
            this.position = position;
            this.data = data;
            refresh();
        }
    }

    public int getPosition() {
        return position;
    }

    public E getData() {
        return data;
    }

    public void onNewParams(Bundle params){

    }
}