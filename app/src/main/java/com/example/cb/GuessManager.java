package com.example.cb;

public class GuessManager {

    private char[] guessPos;
    private char[] hintPos;
    private int diff;
    private static  char blank_char = '_';
    private static char EMPTY_CHAR = 0;

    GuessManager(int diff) {
        guessPos = new char[diff];
        hintPos = new char[diff];
        for (int i = 0; i < diff; i++) {
            guessPos[i] = EMPTY_CHAR;
            hintPos[i] = EMPTY_CHAR;
        }
        this.diff = diff;
    }

    public void reset() {
        for (int i = 0; i < diff; i++)
            guessPos[i] = EMPTY_CHAR;
    }

    public void resetHint() {
        for (int i = 0; i < diff; i++)
            hintPos[i] = EMPTY_CHAR;
    }

    public boolean hasCharAt (int pos) {
        return (guessPos[pos] != EMPTY_CHAR);
    }

    public boolean getCharAt (int pos, char[] c) {
        c[0] = guessPos[pos];
        return (guessPos[pos] != EMPTY_CHAR);
    }


    public boolean getHintAt (int pos, char[] c) {
        c[0] = hintPos[pos];
        return (hintPos[pos] != EMPTY_CHAR);
    }

    public boolean hasHintAt (int pos) {
        return (hintPos[pos] != EMPTY_CHAR);
    }

    public boolean isFilledAt(int pos) {
        return (hasCharAt(pos) || hasHintAt(pos));
    }

    public boolean hasChar (char c) {
        for (int i = 0; i < diff; i++) {
            if (guessPos[i] == c) {
                return true;
            }
        }
        return false;
    }

    public boolean hasHint (char c) {
        for (int i = 0; i < diff; i++) {
            if (hintPos[i] == c) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCharacter(char c) {
        for (int i = 0; i < diff; i++) {
            if (guessPos[i] == c || hintPos[i] == c) {
                return true;
            }
        }
        return false;
    }

    public int num_of_chars (int startingPos) {
        int numchars = 0;
        for (int i = startingPos; i < diff; i++) {
            numchars += (hasCharAt(i) ? 1 : 0);
        }
        return numchars;
    }


    public int num_of_hints (int startingPos) {
        int numhints = 0;
        for (int i = startingPos; i < diff; i++) {
            numhints += (hasHintAt(i) ? 1 : 0);
        }
        return numhints;
    }

    public void setGuess (int pos, char letter) {
        guessPos[pos] = letter;
    }

    public void setHint (int pos, char letter) {
        hintPos[pos] = letter;
    }

    public void deleteLetter (int pos) {
        guessPos[pos] = EMPTY_CHAR;
    }

    public void deleteHint (int pos) {
        hintPos[pos] = EMPTY_CHAR;
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
    
    public int delLastHint() {
        for (int i = diff-1; i >= 0 ; i--) {
            if (hasHintAt(i)) {
                deleteHint(i);
                return i;
            }
        }
        return -1;
    }

    public int getNextAvailPos () {
        for (int i = 0; i < diff; i++) {
            if (!hasCharAt(i) && !hasHintAt(i)) {
                return i;
            }
        }
        return -1;
    }

    public int getNextAvailPoss() {
        // To return the next highest position, we find the highest filled position,
        // and then iterate from there to find the next available position
        int i, highest_pos;
        for (i = diff - 1; i >= 0; i--)
            if (hasCharAt(i))
                break;
        highest_pos = i;
        if (++i >= diff)    // Start at the next pos; wrap around if beyond limit
            i = 0;
        for (int num=0; num < diff; num++) {
            if (! hasCharAt(i) && ! hasHintAt(i))
                return i;
            if (++i >= diff)    // Start at the next pos; wrap around if beyond limit
                i = 0;
        }

        return -1;
    }

    public void updateHintKeyboard() {
        for (char c: hintPos) {
            if (c != EMPTY_CHAR) {
                GameEnvironment.keymgr.set_hint_image(c);
            }
        }
    }


    public String makeString() {
        String s = "";
        for (int i=0; i < diff; i++) {
            if (hasHintAt(i))
                s += String.valueOf(hintPos[i]);
            else if (hasCharAt(i))
                s += String.valueOf(guessPos[i]);
            else
                s += String.valueOf(blank_char);
        }
        return s;
    }

    public void getAllPositions(boolean[] posarray) {
        for (int i=0; i < diff; i++) {
            if (hasCharAt(i) || hasHintAt(i))
                posarray[i] = true;
            else
                posarray[i] = false;
        }
    }

    public int numFilledPos(int startingPos) {
        int num=0;
        for (int i = startingPos; i < diff; i++) {
            if (hasCharAt(i) || hasHintAt(i))
                num += 1;
        }
        return num;
    }
    public int populate_gbox (GuessInputBox gbox, LetterImageManager lim) {
        int numhints = 0;
        for (int i=0; i < diff; i++) {
            if (hasHintAt(i)) {
                gbox.setImageAt(i, lim.getHintLetter(hintPos[i]));
                gbox.removeBorderAt(i);
                numhints++;
            }
        }
        return numhints;
    }
}