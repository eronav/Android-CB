package com.example.cb;

public class Guess {

    private String word;
    private int[] eval;

    public Guess (String word, int cows, int bulls) {
        this.word = word;
        eval[0] = cows;
        eval[1] = bulls;
    }

    public int getCows () {
        return eval[0];
    }
    public int getBulls () {
        return eval[1];
    }
    public String getWord () {
        return word;
    }

}
