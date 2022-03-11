package com.example.bullsandcows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //opening screen
    Button easy;
    Button medium;
    Button hard;
    Button gameInstructions;
    public static double record;// the record from last game
    public static String level;//the level that the user select
//    public static SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        easy = findViewById(R.id.buttonE);
        medium = findViewById(R.id.buttonM);
        hard = findViewById(R.id.buttonH);
        gameInstructions = findViewById(R.id.gameIntro);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String s1 = sp.getString("record", "0");
        record = Double.parseDouble(s1);//read the last record from shared preference and update in the variable
        final SharedPreferences.Editor myEdit = sp.edit();
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level="easy";
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);//go to play activity
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level="medium";
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);//go to play activity
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level="hard";
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);//go to play activity
            }
        });
        gameInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Introductions.class);
                startActivity(intent);//go to introductions
            }
        });
    }
}
