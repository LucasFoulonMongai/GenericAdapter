package com.lfm.recyclerviewgenericadaptersample.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lfm.recyclerviewgenericadaptersample.R;
import com.lfm.recyclerviewgenericadaptersample.models.SampleItem;
import com.lfm.recyclerviewgenericadaptersample.presenters.SampleItemPresenter;
import com.lfm.recyclerviewgenericadaptersample.recyclerview.utils.GenericRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SampleListActivity extends AppCompatActivity {

    private RecyclerView sampleRecyclerView;
    private List<SampleItem> sampleDatas;

    private View.OnClickListener onSampleItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag(R.id.tag_position);
            SampleItem item = (SampleItem) v.getTag(R.id.tag_content);
            Toast.makeText(SampleListActivity.this, position + " -> " + item.getTitre(), Toast.LENGTH_SHORT).show();

        }
    };
    private GenericRecyclerAdapter<SampleItem> sampleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);
        sampleRecyclerView = (RecyclerView) findViewById(R.id.sampleRecyclerView);

        loadData();
    }

    private void loadData() {
        sampleDatas = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            sampleDatas.add(new SampleItem("Item "+i, "Description "+i));
        }
        showData();
    }

    private void showData() {
        if(sampleDatas == null){
            return;
        }

        if(sampleAdapter == null){
            Bundle params = new Bundle();
            params.putInt("color", Color.RED);

            sampleAdapter = new GenericRecyclerAdapter<>(this, sampleDatas, SampleItemPresenter.class, onSampleItemClickListener, params);
            sampleRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            sampleRecyclerView.setAdapter(sampleAdapter);
        } else {
            sampleAdapter.setItems(sampleDatas);
        }
    }
}
