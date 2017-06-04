package com.lfm.rvgenadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Lucas FOULON-MONGAI, github.com/LucasFoulonMongai
 */
public class GenericAdapter<E> extends RecyclerView.Adapter<ItemViewHolder<E>> {

    private final LayoutInflater inflater;
    private List<E> items;
    private ItemViewHolderFactory<E> itemViewHolderFactory;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;

    public GenericAdapter(Context context, ItemViewHolderFactory<E> itemViewHolderFactory) {
        this(context, itemViewHolderFactory, null, null, null);
    }

    public GenericAdapter(Context context, ItemViewHolderFactory<E> itemViewHolderFactory, View.OnClickListener onClickListener) {
        this(context, itemViewHolderFactory, onClickListener, null, null);
    }

    public GenericAdapter(Context context, ItemViewHolderFactory<E> itemViewHolderFactory, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        this(context, itemViewHolderFactory, onClickListener, onLongClickListener, null);
    }

    public GenericAdapter(Context context, ItemViewHolderFactory<E> itemViewHolderFactory, View.OnClickListener onClickListener, Collection<E> items) {
        this(context, itemViewHolderFactory, onClickListener, null, items);
    }

    public GenericAdapter(Context context, ItemViewHolderFactory<E> itemViewHolderFactory, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener, Collection<E> items) {
        this.inflater = LayoutInflater.from(context);
        this.itemViewHolderFactory = itemViewHolderFactory;
        this.onClickListener = onClickListener;
        this.onLongClickListener = onLongClickListener;
        setItems(items);
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        E item = getItem(position);
        if (item != null) {
            return item.hashCode();
        }
        return super.getItemId(position);
    }

    @Override
    public ItemViewHolder<E> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder<E> holder = itemViewHolderFactory.buildItemView(inflater, parent);
        holder.setOnClickListener(onClickListener);
        holder.setOnLongClickListener(onLongClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder<E> holder, int position) {
        holder.swapData(position, getItem(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<E> getItems() {
        return items;
    }

    public void setItems(Collection<E> items) {
        this.items = items != null ? new ArrayList<>(items) : new ArrayList<E>();
        notifyDataSetChanged();
    }

    public E getItem(int position) {
        return items.get(position);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        notifyDataSetChanged();
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void clearItems() {
        setItems(null);
    }
}