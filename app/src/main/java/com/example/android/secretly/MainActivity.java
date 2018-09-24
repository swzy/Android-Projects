package com.example.android.secretly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Secretly! [ALPHA]");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String getMessage() {
        EditText editText = findViewById(R.id.message_entry);
        return String.valueOf(editText.getText());
    }
}
