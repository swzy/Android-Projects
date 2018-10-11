package com.example.android.musick;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MusicListActivity extends AppCompatActivity implements Serializable {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musiclist_activity);

        setFont();
        final ArrayList<Song> songList = new ArrayList<Song>();
        songList.add(new Song("Boo Hoo", "Ghost", "0:45", R.drawable.ghost));
        songList.add(new Song("Nay", "Horse", "0:50", R.drawable.cutehorse));
        songList.add(new Song("What", "Lil Jon", "0:56", R.drawable.liljon));

        SongAdapter adapter = new SongAdapter(this, songList);
        ListView songListViewer = (ListView) findViewById(R.id.listView);
        songListViewer.setAdapter(adapter);


        songListViewer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle extra = new Bundle();
                extra.putSerializable("objects", songList.get(position));

                Intent intent = new Intent(getBaseContext(), SongPlayingActivity.class);
                intent.putExtra("songs", extra);
                startActivity(intent);
            }
        });
    }

    void setFont() {
        TextView text = (TextView) findViewById(R.id.songLibrary_title);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        text.setTypeface(customFont);
    }
}