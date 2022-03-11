package com.example.bullsandcows;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;


public class MyView extends View { //Game board design
    ArrayList<Ball2> row; //list of the colored balls
    public ArrayList<LinePlay> game; //list of all the lines
    Random rnd;
    Ball2 temp; //dragging ball
    Boolean win, isGameFinished; //win/stop game flag
    public GameLogic gl;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //initialize variables
        win = false;
        isGameFinished = false;
        gl = new GameLogic();
        int w = getWidth();
        int h = getHeight();
        int flag = 0;
        for (int i = 1; i <= Main2Activity.levelUp; i++) { //add colored balls as a  number of "levelUp"
            flag++;
            row.add(new Ball2((i * w / 8) - w / 16, h - 100, 50, flag));
        }
        for (int i = 1; i < 9; i++) { // add line to list of lines
            game.add(new LinePlay(i, w, h));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();

        for (Ball2 b : row) {//go over all the colored balls
            p.setColor(b.getRealColor());//set the color according to the color it has to be
            canvas.drawCircle(b.getX(), b.getY(), b.getRadius(), p);//draw the balls
        }
        for (int j = 0; j < 8; j++) {//go over the list of lines
            for (int i = 0; i < 4; i++) { //go over a line and draw the balls
                p.setColor(game.get(j).balls[i].getRealColor());
                canvas.drawCircle(game.get(j).balls[i].getX(), game.get(j).balls[i].getY(), game.get(j).balls[i].getRadius(), p);
                p.setColor(game.get(j).sing[i].getRealColor());//draw the mark balls
                canvas.drawCircle(game.get(j).sing[i].getX(), game.get(j).sing[i].getY(), game.get(j).sing[i].getRadius(), p);
            }
        }
        if (temp != null) { //if the user clicked one of the colored balls
            p.setColor(temp.getRealColor());
            canvas.drawCircle(temp.getX(), temp.getY(), temp.getRadius(), p); //duplicate the clicked ball

        }
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        row = new ArrayList<>();
        game = new ArrayList<>();
        rnd = new Random();
        temp = null;
        this.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!win && !isGameFinished) { //if this is not a win mode or the user stopped the game
                    float x = event.getX();
                    float y = event.getY();

                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        //if touch colored ball

                        for (Ball2 b : row) { //check if the user touched a colored ball
                            if (b.isIn(x, y)) {
                                temp = new Ball2(b.getX(), b.getY(), b.getRadius() + 10, b.getColor()); //update the data of the touched ball to "temp"
                                invalidate();
                            }
                        }
                    } else if (event.getAction() == MotionEvent.ACTION_MOVE && temp != null) { //if moved colored ball
                        temp.setX(x); //update the location
                        temp.setY(y);
                        invalidate();
                    } else if (event.getAction() == MotionEvent.ACTION_UP) { //If the user has released "temp"
                        for (int i = 0; i < 4; i++) { //check if "temp" has released in a empty enabled ball
                            if (game.get(Main2Activity.currentLine - 1).balls[i].isIn(x, y) && temp != null)
                                game.get(Main2Activity.currentLine - 1).balls[i].setColor(temp.getColor()); //set the color of the ball to "temp"'s color
                        }
                        temp = null; //delete "temp"
                        invalidate();
                    }
                }
                return true;

            }
        });


    }


}
