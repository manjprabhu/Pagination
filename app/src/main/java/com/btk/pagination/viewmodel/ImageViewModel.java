package com.btk.pagination.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.btk.pagination.datasource.ImageDatasourceFactory;
import com.btk.pagination.model.Hits;

public class ImageViewModel extends ViewModel {

    ImageDatasourceFactory factory = new ImageDatasourceFactory();
    private LiveData<PagedList<Hits>> pagedListMutableLiveData;

    public ImageViewModel() {
        PagedList.Config config =  new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(50).build();

        pagedListMutableLiveData = new LivePagedListBuilder(factory,config).build();
    }

    public LiveData<PagedList<Hits>> fetchData() {
        return pagedListMutableLiveData;
    }
}
