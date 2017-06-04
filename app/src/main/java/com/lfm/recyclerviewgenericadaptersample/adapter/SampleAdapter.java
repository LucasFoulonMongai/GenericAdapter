package com.lfm.recyclerviewgenericadaptersample.adapter;

import android.content.Context;
import android.view.View;

import com.lfm.recyclerviewgenericadaptersample.itemviewholder.SampleItemViewHolder;
import com.lfm.recyclerviewgenericadaptersample.model.SampleModel;
import com.lfm.rvgenadapter.GenericAdapter;
import com.lfm.rvgenadapter.ItemViewHolder;

/**
 * Created by Lucas FOULON-MONGAI, github.com/LucasFoulonMongai
 */
public class SampleAdapter extends GenericAdapter<SampleModel> {
    public SampleAdapter(Context context, View.OnClickListener onClickListener) {
        super(context, SampleItemViewHolder.FACTORY, onClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder<SampleModel> holder, int position) {
        super.onBindViewHolder(holder, position);
        // Optional : You can add custom calls here
        ((SampleItemViewHolder) holder).setCustomData();
    }
}
