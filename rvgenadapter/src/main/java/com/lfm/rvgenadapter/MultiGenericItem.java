package com.lfm.rvgenadapter;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

public class MultiGenericItem<E> implements Comparable<MultiGenericItem> {

    private final int index;
    private final int viewType;
    private final List<? extends E> list;
    private final Class<? extends ItemPresenter<E>> classPresenter;
    private final View.OnClickListener onClickListener;

    public MultiGenericItem(int index, int viewType, List<? extends E> list, Class<? extends ItemPresenter<E>> classPresenter, View.OnClickListener onClickListener) {
        this.index = index;
        this.viewType = viewType;
        this.list = list;
        this.classPresenter = classPresenter;
        this.onClickListener = onClickListener;
    }

    public int getViewType() {
        return viewType;
    }

    public List<? extends E> getList() {
        return list;
    }

    public Class<? extends ItemPresenter<E>> getClassPresenter() {
        return classPresenter;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    @Override
    public boolean equals(Object o) {
        return !(o == null || !(o instanceof MultiGenericItem)) && index == ((MultiGenericItem) o).index;
    }

    public int getCount() {
        return list == null ? 0 : list.size();
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(@NonNull MultiGenericItem another) {
        return index - another.index;
    }
}
