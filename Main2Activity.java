package com.example.bullsandcows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {//The game board
Button btnStopGame;
Button btnCheck;
MyView mv;
    public static int levelUp;//number of balls according to the selected level
    public static int currentLine=1;//the current empty line in the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnStopGame=findViewById(R.id.buttonst);
        btnCheck=findViewById(R.id.buttonCheck);
        mv=findViewById(R.id.myView);
        switch (MainActivity.level){
            //the number means number of colored balls
            case "easy":levelUp=5;break;
            case "medium":levelUp=6;break;
            case "hard":levelUp=7;break;
        }
        btnStopGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.isGameFinished=true;//The user presses the button, the game is not over
                btnCheck.setEnabled(false);//disable button (if the user opens the activity again)
                currentLine=1;//initialize the current number for the next game
                if(btnCheck.getVisibility()!=View.GONE){//check if the game ended
                GameLogic.score=0;}//initialize score to 0
                Intent intent= new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);//open the end activity

            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mv.gl.calcLine(mv.game.get(currentLine - 1), currentLine);//give mark for line
                }catch (GameOverException e){//The user reached the end of the lines and could not guess
                    Toast.makeText(Main2Activity.this,"Game over",Toast.LENGTH_LONG).show();
                    btnCheck.setVisibility(View.GONE);
                    btnStopGame.setText("new game");
                }catch (MissingDataException e){//the user didn't fill the entire row
                    currentLine--;//go back to the line to try again
                    Toast.makeText(Main2Activity.this,"Complete the entire row of balls",Toast.LENGTH_LONG).show();
                }catch (WinException e){//The user correctly guessed all the balls
                    Toast.makeText(Main2Activity.this,"You win!! your score: "+mv.gl.getScore(),Toast.LENGTH_LONG).show();
                    mv.win=true;
                    btnCheck.setVisibility(View.GONE);
                    btnStopGame.setText("new game");
                }
                mv.invalidate();
                currentLine++;//enable the next line
            }
        });
    }
}
