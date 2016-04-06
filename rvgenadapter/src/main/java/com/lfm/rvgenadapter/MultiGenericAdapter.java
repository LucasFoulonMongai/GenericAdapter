package com.lfm.rvgenadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class MultiGenericAdapter extends RecyclerView.Adapter<ItemPresenterHolder> {

    private Context context;
    private SortedSet<MultiGenericItem> itemSet;

    public MultiGenericAdapter(Context context) {
        this.context = context;
        this.itemSet = new TreeSet<>();
        setHasStableIds(true);
    }

    @Override
    public ItemPresenterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MultiGenericItem item = getItemMulti(viewType);
        return newPresenter(item.getClassPresenter(), parent, item.getOnClickListener());
    }

    private MultiGenericItem getItemMulti(int viewType) {
        for (MultiGenericItem item : itemSet) {
            if (item.getViewType() == viewType) {
                return item;
            }
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        int count = 0;
        for (MultiGenericItem item : itemSet) {
            count += item.getCount();
            if (count > position) {
                return item.getViewType();
            }
        }
        return 0;
    }

    public Object getItem(int position) {
        int count = 0;
        for (MultiGenericItem item : itemSet) {
            int tmp = item.getCount();
            if ((count + tmp) > position) {
                return item.getList().get(position - count);
            }
            count += tmp;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(ItemPresenterHolder holder, int position) {
        holder.swapData(position, getItem(position));
    }

    private ItemPresenterHolder newPresenter(Class<ItemPresenter> classPresenter, ViewGroup parent, View.OnClickListener onClickListener) {
        try {
            ItemPresenter itemPresenter = classPresenter.newInstance();
            itemPresenter.initViewPresenter(context, parent, null, onClickListener);
            return new ItemPresenterHolder<>(itemPresenter);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (MultiGenericItem item : itemSet) {
            count += item.getCount();
        }
        return count;
    }

    public void clearItems() {
        itemSet.clear();
    }

    public <E> void setItems(int index, int viewType, E item, Class<? extends ItemPresenter<E>> classPresenter) {
        setItems(index, viewType, item, classPresenter, null);
    }

    public <E> void setItems(int index, int viewType, List<? extends E> list, Class<? extends ItemPresenter<E>> classPresenter) {
        setItems(index, viewType, list, classPresenter, null);
    }

    public <E> void setItems(int index, int viewType, E item, Class<? extends ItemPresenter<E>> classPresenter, View.OnClickListener onClickListener) {
        List<E> tmpList = new ArrayList<>(1);
        tmpList.add(item);
        setItems(index, viewType, tmpList, classPresenter, onClickListener);
    }

    public <E> void setItems(int index, int viewType, List<? extends E> list, Class<? extends ItemPresenter<E>> classPresenter, View.OnClickListener onClickListener) {
        MultiGenericItem item = new MultiGenericItem<>(index, viewType, list, classPresenter, onClickListener);
        if (itemSet.contains(item)) {
            itemSet.remove(item);
        }
        itemSet.add(item);
    }

    @Override
    public void onViewAttachedToWindow(ItemPresenterHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.onAttached();
    }

    @Override
    public void onViewDetachedFromWindow(ItemPresenterHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onDetached();
    }
}
