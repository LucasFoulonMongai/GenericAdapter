package com.lfm.rvgenadapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mogwai on 13/10/15.
 */
public class ItemPresenterHolder<E> extends RecyclerView.ViewHolder {
    private final View view;
    private ItemPresenter<E> itemPresenter;

    public ItemPresenterHolder(ItemPresenter<E> itemPresenter) {
        super(itemPresenter.getView());
        this.view = itemPresenter.getView();
        this.itemPresenter = itemPresenter;
    }

    public ItemPresenter<E> getItemPresenter() {
        return itemPresenter;
    }

    public void swapData(int position, E data) {
        itemPresenter.swapData(position, data);
    }

    public View getView() {
        return view;
    }

    public void setParams(Bundle params) {
        itemPresenter.onNewParams(params);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        itemPresenter.onNewOnClickListener(onClickListener);
    }

}