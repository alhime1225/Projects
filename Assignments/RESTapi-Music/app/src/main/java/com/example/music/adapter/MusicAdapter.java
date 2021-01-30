package com.example.music.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music.R;
import com.example.music.model.MusicModel;
import com.example.music.utilities.ItemClickListener;


import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private List<MusicModel> musicList;
    private int rowLayout;
    private Context mContext;

    public MusicAdapter(List<MusicModel> movieList, int rowLayout, Context context) {
        this.musicList = movieList;
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
        final MusicModel music = musicList.get(i);
            viewHolder.itemView.setBackgroundColor(Color.BLACK);
            viewHolder.title.setText(music.getTrackName());
            viewHolder.artistName.setText(music.getArtistName());
            viewHolder.price.setText("$"+(double)music.getTrackPrice());
            DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL);
            divider.setDrawable(mContext.getResources().getDrawable(R.drawable.divider_test));
            Glide.with(mContext)
                    .load(music.getArtworkUrl100())
                    .into( viewHolder.movieImage);
            viewHolder.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    if (isLongClick) {
                        Toast.makeText(mContext, "#" + position + " - " + music.getArtistName() + " (Long click)", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "#" + position + " - " + music.getArtistName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });


    //  viewHolder.movieImage.setBackgroundResource(R.drawable.bg_circle);
//     viewHolder.movieImage.setOutlineProvider(ViewOutlineProvider.BACKGROUND);


    }

    @Override
    public int getItemCount() {
        return musicList == null ? 0 : musicList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView title;
        public ImageView movieImage;
        public TextView artistName;
        public TextView price;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.name);
            movieImage = (ImageView)itemView.findViewById(R.id.img);
            artistName =(TextView) itemView.findViewById(R.id.description);
            price = (TextView) itemView.findViewById(R.id.price);
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
