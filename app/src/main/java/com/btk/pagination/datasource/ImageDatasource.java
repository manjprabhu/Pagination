package com.btk.pagination.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.btk.pagination.model.Hits;
import com.btk.pagination.model.ImageDataResponse;
import com.btk.pagination.network.ApiInterface;
import com.btk.pagination.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageDatasource extends PageKeyedDataSource<Integer, Hits> {

    public static final int PAGE_SIZE = 50;
    private final int FIRST_PAGE = 1;
    private final String KEY = "16882299-34c8e8f890077274bce6cbbc1";
    /**
     * Load initial data.
     * <p>
     * This method is called first to initialize a PagedList with data. If it's possible to count
     * the items that can be loaded by the DataSource, it's recommended to pass the loaded data to
     * the callback via the three-parameter
     * {@link LoadInitialCallback#onResult(List, int, int, Object, Object)}. This enables PagedLists
     * presenting data from this source to display placeholders to represent unloaded items.
     * <p>
     * {@link LoadInitialParams#requestedLoadSize} is a hint, not a requirement, so it may be may be
     * altered or ignored.
     *
     * @param params   Parameters for initial load, including requested load size.
     * @param callback Callback that receives initial load data.
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Hits> callback) {


        ApiInterface apiInterface = RetrofitClient.getInstance().create(ApiInterface.class);

        apiInterface.fetchData(KEY,FIRST_PAGE,PAGE_SIZE).enqueue(new Callback<ImageDataResponse>() {
            @Override
            public void onResponse(Call<ImageDataResponse> call, Response<ImageDataResponse> response) {
                callback.onResult(response.body().getHitsList(),null,FIRST_PAGE+1);
            }

            @Override
            public void onFailure(Call<ImageDataResponse> call, Throwable t) {

            }
        });

    }

    /**
     * Prepend page with the key specified by {@link LoadParams#key LoadParams.key}.
     * <p>
     * It's valid to return a different list size than the page size if it's easier, e.g. if your
     * backend defines page sizes. It is generally safer to increase the number loaded than reduce.
     * <p>
     * Data may be passed synchronously during the load method, or deferred and called at a
     * later time. Further loads going down will be blocked until the callback is called.
     * <p>
     * If data cannot be loaded (for example, if the request is invalid, or the data would be stale
     * and inconsistent, it is valid to call {@link #invalidate()} to invalidate the data source,
     * and prevent further loading.
     *
     * @param params   Parameters for the load, including the key for the new page, and requested load
     *                 size.
     * @param callback Callback that receives loaded data.
     */
    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Hits> callback) {

    }

    /**
     * Append page with the key specified by {@link LoadParams#key LoadParams.key}.
     * <p>
     * It's valid to return a different list size than the page size if it's easier, e.g. if your
     * backend defines page sizes. It is generally safer to increase the number loaded than reduce.
     * <p>
     * Data may be passed synchronously during the load method, or deferred and called at a
     * later time. Further loads going down will be blocked until the callback is called.
     * <p>
     * If data cannot be loaded (for example, if the request is invalid, or the data would be stale
     * and inconsistent, it is valid to call {@link #invalidate()} to invalidate the data source,
     * and prevent further loading.
     *
     * @param params   Parameters for the load, including the key for the new page, and requested load
     *                 size.
     * @param callback Callback that receives loaded data.
     */
    @Override
    public void loadAfter(final @NonNull LoadParams<Integer> params, final @NonNull LoadCallback<Integer, Hits> callback) {

        ApiInterface api = RetrofitClient.getInstance().create(ApiInterface.class);

        api.fetchData(KEY,params.key,5).enqueue(new Callback<ImageDataResponse>() {
            @Override
            public void onResponse(Call<ImageDataResponse> call, Response<ImageDataResponse> response) {
                if (response.body() != null && true) {
                    callback.onResult(response.body().getHitsList(), params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<ImageDataResponse> call, Throwable t) {

            }
        });
    }
}
