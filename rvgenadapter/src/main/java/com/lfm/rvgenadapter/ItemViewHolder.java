package com.lfm.rvgenadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lucas FOULON-MONGAI, github.com/LucasFoulonMongai
 */
public abstract class ItemViewHolder<E> extends RecyclerView.ViewHolder {
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;

    public ItemViewHolder(View itemView) {
        super(itemView);
    }

    public ItemViewHolder(LayoutInflater inflater, ViewGroup parent, @LayoutRes int itemViewId) {
        super(inflater.inflate(itemViewId, parent, false));
    }

    public View getView() {
        return itemView;
    }

    public void swapData(int position, E data) {
        if (itemView == null) {
            return;
        }
        itemView.setTag(R.id.tag_position, position);
        itemView.setTag(R.id.tag_content, data);

        onDataUpdated(itemView.getContext(), itemView, position, data);
    }

    public abstract void onDataUpdated(Context context, View view, int position, E data);

    protected View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);
        this.onClickListener = onClickListener;
    }

    protected View.OnLongClickListener getOnLongClickListener() {
        return onLongClickListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        itemView.setOnLongClickListener(onLongClickListener);
        this.onLongClickListener = onLongClickListener;
    }
}