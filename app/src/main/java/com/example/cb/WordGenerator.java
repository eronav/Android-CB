package com.example.cb;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordGenerator {
    private String goal;
    private int culty;
    private Context myctxt;


    WordGenerator(Context ctxt, int difficulty) {
        culty = difficulty;
        myctxt = ctxt;
    }

    public String getWord() {
        this.targetGen(culty);
        return goal;
    }

    private String targetGenRandom (int diff) {

        goal = "";
        Random myrand = new Random();
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

    public int getHint (String word, boolean[] posArray, int diff, boolean[] hintArray) {
        Random myrand = new Random();
        int randIdx = myrand.nextInt(diff);
        while (posArray[randIdx] == true) {
            randIdx = myrand.nextInt(diff);
        }
        posArray[randIdx] = true;
        hintArray[randIdx] = true;
        char randChr = word.charAt(randIdx);
        return (randIdx << 8) | ((int) randChr);
    }

    public String targetGen(int diff) {
        goal = targetGenFromDict(diff).toLowerCase();
        return goal;
    }

    public List<String> getWordList(String path) {
        List<String> mLines = new ArrayList<>();

        AssetManager am = myctxt.getAssets();

        try {
            InputStream is = am.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null)
                mLines.add(line);
        } catch (IOException e) {
            // ForDebug e.printStackTrace();
        }

        return mLines;
    }

    private void print (List<String> words) {
        for (int i = 0; i < 1; i++) {
            String word = words.get(i);
            System.out.println(word);
        }
    }

    public String targetGenFromDict (int diff){
        String fileToUse;
        List<String> words;

        boolean double_letters = GameEnvironment.ups.IsDupsOn();

        // Build the name of the file that we should be using
        // Format: "word" + <number-of-letters> + "[Double]" + ".txt"
        fileToUse = "word";
        fileToUse += diff;
        if (double_letters) {
            fileToUse += "Double";
        }
        fileToUse += ".txt";

        words = getWordList(fileToUse);
        int myLength = words.size();

        if (myLength > 0) {
            Random myrand = new Random();
            int randIdx = myrand.nextInt(myLength);
            String word = words.get(randIdx);
            return word;
        }
        return "ERROR";
    }

    public int[] evaluateGuess(String guess) {
        String errmsg;
        int[] result = new int[3];

        guess = guess.toLowerCase();
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
