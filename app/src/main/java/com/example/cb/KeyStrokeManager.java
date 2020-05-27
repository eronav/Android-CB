package com.example.cb;

import android.view.KeyEvent;

public class KeyStrokeManager {

    KeyStrokeManager () {

    }

    public char getCharForKeycode (int keycode, KeyEvent event) {
        if (KeyEvent.KEYCODE_A <= keycode && keycode <= KeyEvent.KEYCODE_Z) {
            return (char) (keycode + (int) 'a' - KeyEvent.KEYCODE_A);
        } else if (KeyEvent.KEYCODE_0 <= keycode && keycode <= KeyEvent.KEYCODE_9) {
            return (char) (keycode + (int) '0' - KeyEvent.KEYCODE_0);
        } else {
            return ' ';
        }
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
