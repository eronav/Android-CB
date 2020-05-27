package com.example.cb;

public class HintManager {

    private char[] hintPos;
    private int diff;
    private static  char blank_char = '_';
    private static char EMPTY_CHAR = 0;

    HintManager (int diff) {
        hintPos = new char[diff];
        for (int i = 0; i < diff; i++) {
            hintPos[i] = 0;
        }
        this.diff = diff;
    }

    public void reset() {
        for (int i = 0; i < diff; i++)
            hintPos[i] = EMPTY_CHAR;
    }

    public boolean hasCharAt (int pos) {
        return (hintPos[pos] != EMPTY_CHAR);
    }

    public boolean hasChar (char c) {
        for (int i = 0; i < diff; i++) {
            if (hintPos[i] == c) {
                return true;
            }
        }
        return false;
    }

    public int num_of_hints (int startingPos) {
        int numhints = 0;
        for (int i = startingPos; i < diff; i++) {
            numhints += (hasCharAt(i) ? 1 : 0);
        }
        return numhints;
    }

    public void setLetter (int pos, char letter) {
        hintPos[pos] = letter;
    }

    public void deleteLetter (int pos) {
        hintPos[pos] = EMPTY_CHAR;
    }

    public char getLetter (int pos) {
        return (hasCharAt(pos) ? hintPos[pos] : blank_char);
    }

    public int delLast() {
        for (int i = diff-1; i >= 0 ; i--) {
            if (hasCharAt(i)) {
                deleteLetter(i);
                return i;
            }
        }
        return -1;
    }

    public int getHighestPos() {
        for (int i = diff - 1; i >= 0; i--) {
            if (hasCharAt(i)) {
                return i;
            }
        }

        return -1;
    }

    public int getNextPos(int startingPos) {
        for (int i = startingPos; i < diff; i++) {
            if (! hasCharAt(i)) {
                return i;
            }
        }

        return diff;
    }

    public String addToString(String ins) {
        String s = "";
        for (int i=0; i < diff; i++) {
            if (hasCharAt(i))
                s += String.valueOf(hintPos[i]);
            else if (i < ins.length())
                s += String.valueOf(ins.charAt(i));
            else
                s += String.valueOf(blank_char);
        }

        return s;
    }

    public void getPositions(boolean[] posarray, boolean reset) {
        for (int i=0; i < diff; i++) {
            if (hasCharAt(i))
                posarray[i] = true;
            else if (reset)
                posarray[i] = false;
        }
    }

    public int populate_gbox (GuessInputBox gbox, LetterImageManager lim) {
        int numhints = 0;
        for (int i=0; i < diff; i++) {
            if (hasCharAt(i)) {
                gbox.setImageAt(i, lim.getLetter(hintPos[i]));
                numhints++;
            }
        }
        return numhints;
    }

}
