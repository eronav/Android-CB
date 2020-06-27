package com.example.cb;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class GuessInputBox {
    private int diff;
    private LinearLayout guess_box;
    private ImageView[] boxViews;
    private int userSelectedBoxPosition = 7;

    GuessInputBox(AppCompatActivity activity, int diff) {
        this.diff = diff;

        // Initialize the input box
        guess_box = activity.findViewById(R.id.guess_box);
        guess_box.setFocusable(true);
        guess_box.setFocusableInTouchMode(true);
        guess_box.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public void build_guess_box () {
        Context ctxt = GameEnvironment.main_game.getApplicationContext();

        boxViews = new ImageView[diff];
        for (int i = 0; i < diff; i++) {
            ImageView v = new ImageView(ctxt);
            LinearLayout.LayoutParams img_layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            v.setBackgroundDrawable(new Border(R.color.hintColorGame, 10));
            img_layout.height = GameEnvironment.phoneDims[0] / 9;
            img_layout.width = GameEnvironment.phoneDims[0] / 9;
            img_layout.setMargins(0,0,8,0);
            v.setLayoutParams(img_layout);
            v.setClickable(true);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i;
                    for (i = 0; i < diff; i++) {
                        if (boxViews[i] == v) {
                            userSelectedBoxPosition = i;
                            break;
                        }
                    }

                    Toast.makeText(GameEnvironment.main_game.getApplicationContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
                }
            });
            guess_box.addView(v);
            boxViews[i] = v;
        }
    }

    public int getUserSelectedPos (boolean reset) {
        int pos = userSelectedBoxPosition;
        if (reset) {
            userSelectedBoxPosition = 7;
        }
        return pos;
    }

    public void reset_guess_box(boolean rebuild) {
        guess_box.removeAllViews();
        Arrays.fill(boxViews, null);
        if (rebuild)
            build_guess_box();
    }

    public void setImageAt(int pos, int img_resid) {
        if (pos < diff) {
            ImageView img = (ImageView) guess_box.getChildAt(pos);
            img.setImageResource(img_resid);
        }
    }

    public void removeBorderAt(int pos) {
        guess_box.getChildAt(pos).setBackgroundDrawable(null);
    }

    public void setBorderAt(int pos) {
        guess_box.getChildAt(pos).setBackgroundDrawable(new Border(R.color.hintColorGame, 10));
    }

    public void deleteImageAt(int pos) {
        int abs_pos = (pos > 0) ? pos : -pos;
        int num_child = 0;

        try {
            num_child = guess_box.getChildCount();
        } catch (Exception e){
            e.printStackTrace();
        }

        if (abs_pos < num_child) {
            if (pos < 0) {
                ImageView img = (ImageView) guess_box.getChildAt(num_child - abs_pos);
                img.setImageResource(0);
            } else {
                ImageView img = (ImageView) guess_box.getChildAt(pos);
                img.setImageResource(0);
            }
        }
    }

    public void reinitView () {
        char[] c = {' '};
        LetterImageManager ltrmngr = GameEnvironment.ltrmngr;
        for (int i = 0; i < diff; i++) {
            if (GameEnvironment.guessmngr.getCharAt(i, c)) {
                removeBorderAt(i);
                setImageAt(i, ltrmngr.get_imgresid_for_char(ltrmngr.getKeycodeFromChar(c[0])));
            } else if (GameEnvironment.guessmngr.getHintAt(i, c)) {
                removeBorderAt(i);
                setImageAt(i, ltrmngr.get_imgresid_for_hint(ltrmngr.getKeycodeFromChar(c[0])));
            }
        }
    }

    public int moveCursor (boolean[] posArray) {
        for (int i = 0; i < posArray.length; i++) {
            if (posArray[i] == false) {
                return i;
            }
        }
        return 0;
    }
}
