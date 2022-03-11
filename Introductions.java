package com.example.bullsandcows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Introductions extends AppCompatActivity {//introductions of the game
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductions);
        btnBack=findViewById(R.id.cmdBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//back to the opening screen
                Intent intent=new Intent(Introductions.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
