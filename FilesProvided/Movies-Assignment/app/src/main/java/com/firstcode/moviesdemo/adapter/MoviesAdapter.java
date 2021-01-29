package com.firstcode.moviesdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firstcode.cakedemo.R;
import com.firstcode.moviesdemo.model.MoviesModel;
import com.firstcode.moviesdemo.utilities.ItemClickListener;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{

    private List<MoviesModel> moviesList;
    private int rowLayout;
    private Context mContext;

    public MoviesAdapter(List<MoviesModel> cakesList, int rowLayout, Context context) {
        this.moviesList = cakesList;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final MoviesModel movies = moviesList.get(i);
        viewHolder.cakeName.setText(movies.getOriginal_title());
        Glide.with(mContext)
                .load(movies.getImage())
                .into( viewHolder.cakeImage);
        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(mContext, "#" + position + " - " + movies.getOriginal_title() + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "#" + position + " - " + movies.getOriginal_title(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList == null ? 0 : moviesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView cakeName;
        public ImageView cakeImage;
        public String versionName;
        private ItemClickListener clickListener;
        public ViewHolder(View itemView) {
            super(itemView);
            cakeName = (TextView) itemView.findViewById(R.id.name);
            cakeImage = (ImageView)itemView.findViewById(R.id.img);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }
        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }

    }
}
