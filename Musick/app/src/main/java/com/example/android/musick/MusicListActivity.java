package com.example.android.musick;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileDescriptor;
import java.io.Serializable;
import java.util.ArrayList;

public class MusicListActivity extends AppCompatActivity implements Serializable {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musiclist_activity);

        setFont();
        //Parses METADATA to ms then back to minutes:seconds
        String song1 = findSongLength();
        String songFormat = parseIntString(Integer.parseInt(song1));

        final ArrayList<Song> songList = new ArrayList<Song>();
        songList.add(new Song("Boo Hoo", "Ghost", songFormat, R.drawable.ghost));
        songList.add(new Song("Nay", "Horse", songFormat, R.drawable.cutehorse));
        songList.add(new Song("What", "Lil Jon", songFormat, R.drawable.liljon));

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

    private String findSongLength() {
        Uri mediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cotw);
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(this, mediaPath);
        return mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
    }

    private String parseIntString(long in) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (in / (1000 * 60 * 60));
        int minutes = (int) (in % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((in % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        //      return  String.format("%02d Min, %02d Sec",
        //                TimeUnit.MILLISECONDS.toMinutes(milliseconds),
        //                TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
        //                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)));

        // return timer string
        return finalTimerString;
    }
}