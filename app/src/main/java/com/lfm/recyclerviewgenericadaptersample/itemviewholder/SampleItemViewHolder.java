package com.lfm.recyclerviewgenericadaptersample.itemviewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lfm.recyclerviewgenericadaptersample.R;
import com.lfm.recyclerviewgenericadaptersample.model.SampleModel;
import com.lfm.rvgenadapter.ItemViewHolder;
import com.lfm.rvgenadapter.ItemViewHolderFactory;

/**
 * Created by Lucas FOULON-MONGAI, github.com/LucasFoulonMongai
 */
public class SampleItemViewHolder extends ItemViewHolder<SampleModel> {

    public static final ItemViewHolderFactory<SampleModel> FACTORY = new ItemViewHolderFactory<SampleModel>() {
        @Override
        public ItemViewHolder<SampleModel> buildItemView(LayoutInflater inflater, ViewGroup parent) {
            return new SampleItemViewHolder(inflater, parent);
        }
    };

    private TextView itemSampleTitreView;
    private TextView itemSampleDescriptionView;

    private SampleItemViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent, R.layout.item_sample);
        bindViews(getView());
    }

    private void bindViews(View view) {
        itemSampleTitreView = (TextView) view.findViewById(R.id.item_sample_titre_view);
        itemSampleDescriptionView = (TextView) view.findViewById(R.id.item_sample_description_view);
    }

    @Override
    public void onDataUpdated(Context context, View view, int position, SampleModel data) {
        itemSampleTitreView.setText(data.getTitre());
        itemSampleDescriptionView.setText(data.getDescription());
    }

    public void setCustomData() {
        // Place your custom actions here
    }
}

