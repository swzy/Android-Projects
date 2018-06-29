package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int scoreForTeamA = 0;
    int scoreForTeamB = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(scoreForTeamA);
        displayForTeamB(scoreForTeamB);
    }

    public void addSixPointsA(View view) {
        scoreForTeamA += 6;
        displayForTeamA(scoreForTeamA);
    }

    public void addFieldGoalA(View view) {
        scoreForTeamA += 3;
        displayForTeamA(scoreForTeamA);
    }

    public void addOnePointA(View view) {
        scoreForTeamA += 1;
        displayForTeamA(scoreForTeamA);
    }

    public void addTwoPointA(View view) {
        scoreForTeamA += 2;
        displayForTeamA(scoreForTeamA);
    }

    public void addSafetyA(View view) {
        scoreForTeamA += 2;
        displayForTeamA(scoreForTeamA);
    }

    public void addSixPointsB(View view) {
        scoreForTeamB += 6;
        displayForTeamB(scoreForTeamB);
    }

    public void addFieldGoalB(View view) {
        scoreForTeamB += 3;
        displayForTeamB(scoreForTeamB);
    }

    public void addOnePointB(View view) {
        scoreForTeamB += 1;
        displayForTeamB(scoreForTeamB);
    }

    public void addTwoPointB(View view) {
        scoreForTeamB += 2;
        displayForTeamB(scoreForTeamB);
    }

    public void addSafetyB(View view) {
        scoreForTeamB += 2;
        displayForTeamB(scoreForTeamB);
    }

    public void resetScores(View view) {
        scoreForTeamA = 0;
        scoreForTeamB = 0;
        displayForTeamA(scoreForTeamA);
        displayForTeamB(scoreForTeamB);
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

}
