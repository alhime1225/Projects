package com.example.music;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.music.model.Music;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.TextView;

public class MusicDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_details);

     Music music= (Music) getIntent().getExtras().get("MUSIC");

        ImageView img2 = findViewById(R.id.img2);

        Glide.with(MusicDetailsActivity.this)
                .load(music.getArtworkUrl100())
                .into( img2);

        TextView lbArtistName = findViewById(R.id.lbArtistName);
        lbArtistName.setText("By "+music.getArtistName());

        TextView lbArtistTrack = findViewById(R.id.lbArtistTrack);
        lbArtistTrack.setText(music.getTrackName());

        TextView lbLongDesription = findViewById(R.id.lbLongDescription);
        lbLongDesription.setText(music.getLongDescription());

        TextView lblYear = findViewById(R.id.lblYear);
        lblYear.setText("Release date: " + music.getReleaseDate());
//
//        TextView lblMin = findViewById(R.id.lblMinutes);
//        lblMin.setText((int) music.getTrackTimeMillis());

    }
}