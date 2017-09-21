package com.example.wendt.truefalsequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private int userScore;
    private TextView userScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        wireWidgets();
        getMainIntent();
        setText();
    }

    private void setText() {
        userScoreTextView.setText(userScore);
    }

    private void getMainIntent() {
        Intent resultsScreen = getIntent();
        userScore = resultsScreen.getIntExtra(MainActivity.EXTRA_SCORE_MESSAGE, 0);
    }

    private void wireWidgets() {
        userScoreTextView = (TextView) findViewById(R.id.text_score);
    }
}
