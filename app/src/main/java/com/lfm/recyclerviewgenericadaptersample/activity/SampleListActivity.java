package com.lfm.recyclerviewgenericadaptersample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lfm.recyclerviewgenericadaptersample.R;
import com.lfm.recyclerviewgenericadaptersample.adapter.SampleAdapter;
import com.lfm.recyclerviewgenericadaptersample.itemviewholder.SampleItemViewHolder;
import com.lfm.recyclerviewgenericadaptersample.model.SampleModel;
import com.lfm.rvgenadapter.GenericAdapter;
import com.lfm.rvgenadapter.multi.MultiGenericAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas FOULON-MONGAI, github.com/LucasFoulonMongai
 */
public class SampleListActivity extends AppCompatActivity implements View.OnClickListener {

    public static final boolean USE_CUSTOM_ADAPTER = true;
    private RecyclerView recyclerView;
    private List<SampleModel> sampleModels;
    private GenericAdapter<SampleModel> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);
        recyclerView = (RecyclerView) findViewById(R.id.sample_recycler_view);

        loadData();
        displayData();
    }

    private void loadData() {
        sampleModels = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            sampleModels.add(new SampleModel("Item " + i, "Description " + i));
        }
    }

    private void displayData() {
        if (adapter != null) {
            adapter.setItems(sampleModels);
            return;
        }

        if (USE_CUSTOM_ADAPTER) {
            adapter = new SampleAdapter(this, this);
        } else {
            // If you don't have any custom code to add to the adapter, use this :
            adapter = new GenericAdapter<>(this, SampleItemViewHolder.FACTORY, this);
        }

        adapter.setItems(sampleModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void demoMultiAdapter() {
        // Here is a demo on how to use the MultiGenericAdapter
        MultiGenericAdapter multiAdapter = new MultiGenericAdapter(this, this);
        multiAdapter.setItems(0, 1, sampleModels, SampleItemViewHolder.FACTORY);
        // Items at index 1 will be added after all the items at index 0
        multiAdapter.setItems(1, 1, sampleModels, SampleItemViewHolder.FACTORY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_sample:
                int position = (int) v.getTag(R.id.tag_position);
                SampleModel item = (SampleModel) v.getTag(R.id.tag_content);
                Toast.makeText(SampleListActivity.this, position + " -> " + item.getTitre(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
