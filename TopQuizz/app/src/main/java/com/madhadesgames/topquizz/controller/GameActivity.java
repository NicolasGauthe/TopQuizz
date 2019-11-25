package com.madhadesgames.topquizz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.madhadesgames.topquizz.R;
import com.madhadesgames.topquizz.model.Question;
import com.madhadesgames.topquizz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mQuestion;
    private Button mAnswer1, mAnswer2, mAnswer3, mAnswer4;
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private int mNumberOfQuestion;
    private int mScore;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionBank = this.generateQuestions();
        mNumberOfQuestion = 5;
        mScore = 0;


        // on reference les objets present dans le fichier xml de layout
        mQuestion = findViewById(R.id.activity_game_question_txt);
        mAnswer1 = findViewById(R.id.activity_game_answer1_btn);
        mAnswer2 = findViewById(R.id.activity_game_answer2_btn);
        mAnswer3 = findViewById(R.id.activity_game_answer3_btn);
        mAnswer4 = findViewById(R.id.activity_game_answer4_btn);

        mAnswer1.setOnClickListener(this);
        mAnswer2.setOnClickListener(this);
        mAnswer3.setOnClickListener(this);
        mAnswer4.setOnClickListener(this);

        mAnswer1.setTag(0);
        mAnswer2.setTag(1);
        mAnswer3.setTag(2);
        mAnswer4.setTag(3);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }


    private void displayQuestion(final Question pQuestion) {
        mQuestion.setText(pQuestion.getQuestion());
        mAnswer1.setText(pQuestion.getChoiceList().get(0));
        mAnswer2.setText(pQuestion.getChoiceList().get(1));
        mAnswer3.setText(pQuestion.getChoiceList().get(2));
        mAnswer4.setText(pQuestion.getChoiceList().get(3));

    }


    private QuestionBank generateQuestions() {
        Question question1 = new Question("What is the name of the current french president?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1);

        Question question2 = new Question("How many countries are there in the European Union?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        Question question3 = new Question("Who is the creator of the Android operating system?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Question question4 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Question question6 = new Question("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Question question8 = new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);
        Question question10 = new Question("Which actor is the lead singer of a famous American band \"30 Seconds to Mars\"?",
                Arrays.asList("Jared Leto", "Tom Cruise", "Ryan Reynolds", "Will Smith"), 0);
        Question question11 = new Question("Who is the most famous Hemsworth brother?",
                Arrays.asList("James", "Chris", "Peter", "Jean-Claude"),1);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9,
                question10,
                question11));
    }

    @Override
    public void onClick(View v) {
        int reponseIndex = (int)v.getTag();
        if(reponseIndex == mCurrentQuestion.getAnswerIndex()) {
            // Good Answer !
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;
        }else {
            // Bad Answer....
            Toast.makeText(this, "Bad answer !!! ahahahah", Toast.LENGTH_SHORT).show();
        }


        if(--mNumberOfQuestion == 0) {
            //end the game !
            endGame();
        }else {
            mCurrentQuestion = mQuestionBank.getQuestion();
            displayQuestion(mCurrentQuestion);
        }

    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Good Job !!")
                .setMessage("your score is : " +mScore)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }
}
