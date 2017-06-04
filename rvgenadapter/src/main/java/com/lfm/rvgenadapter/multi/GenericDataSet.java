package com.lfm.rvgenadapter.multi;

import android.support.annotation.NonNull;

import com.lfm.rvgenadapter.ItemViewHolderFactory;

import java.util.List;

/**
 * Created by Lucas FOULON-MONGAI, github.com/LucasFoulonMongai
 */
public class GenericDataSet<E> implements Comparable<GenericDataSet> {

    private final int index;
    private final int viewType;
    private final List<? extends E> list;
    private final ItemViewHolderFactory<E> itemViewHolderFactory;

    public GenericDataSet(int index, int viewType, List<? extends E> list, ItemViewHolderFactory<E> itemViewHolderFactory) {
        this.index = index;
        this.viewType = viewType;
        this.list = list;
        this.itemViewHolderFactory = itemViewHolderFactory;
    }

    public int getViewType() {
        return viewType;
    }

    public List<? extends E> getList() {
        return list;
    }

    public ItemViewHolderFactory<E> getItemViewHolderFactory() {
        return itemViewHolderFactory;
    }

    @Override
    public boolean equals(Object o) {
        return !(o == null || !(o instanceof GenericDataSet)) && index == ((GenericDataSet) o).index;
    }

    public int getCount() {
        return list == null ? 0 : list.size();
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(@NonNull GenericDataSet another) {
        return index - another.index;
    }
}