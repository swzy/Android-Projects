package com.example.android.musick;

import java.io.Serializable;

public class Song implements Serializable {
    private String songName;
    private String artist;
    private String songLength;
    private int mResourceID;

    Song(String name, String artist, String length, int mResourceID) {
        this.songName = name;
        this.artist = artist;
        this.songLength = length;
        this.mResourceID = mResourceID;
    }

    public String getSongName() {return songName;}
    public String getArtist() {return artist;}
    public String getSongLength() {return songLength;}
    public int getmResourceID() {return mResourceID;}
}
