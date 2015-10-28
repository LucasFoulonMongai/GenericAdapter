package com.lfm.rvgenadapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mogwai on 13/10/15.
 */
public class ViewPresenterHolder<E> extends RecyclerView.ViewHolder {
    private final View view;
    private ViewPresenter<E> viewPresenter;

    public ViewPresenterHolder(ViewPresenter<E> viewPresenter) {
        super(viewPresenter.getView());
        this.view = viewPresenter.getView();
        this.viewPresenter = viewPresenter;
    }

    public ViewPresenter<E> getViewPresenter() {
        return viewPresenter;
    }

    public void swapData(int position, E data) {
        viewPresenter.swapData(position, data);
    }

    public View getView() {
        return view;
    }

    public void setParams(Bundle params) {
        this.viewPresenter.onNewParams(params);
    }
}