package com.madhadesgames.topquizz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.madhadesgames.topquizz.R;

public class GameActivity extends AppCompatActivity {

    private TextView mQuestion;
    private Button mAnswer1, mAnswer2, mAnswer3, mAnswer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // on reference les objets present dans le fichier xml de layout
        mQuestion = findViewById(R.id.activity_game_question_txt);
        mAnswer1 = findViewById(R.id.activity_game_answer1_btn);
        mAnswer2 = findViewById(R.id.activity_game_answer2_btn);
        mAnswer3 = findViewById(R.id.activity_game_answer3_btn);
        mAnswer4 = findViewById(R.id.activity_game_answer4_btn);
    }
}
