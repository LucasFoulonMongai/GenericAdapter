package com.lfm.rvgenadapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by Lucas FOULON-MONGAI, github.com/LucasFoulonMongai
 */
public interface ItemViewHolderFactory<E> {

    ItemViewHolder<E> buildItemView(LayoutInflater inflater, ViewGroup parent);
}