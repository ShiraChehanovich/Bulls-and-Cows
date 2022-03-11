package com.example.bullsandcows;

import android.graphics.Color;

public class Ball2 {//represent a ball
    int x, y, radius, color;

    public Ball2(int x, int y, int radius, int color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public boolean isIn(float x1, float y1) {
        double dist = Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2));
        if (dist <= radius)
            return true;
        return false;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }

    public int getRealColor() {
        switch (color) {
            case 0:
                return Color.WHITE;
            case 1:
                return Color.RED;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.BLUE;
            case 5:
                return Color.MAGENTA;
            case 6:
                return Color.GRAY;
            case 7:
                return Color.BLACK;
        }
        ;
        return -1;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(float x1) {
        x = (int) x1;
    }

    public void setY(float y1) {
        y = (int) y1;
    }
}