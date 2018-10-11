package com.example.android.musick;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImage();
        setFont();

        TextView entrance = (TextView)findViewById(R.id.nextScreen);
        entrance.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent musicList = new Intent(MainActivity.this, MusicListActivity.class);

                // Start the new activity
                startActivity(musicList);
            }
        });
    }

    void setFont() {
        TextView text = (TextView) findViewById(R.id.app_title);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        text.setTypeface(customFont);
    }

    void setImage() {
        ImageView mImageView;
        mImageView = (ImageView) findViewById(R.id.main_background);
        mImageView.setImageResource(R.drawable.musicnote);
    }
}
