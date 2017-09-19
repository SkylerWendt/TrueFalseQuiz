package com.example.wendt.truefalsequiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button trueButton, falseButton, nextButton;
    private TextView questionText;
    private List<Question> questionBank;
    private int questionIndex = 0; //question user is currently on

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
        initQuestionBank();
        questionText.setText(questionBank.get(0).getQuestionText()); //loads first question
    }

    private void initQuestionBank() {
        questionBank = new ArrayList<>();
        questionBank.add(new Question(getString(R.string.question_nonsense), true));
        questionBank.add(new Question(getString(R.string.question_two), true));
        questionBank.add(new Question(getString(R.string.question_pineapple_pizza), false));
    }

    private void setListeners() {
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    private void wireWidgets() {
        trueButton = (Button) findViewById(R.id.button_true);
        falseButton = (Button) findViewById(R.id.button_false);
        nextButton = (Button) findViewById(R.id.button_next);
        questionText = (TextView) findViewById(R.id.text_question);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_true:
                checkAnswer(true);
                break;
            case R.id.button_false:
                checkAnswer(false);
                break;
            case R.id.button_next:
                advanceToNextQuestion();
                break;
            default:
        }
    }

    /**
     * Let's compare the users answer with the real answer with help of the Question class's own checkAnswer method
     * @param userAnswer what the user selected
     */
    private void checkAnswer(boolean userAnswer) {
        if(questionBank.get(questionIndex).checkAnswer(userAnswer)){ //if the user answer is true
            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
        }
        else{ //else user answer is false
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
        }
        disableTrueFalseButtons();
    }

    /**
     * Sets text to the new question, looping back if all questions have been seen
     */
    private void advanceToNextQuestion() {
        if(questionIndex < questionBank.size()-1){
            questionIndex++;
        }
        else{
            questionIndex = 0;
        }
        questionText.setText(questionBank.get(questionIndex).getQuestionText());
    }

    private void enableTrueFalseButtons(){
        trueButton.setEnabled(true);
        falseButton.setEnabled(true);
    }

    private void disableTrueFalseButtons() {
        trueButton.setEnabled(false);
        falseButton.setEnabled(false);
    }
}
