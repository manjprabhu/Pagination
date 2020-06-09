package com.btk.pagination.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.btk.pagination.R;
import com.btk.pagination.adatper.ImageListAdapter;
import com.btk.pagination.databinding.ActivityMainBinding;
import com.btk.pagination.model.Hits;
import com.btk.pagination.viewmodel.ImageViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    ImageViewModel imageViewModel;
    private ImageListAdapter imageListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        imageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
        initviews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        observeData();
    }

    private void observeData() {

        imageViewModel.fetchData().observe(this, new Observer<PagedList<Hits>>() {
            @Override
            public void onChanged(PagedList<Hits> hits) {
                imageListAdapter.submitList(hits);
            }
        });
        mainBinding.rvImage.setVisibility(View.VISIBLE);
        mainBinding.rvImage.setAdapter(imageListAdapter);
    }

    private void initviews() {
        imageListAdapter = new ImageListAdapter(this);
        mainBinding.rvImage.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.rvImage.setHasFixedSize(true);
    }
}
