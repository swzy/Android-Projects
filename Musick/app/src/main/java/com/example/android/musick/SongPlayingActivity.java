package com.example.android.musick;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.Serializable;

public class SongPlayingActivity extends Activity implements Serializable {
    Button mPause;
    Button mPlay;
    boolean mUserIsSeeking = false;
    private SeekBar seekBar;
    private PlayerAdapter playerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.song_playing);

        //Unpack bundle from MusicListActivity
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

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        initializeSeekbar();
        initializePlaybackController();

        mPause = (Button) findViewById(R.id.back_button);
        mPlay = (Button) findViewById(R.id.play_button);

        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerAdapter.pause();
            }
        });
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerAdapter.play();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        playerAdapter.loadMedia(R.raw.cotw);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (playerAdapter != null) playerAdapter.release();
    }

    private void initializeSeekbar() {
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int userSelectedPosition = 0;

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        mUserIsSeeking = true;
                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            userSelectedPosition = progress;
                        }
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mUserIsSeeking = false;
                        playerAdapter.seekTo(userSelectedPosition);
                    }
                });
    }

    private void initializePlaybackController() {
        MediaPlayerHolder mMediaPlayerHolder = new MediaPlayerHolder(this);
        mMediaPlayerHolder.setPlaybackInfoListener(new PlaybackListener());
        playerAdapter = mMediaPlayerHolder;
    }

    public class PlaybackListener extends PlaybackInfoListener {

        @Override
        public void onDurationChanged(int duration) {
            seekBar.setMax(duration);
        }

        @Override
        public void onPositionChanged(int position) {
            if (!mUserIsSeeking) {
                seekBar.setProgress(position, true);
            }
        }

        @Override
        public void onStateChanged(@State int state) {
            String stateToString = PlaybackInfoListener.convertStateToString(state);
            onLogUpdated(String.format("onStateChanged(%s)", stateToString));
        }

        @Override
        public void onPlaybackCompleted() {
        }
    }
}