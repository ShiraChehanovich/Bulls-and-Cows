package com.example.bullsandcows;

public class GameOverException extends Exception {
    public GameOverException(){
        super();
    }
    public GameOverException(String msg){
        super(msg);
    }
}
