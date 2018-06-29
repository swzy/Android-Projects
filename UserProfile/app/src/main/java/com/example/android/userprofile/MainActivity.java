package com.example.android.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nameTextView = (TextView) findViewById(R.id.name);
        TextView countryTextView = findViewById(R.id.country);
        TextView birthdayTextView = findViewById(R.id.birthday);

        nameTextView.setText("Sam");
        countryTextView.setText("USA");
        birthdayTextView.setText("November 17, 1992");

        ImageView profileImage = (ImageView) findViewById(R.id.profilePicture);
        profileImage.setImageResource(R.drawable.vasewithflowers);
    }
}
