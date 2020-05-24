package com.example.cb;

import android.content.Context;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyLL extends LinearLayout {

    private LinearLayout guess_box;
    private LetterImageManager ltrmngr;
    private Context myctxt;
    private String guess;
    private int diff;

    MyLL (Context ctxt, LinearLayout ll, LetterImageManager ltrmngr, String guess, int diff) {
        super(ctxt);
        this.myctxt = ctxt;
        this.guess_box = ll;
        this.ltrmngr = ltrmngr;
        this.guess = guess;
        this.diff = diff;
    }


    @Override
    public boolean onKeyUp (int keyCode, KeyEvent event) {

        return true;
    }

    public boolean procesesKey (int keyCode, KeyEvent event) {
        String chr = null;
        int chr_keycode = 0;

        ImageView img = new ImageView(myctxt);
        img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ViewGroup.LayoutParams img_layout = img.getLayoutParams();
        img_layout.height = 60;
        img_layout.width = 60;
        int gl = guess.length();

        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (gl == 0) {
                return true;
            }
            if (gl == 1) {
                guess_box.removeAllViews();
                guess = "";
                return true;
            }

            guess_box.removeViewAt(guess_box.getChildCount() - 1);
            guess = guess.substring(0, gl-1);
        } else if (gl == diff) {
            // Ignore characters if already at max acceptable
        } else {
            try {
                if (KeyEvent.KEYCODE_A <= keyCode && keyCode <= KeyEvent.KEYCODE_Z) {
                    chr_keycode = ltrmngr.onkeyup_get_letter(keyCode);
                    img.setImageResource(chr_keycode);
                    guess_box.addView(img);
                    guess += String.valueOf((char) (keyCode + (int) 'a' - KeyEvent.KEYCODE_A));
                } else if (KeyEvent.KEYCODE_0 <= keyCode && keyCode <= KeyEvent.KEYCODE_9) {
                    chr_keycode = ltrmngr.onkeyup_get_letter(keyCode);
                    img.setImageResource(chr_keycode);
                    guess_box.addView(img);
                    guess += String.valueOf((char) (keyCode + (int) '0' - KeyEvent.KEYCODE_0));
                }

                // Silently ignore other characters
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

}
