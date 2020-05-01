package com.example.cb;

import java.util.Random;

public class WordGenerator {
    private String goal;
    private int culty;

    WordGenerator(int difficulty) {
        culty = difficulty;
        this.targetGen(difficulty);
    }

    public String getWord() {
        return goal;
    }
    public String targetGen(int diff) {
        goal = "";
        Random myrand = new Random();

        //culty = diff;
        try {
            for (int i = 0; i < culty;) {
                int r = myrand.nextInt(26) + 65;
                String r1 = String.valueOf((char) r);
                if (goal.contains(r1)) {
                    continue;
                } else {
                    goal += r1;
                    i++;
                }
            }
            // errbox.setText(goal);
            // diffbox.setText("");
            // diffbox.setHint("enter a word");

        } catch (NumberFormatException e){
            // errbox.setText(e.toString());
            goal="ERRWORD";
        }
        return goal;
    }

    public int[] evaluateGuess(String guess) {
        String errmsg;
        int[] result = new int[3];

        guess = guess.toUpperCase();
        if (guess.length() != goal.length()) {
            errmsg = "Wrong number of letters. Please enter a word with " + culty + " letters";
            result[0] = -1;
        } else {
            // Compare guess against target
            if (guess.equals(goal)) {
                errmsg = ("You are correct!");
                result[0] = 1;
                result[1] = 0;
                result[2] = guess.length();

            } else {
                int cows = 0;
                int bulls = 0;
                for (int i = 0; i < guess.length(); i++) {
                    int idx = goal.indexOf(guess.charAt(i));
                    if (i == idx) {
                        bulls += 1;
                    } else if (idx != -1) {
                        cows += 1;
                    } else {
                        // Nothing to do
                        //hksjfdhkusdfhjker shjk
                        ;
                    }
                }
                result[0] = 0;
                result[1] = cows;
                result[2] = bulls;
            }
        }
        return result;
    }
}
