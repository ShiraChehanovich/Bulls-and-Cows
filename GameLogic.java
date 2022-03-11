package com.example.bullsandcows;

import android.content.Context;
import android.widget.Toast;


public class GameLogic {
    public int[] computerLine;//The balls that the computer selected randomly
    public static double score;//mark for current game

    public GameLogic() {
        score = 100 + (Main2Activity.levelUp * 5);//the highest score
        computerLine = computerSelectLine();//initialize the balls
    }

    public double getScore() {
        return score;
    }

    private int[] computerSelectLine() {//The computer will select a row of random numbers that user must guess‏

        int[] temp = new int[4];
        int next;
        for (int i = 0; i < 4; i++) {
            next = (int) ((Math.random() * (Main2Activity.levelUp)) + 1);//
            temp[i] = next;//update to array
        }

        return temp;
    }

    public void calcLine(LinePlay l, int index_line) throws GameOverException, MissingDataException, WinException {
        //calculate mark for Received line

        if (!validity(l))//if the user didn't complete the line
            throw new MissingDataException();
        else {
            int flag = 0;//sum of bulls+cows founded
            boolean[] user = new boolean[4];//An array of flags that expresses the user's line
            boolean[] comp = new boolean[4];//An array of flags that expresses the computer's line
            for (int i = 0; i < 4; i++) {
                if (l.balls[i].getColor() == computerLine[i]) {//checks if place i is a bull
                    l.sing[flag++].setColor(7);//set the ball's color in place flag to black, then add 1 to flag
                    user[i] = true;//update user array in index i to true
                    comp[i] = true;//update computer array in index i to true( found)
                    l.setBulls(l.getBulls() + 1);//add 1 to bulls of line
                }
            }
            for (int j = 0; j < 4; j++) {//look for cows
                if (!user[j])//if the current index is not a bull
                    for (int i = 0; i < 4; i++) {
                        if (!comp[i] && l.balls[j].getColor() == computerLine[i]) {//if the current index is not a bull and the color appears
                            l.sing[flag++].setColor(6);//set the ball's color in place flag to gray, then add 1 to flag
                            user[j] = true;
                            comp[i] = true;
                            l.setCows(l.getCows() + 1);//add 1 to sum of cows in line
                            break;
                        }
                    }
            }
            if (l.getBulls() == 4) {//the user success
                throw new WinException();
            }
            score -= (100 + Main2Activity.levelUp * 5) / 8;//decrease from the score
            if (index_line == 8)//the user didn't guess and he tried 8 times
                throw new GameOverException();
        }
    }

    private boolean validity(LinePlay l) {//check if the user filled all the line when pressed "check" button
        for (int i = 0; i < 4; i++) {
            if (l.balls[i].getColor() == 0)//if the color of one place still white
                return false;
        }
        return true;
    }


}
