package com.example.genericquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class QuizHandler extends AppCompatActivity {
    Quiz currentQuiz;
    TextView mText;
    boolean answer;
    Button tButton, fButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        Toast toast = new Toast(this);
        int qNum = generateInt();

        ArrayList<Quiz> quizList = new ArrayList<>();
        quizList.add(new Quiz(1, "This is a simple question 1", true));
        quizList.add(new Quiz(2, "This is a simple question 2", false));
        quizList.add(new Quiz(3, "This is a simple question 3", false));

        currentQuiz = quizList.get(qNum);
        mText = (TextView) findViewById(R.id.quiz_question);
        mText.setText(currentQuiz.getQuestion());

        fButton = (Button) findViewById(R.id.fButton);
        tButton = (Button) findViewById(R.id.tButton);
        final Intent i = new Intent(this, QuizHandler.class);

        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("button:", "false");
                answer = false;
                checkAns(currentQuiz.getAns(), answer);
                startActivity(i);
            }
        });
        tButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("button:", "true");
                answer = true;
                checkAns(currentQuiz.getAns(), answer);
                startActivity(i);
            }
        });

        //Destroys activity
        finish();
    }

    public int generateInt() {
        Random r = new Random();
        return r.nextInt(3);
    }

    public boolean checkAns(boolean a, boolean b) {
        Toast toast = new Toast(this);
        Log.i("Function:", "Wee");
        if (a == b) {
            toast.makeText(QuizHandler.this, "Correct!",Toast.LENGTH_SHORT).show();
            return true;
        }
        toast.makeText(QuizHandler.this, "Incorrect.", Toast.LENGTH_SHORT).show();
        return false;
    }
}
