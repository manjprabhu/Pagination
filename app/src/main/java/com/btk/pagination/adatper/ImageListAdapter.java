package com.btk.pagination.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.btk.pagination.R;
import com.btk.pagination.model.Hits;
import com.bumptech.glide.Glide;

public class ImageListAdapter extends PagedListAdapter<Hits, ImageListAdapter.ImageListViewHolder> {

    private Context mContext;
    public ImageListAdapter(Context context) {
        super(DIFF_UTILS);
        mContext = context;
    }

    @NonNull
    @Override
    public ImageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_singleitem,parent,false);
        return new ImageListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageListViewHolder holder, int position) {
        Hits hits = getItem(position);
        Glide.with(mContext).load(hits.getLargeImageURL()).into(holder.imageView);
    }

    class ImageListViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public ImageListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }

    private static final DiffUtil.ItemCallback<Hits>  DIFF_UTILS  = new DiffUtil.ItemCallback<Hits>() {
        @Override
        public boolean areItemsTheSame(@NonNull Hits oldItem, @NonNull Hits newItem) {
            return (oldItem.getId() == newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Hits oldItem, @NonNull Hits newItem) {
            return (oldItem.getLargeImageURL().equalsIgnoreCase(newItem.getLargeImageURL()));
        }
    };
}
