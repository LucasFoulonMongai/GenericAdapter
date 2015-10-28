package com.lfm.recyclerviewgenericadaptersample.presenters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lfm.rvgenadapter.ViewPresenter;
import com.lfm.recyclerviewgenericadaptersample.R;
import com.lfm.recyclerviewgenericadaptersample.models.SampleItem;

/**
 * Created by mogwai on 13/10/15.
 */
public class SampleItemPresenter extends ViewPresenter<SampleItem> {
    private View view;

    private TextView itemSampleTitreView;
    private TextView itemSampleDescriptionView;

    public SampleItemPresenter() {
        super();
    }


    @Override
    public void initViewPresenter(Context context, ViewGroup parent, Bundle params) {
        this.view = LayoutInflater.from(context).inflate(R.layout.item_sample, parent, false);

        mapViews(getView());

        if(params != null) {
            int color = params.getInt("color");

            if(color !=-1){
                itemSampleDescriptionView.setTextColor(color);
            }
        }
        if(getOnClickListener() != null) {
            view.setOnClickListener(getOnClickListener());
        }
    }

    private void mapViews(View view) {
        itemSampleTitreView = (TextView) view.findViewById(R.id.itemSampleTitreView);
        itemSampleDescriptionView = (TextView) view.findViewById(R.id.itemSampleDescriptionView);
    }

    @Override
    public void refresh() {
        if(view == null){
            return;
        }

        SampleItem data = getData();
        if (data != null) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
            return;
        }

        itemSampleTitreView.setText(data.getTitre());
        itemSampleDescriptionView.setText(data.getDescription());

    }

    @Override
    public void setOnClickListener(View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        if(view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    @Override
    public View getView() {
        return view;
    }
}
