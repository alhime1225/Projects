package com.example.music.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music.MainActivity;
import com.example.music.MusicDetailsActivity;
import com.example.music.R;
import com.example.music.model.MusicModel;
import com.example.music.utilities.MyItemClickListener;
import com.google.android.material.textfield.TextInputEditText;


import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private  List<MusicModel> musicListFiltered;
    private List<MusicModel> musicList;
    private int rowLayout;
    private Context mContext;
    //private ViewHolder.MusicAdapterListener listener;


    public MusicAdapter(List<MusicModel> musicModelList, int rowLayout, Context context) {
        this.musicList = musicModelList;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.musicListFiltered = musicModelList;

        //this.listener = listener;


    }




    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    musicListFiltered = musicList;
                } else {
                    List<MusicModel> filteredList = new ArrayList<>();
                    for (MusicModel row : musicList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTrackName().toLowerCase().contains(charString.toLowerCase()) ) {
                            filteredList.add(row);
                        }
                    }

                    musicListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = musicListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                musicListFiltered = (ArrayList<MusicModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final MusicModel music = musicList.get(i);
//        Editable txtSearch = searchBar.getText();
//
//        if(txtSearch != null) {
//            String searchKey = txtSearch.toString();
//
//                if (!searchKey.isEmpty() && !music.getTrackName().toLowerCase().contains(searchKey.toLowerCase())) {
//                    return;
//
//            }
//        }
            viewHolder.itemView.setBackgroundColor(Color.BLACK);
            viewHolder.title.setText(music.getTrackName());
            viewHolder.artistName.setText(music.getArtistName());
            viewHolder.price.setText("$"+(double)music.getTrackPrice());



            DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL);
            divider.setDrawable(mContext.getResources().getDrawable(R.drawable.divider_test));
            Glide.with(mContext)
                    .load(music.getArtworkUrl100())
                    .into( viewHolder.movieImage);
            viewHolder.setClickListener(new MyItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {


                    if (isLongClick) {
                        Toast.makeText(mContext, "#" + position + " - " + music.getArtistName() + " (Long click)", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "#" + position + " - " + music.getArtistName(), Toast.LENGTH_SHORT).show();
                    }

                    Intent intent= new Intent(mContext, MusicDetailsActivity.class);
                    intent.putExtra("MUSIC",music);
                    ((MainActivity)mContext).startActivity(intent);
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
        private MyItemClickListener clickListener;



        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.name);
            movieImage = (ImageView)itemView.findViewById(R.id.img);
            artistName =(TextView) itemView.findViewById(R.id.description);
            price = (TextView) itemView.findViewById(R.id.price);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    //listener.onContactSelected(musicListFiltered.get(getAdapterPosition()));
                }
            });


        }
        public void setClickListener(MyItemClickListener itemClickListener) {
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

        public interface MusicAdapterListener {
            void onMusicModelSelected(MusicModel contact);
        }

    }
}
