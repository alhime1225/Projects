package com.example.music.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music.R;
import com.example.music.model.Music;
import com.example.music.utilities.MyItemClickListener;
import com.example.music.utilities.RecyclerItemClickListener;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private ArrayList<Music> dataList;
    private RecyclerItemClickListener recyclerItemClickListener;

    public MusicAdapter(ArrayList<Music> dataList , RecyclerItemClickListener recyclerItemClickListener) {
        this.dataList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }


    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.music_row_circular, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.title.setText(dataList.get(position).getTrackName());
        holder.artistName.setText(dataList.get(position).getArtistName());
        holder.price.setText(String.valueOf(dataList.get(position).getTrackPrice()));
      //        holder.txtNoticeFilePath.setText(dataList.get(position).getFileSource());
//        Glide.with(mContext)
//                .load(music.getArtworkUrl100())
//                .into( viewHolder.movieImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(dataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MusicViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
      //  public ImageView movieImage;
        public TextView artistName;
        public TextView price;



        public MusicViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.name);
           // movieImage = (ImageView) itemView.findViewById(R.id.img);
            artistName = (TextView) itemView.findViewById(R.id.description);
            price = (TextView) itemView.findViewById(R.id.price);

        }
    }
}
