package com.example.android.bioquiz;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public char[] quizAns = {'C', 'C', 'B', 'T', 'B'};
    public char[] userAns = new char[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Switch case which records answer in empty array. Called when Q1 radio buttons are pressed.
    public void Q1(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.choiceA_Q1:
                if (checked) {
                    userAns[0] = 'A';
                    break;
                }
            case R.id.choiceB_Q1:
                if (checked) {
                    userAns[0] = 'B';
                    break;
                }
            case R.id.choiceC_Q1:
                if (checked) {
                    userAns[0] = 'C';
                    break;
                }
        }
    }

    //Switch case which records answer in empty array. Called when Q2 radio buttons are pressed.
    public void Q2(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.choiceA_Q2:
                if (checked) {
                    userAns[1] = 'A';
                    break;
                }
            case R.id.choiceB_Q2:
                if (checked) {
                    userAns[1] = 'B';
                    break;
                }
            case R.id.choiceC_Q2:
                if (checked) {
                    userAns[1] = 'C';
                    break;
                }
        }
    }

    //Switch case which records answer in empty array. Called when Q3 radio buttons are pressed.
    public void Q3(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.choiceA_Q3:
                if (checked) {
                    userAns[2] = 'A';
                    break;
                }
            case R.id.choiceB_Q3:
                if (checked) {
                    userAns[2] = 'B';
                    break;
                }
            case R.id.choiceC_Q3:
                if (checked) {
                    userAns[2] = 'C';
                    break;
                }
        }
    }

    //Switch case which records answer in empty array. Called when Q4 radio buttons are pressed.
    public void Q4(View view) {
        CheckBox checkBoxA = findViewById(R.id.choiceA_Q4);
        CheckBox checkBoxB = findViewById(R.id.choiceB_Q4);
        CheckBox checkBoxC = findViewById(R.id.choiceC_Q4);

        if (checkBoxA.isChecked() == true && checkBoxB.isChecked() == true && checkBoxC.isChecked() == false) {
            userAns[3] = 'T';
        }
        else {
            userAns[3] = 'F';
        }
    }




    //Switch case which records answer in empty array. Called when Q5 radio buttons are pressed.
    public void Q5(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.choiceA_Q5:
                if (checked) {
                    userAns[4] = 'A';
                    break;
                }
            case R.id.choiceB_Q5:
                if (checked) {
                    userAns[4] = 'B';
                    break;
                }
            case R.id.choiceC_Q5:
                if (checked) {
                    userAns[4] = 'C';
                    break;
                }
        }
    }

    //Compares array of user answers to answer bank and tallies score. Score is set as final message.
    public void submitQuiz(View view) {
        int score = 0;
        Q4(view);
        for (int i = 0; i < quizAns.length; i++) {
            if (userAns[i] == quizAns[i]) {
                score++;
            }
        }
        displayMessage(finalMessage(score));


        //Initialize view objects
        RadioButton q1 = findViewById(R.id.choiceC_Q1);
        RadioButton q2 = findViewById(R.id.choiceC_Q2);
        RadioButton q3 = findViewById(R.id.choiceB_Q3);
        CheckBox checkBoxA = findViewById(R.id.choiceA_Q4);
        CheckBox checkBoxB = findViewById(R.id.choiceB_Q4);
        RadioButton q5 = findViewById(R.id.choiceB_Q5);

        //Quiz shows what the correct answers were via highlighted green background.
        q1.setBackgroundColor(Color.GREEN);
        q2.setBackgroundColor(Color.GREEN);
        q3.setBackgroundColor(Color.GREEN);
        checkBoxA.setBackgroundColor(Color.GREEN);
        checkBoxB.setBackgroundColor(Color.GREEN);
        q5.setBackgroundColor(Color.GREEN);

        //Toast shows final score.
        Context context = getApplicationContext();
        String text = "Thanks for participating! You scored " + score + " out of 5!";
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.show();
    }

    public String finalMessage(int score) {
        String message = "Thanks for participating! You scored " + score + " out of 5!";
        return message;
    }

    public void displayMessage(String message) {
        TextView text = findViewById(R.id.finalMessage_text_view);
        text.setText(message);
    }
}
