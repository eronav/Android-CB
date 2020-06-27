package com.example.cb;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HintButton implements View.OnLongClickListener {

    private boolean hintPressed = false;
    private int diff;
    private ImageView hintImage;

    public HintButton (int diff, AppCompatActivity activity) {
        this.diff = diff;

        hintImage = activity.findViewById(R.id.questionImage);
        hintImage.setClickable(true);
        hintImage.setOnLongClickListener(this);
    }

    public void hint_logic() {
        hintPressed = true;
        boolean[] filledPos = new boolean[diff];
        Context appctxt = GameEnvironment.main_game.getApplicationContext();

        GameEnvironment.guessmngr.getAllPositions(filledPos);


        int filledCount = 0;
        for (int i = 0; i < diff; i++) {
            if (filledPos[i] == true) {
                filledCount += 1;
            }
        }

        if (filledCount == diff) {
            Toast.makeText(appctxt, "No hint position available", Toast.LENGTH_SHORT).show();
            return;
        }

        int chrWithPos = GameEnvironment.wgen.getHint(GameEnvironment.word, filledPos, diff);
        int pos = chrWithPos >> 8;
        char letter = (char) (chrWithPos & 0xFF);
        if (GameEnvironment.hintCount != 0) {
            GameEnvironment.guessmngr.setHint(pos, letter);
            GameEnvironment.keymgr.set_hint_image(letter);
            GameEnvironment.gbox.setImageAt(pos, GameEnvironment.ltrmngr.getHintLetter(letter));
            GameEnvironment.gbox.removeBorderAt(pos);
            GameEnvironment.hintCount--;
        } else {
            Toast.makeText(appctxt, "No more hints", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        hint_logic();
        return true;
    }
}
