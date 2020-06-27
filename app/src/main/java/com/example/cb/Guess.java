package com.example.cb;

public class Guess {

    private String word;
    private int[] eval = new int[3];

    public Guess (String word, int cows, int bulls) {
        this.word = word;
        eval[0] = 0;
        eval[1] = cows;
        eval[2] = bulls;
    }

    public int getCows () {
        return eval[1];
    }
    public int getBulls () {
        return eval[2];
    }
    public int[] getEval() { return eval; }
    public String getWord () {
        return word;
    }

}
