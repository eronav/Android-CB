package com.example.cb;

public class KeyStrokeManager {

    KeyStrokeManager () {

    }

    private int countCharInString (String word, char letter) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                count += 1;
            }
        }
        return count;
    }

    public boolean isDouble (String previousWord, char nextLetter) {
        return (countCharInString(previousWord, nextLetter) > 1);
    }
}
