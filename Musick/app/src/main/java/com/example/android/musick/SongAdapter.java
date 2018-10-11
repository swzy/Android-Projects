package com.example.android.musick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> implements Serializable {

    public SongAdapter(Context context, ArrayList<Song> objects) {
        super(context, 0 , objects);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        Song songObject = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_item, parent, false);
        }
        ImageView image = (ImageView) convertView.findViewById(R.id.song_image);
            image.setImageResource(songObject.getmResourceID());

        TextView name = (TextView) convertView.findViewById(R.id.song_name);
            name.setText(songObject.getSongName());

        TextView artist = (TextView) convertView.findViewById(R.id.artist_name);
            artist.setText(songObject.getArtist());

        TextView length = (TextView) convertView.findViewById(R.id.song_length);
            length.setText(songObject.getSongLength());

        return convertView;
    }
}
