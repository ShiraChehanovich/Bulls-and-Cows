package com.example.bullsandcows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {//Score and record screen
    Button btnNewGame;
    TextView score, record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        score = findViewById(R.id.txtScore);
        record = findViewById(R.id.txtRecord);
        score.setText(score.getText() + " " + GameLogic.score);//write the score of the game to screen
        if (MainActivity.record < GameLogic.score) {//if the score bigger than the record
            record.setText(record.getText() + " " + GameLogic.score);//write the score in the record place
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor myEdit = sp.edit();
            myEdit.putString("record", String.valueOf(GameLogic.score));//update the record to the current score
            myEdit.commit();
        } else
            record.setText(record.getText() + " " + MainActivity.record);//write the last record
        btnNewGame = findViewById(R.id.buttonNg);
        btnNewGame.setOnClickListener(new View.OnClickListener() {//start a new game
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, MainActivity.class);//open the main activity
                startActivity(intent);
            }
        });
    }
}
