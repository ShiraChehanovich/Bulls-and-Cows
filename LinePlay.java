package com.example.bullsandcows;


public class LinePlay {//represents one line in the game
    public Ball2[] balls;//line of balls
    public Ball2[] sing;//4 balls for mark
    int bulls;//num of bulls- (right color right place)
    int cows;//num of cows- (right color wrong place)

    public int getCows() {
        return cows;
    }

    public void setCows(int rows) {
        this.cows = rows;
    }

    public int getBulls() {
        return bulls;
    }

    public void setBulls(int bulls) {
        this.bulls = bulls;
    }

    public LinePlay(int index_line, int w, int h) {
        bulls = 0;
        cows = 0;
        balls = new Ball2[4];
        sing = new Ball2[4];
        for (int i = 1; i <= 4; i++) {
            balls[i - 1] = new Ball2((i * w / 5) - w / 10, (index_line * h / 9) - h / 18, h / 30, 0);//initialize ball data
            sing[i - 1] = new Ball2((int) (w * (17.0 / 20.0 + ((i - 1) % 2) / 14.0)), (int) (h * (1 / 26.0 + ((i - 1) / 2) / 28.0 + (index_line - 1) / 9.0)), (int) h / 80, 0);
        }
    }
}
