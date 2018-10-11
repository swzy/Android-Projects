package com.example.android.musick;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.Serializable;

public class SongPlayingActivity extends Activity implements Serializable {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.song_playing);


        Bundle extra = getIntent().getBundleExtra("songs");
        Song song = (Song) extra.getSerializable("objects");

        TextView songName = (TextView) findViewById(R.id.song_name);
        songName.setText((song.getSongName()));

        TextView artistName = (TextView) findViewById(R.id.artist_name);
        artistName.setText(((song.getArtist())));

        TextView songLength = (TextView) findViewById(R.id.song_length);
        songLength.setText(((song.getSongLength())));

        ImageView songImage = (ImageView) findViewById(R.id.song_image);
        songImage.setImageResource(((song.getmResourceID())));
    }
}