package com.lfm.rvgenadapter.multi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lfm.rvgenadapter.ItemViewHolder;
import com.lfm.rvgenadapter.ItemViewHolderFactory;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Lucas FOULON-MONGAI, github.com/LucasFoulonMongai
 */
public class MultiGenericAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private final LayoutInflater inflater;
    private SortedSet<GenericDataSet> itemSet;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;

    public MultiGenericAdapter(Context context) {
        this(context, null, null);
    }

    public MultiGenericAdapter(Context context, View.OnClickListener onClickListener) {
        this(context, onClickListener, null);
    }

    public MultiGenericAdapter(Context context, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        this.itemSet = new TreeSet<>();
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
        this.onLongClickListener = onLongClickListener;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        Object item = getItem(position);
        if (item == null) {
            return super.getItemId(position);
        }
        return item.hashCode();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder holder = getItemMulti(viewType).getItemViewHolderFactory().buildItemView(inflater, parent);
        holder.setOnClickListener(onClickListener);
        holder.setOnLongClickListener(onLongClickListener);
        return holder;
    }

    private GenericDataSet getItemMulti(int viewType) {
        for (GenericDataSet item : itemSet) {
            if (item.getViewType() == viewType) {
                return item;
            }
        }
        return itemSet.first();
    }

    @Override
    public int getItemViewType(int position) {
        int count = 0;
        for (GenericDataSet item : itemSet) {
            count += item.getCount();
            if (count > position) {
                return item.getViewType();
            }
        }
        return 0;
    }

    public Object getItem(int position) {
        int totalCount = 0;
        for (GenericDataSet item : itemSet) {
            int itemCount = item.getCount();
            if ((totalCount + itemCount) > position) {
                return item.getList().get(position - totalCount);
            }
            totalCount += itemCount;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.swapData(position, getItem(position));
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (GenericDataSet item : itemSet) {
            count += item.getCount();
        }
        return count;
    }

    public void clearItems() {
        itemSet.clear();
    }

    public <E> void setItems(int index, int viewType, E item, ItemViewHolderFactory<E> itemViewHolderFactory) {
        setItems(index, viewType, Collections.singletonList(item), itemViewHolderFactory);
    }

    public <E> void setItems(int index, int viewType, List<? extends E> list, ItemViewHolderFactory<E> itemViewHolderFactory) {
        GenericDataSet item = new GenericDataSet<>(index, viewType, list, itemViewHolderFactory);
        itemSet.remove(item);
        itemSet.add(item);
    }

    public <E> void removeItems(int index) {
        GenericDataSet<E> item = new GenericDataSet<>(index, 0, null, null);
        itemSet.remove(item);
    }

    public void removeMultipleItems(int... indexes) {
        for (int index : indexes) {
            removeItems(index);
        }
        notifyDataSetChanged();
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }
}