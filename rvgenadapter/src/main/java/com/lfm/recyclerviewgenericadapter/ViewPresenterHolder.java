package com.lfm.recyclerviewgenericadapter;

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

    public void swapData(E data) {
        viewPresenter.swapData(data);
    }

    public View getView() {
        return view;
    }

    public void setTag(int idTag, Object tag) {
        view.setTag(idTag, tag);
    }
}