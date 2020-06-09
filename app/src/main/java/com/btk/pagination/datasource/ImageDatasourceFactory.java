package com.btk.pagination.datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.btk.pagination.model.Hits;

public class ImageDatasourceFactory extends DataSource.Factory<Integer, Hits> {

    private MutableLiveData<ImageDatasource> mutableLiveData;

    public ImageDatasourceFactory() {
        mutableLiveData = new MutableLiveData<>();
    }

    /**
     * Create a DataSource.
     * <p>
     * The DataSource should invalidate itself if the snapshot is no longer valid. If a
     * DataSource becomes invalid, the only way to query more data is to create a new DataSource
     * from the Factory.
     * <p>
     * {@link LivePagedListBuilder} for example will construct a new PagedList and DataSource
     * when the current DataSource is invalidated, and pass the new PagedList through the
     * {@code LiveData<PagedList>} to observers.
     *
     * @return the new DataSource.
     */
    @Override
    public DataSource<Integer, Hits> create() {
        ImageDatasource imageDatasource = new ImageDatasource();
        mutableLiveData.postValue(imageDatasource);
        return imageDatasource;
    }
}
