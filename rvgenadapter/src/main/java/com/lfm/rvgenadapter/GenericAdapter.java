package com.lfm.rvgenadapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mogwai on 13/10/15.
 */
public class GenericAdapter<E> extends RecyclerView.Adapter<ItemPresenterHolder<E>> {

    private View.OnClickListener onClickListener;
    private List<E> items;
    private Class<? extends ItemPresenter<E>> classPresenter;
    private Context context;
    private Bundle params;
    private boolean paramsInvalidated = false;
    private boolean onClickInvalidated = false;

    public GenericAdapter(Context context, Collection<E> items, Class<? extends ItemPresenter<E>> classPresenter) {
        this(context, items, classPresenter, null, null);
    }

    public GenericAdapter(Context context, Collection<E> items, Class<? extends ItemPresenter<E>> classPresenter, View.OnClickListener onClickListener) {
        this(context, items, classPresenter, onClickListener, null);
    }

    public GenericAdapter(Context context, Collection<E> items, Class<? extends ItemPresenter<E>> classPresenter, View.OnClickListener onClickListener, Bundle params) {
        this.items = new ArrayList<>(items);
        this.classPresenter = classPresenter;
        this.context = context;
        this.params = params;
        this.onClickListener = onClickListener;
    }

    public Bundle getParams() {
        return params;
    }

    public void setParams(Bundle params) {
        this.params = params;
        paramsInvalidated = true;
        notifyDataSetChanged();
    }

    @Override
    public ItemPresenterHolder<E> onCreateViewHolder(ViewGroup parent, int viewType) {
        return newPresenter(parent);
    }

    @Override
    public void onBindViewHolder(ItemPresenterHolder<E> holder, int position) {
        if (paramsInvalidated) {
            holder.setParams(params);
        }
        if (onClickInvalidated) {
            holder.setOnClickListener(onClickListener);
        }
        holder.swapData(position, getItem(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ItemPresenterHolder<E> newPresenter(ViewGroup parent) {
        try {
            ItemPresenter<E> itemPresenter = classPresenter.newInstance();
            itemPresenter.initViewPresenter(context, parent, params, onClickListener);
            return new ItemPresenterHolder<>(itemPresenter);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        onClickInvalidated = true;
        notifyDataSetChanged();
    }

    public List<E> getItems() {
        return items;
    }

    public void setItems(Collection<E> items) {
        this.items = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    public E getItem(int position) {
        return items.get(position);
    }

    @Override
    public void onViewAttachedToWindow(ItemPresenterHolder<E> holder) {
        super.onViewAttachedToWindow(holder);
        holder.onAttached();
    }

    @Override
    public void onViewDetachedFromWindow(ItemPresenterHolder<E> holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onDetached();
    }
}