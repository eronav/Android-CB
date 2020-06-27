package com.example.cb;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DoneButton implements View.OnClickListener {

    private ImageView submitImage;
    private ImageView submitImageBackground;
    private int count = 0;

    DoneButton(AppCompatActivity activity) {

        submitImage = activity.findViewById(R.id.sendView);
        submitImage.setClickable(true);
        submitImage.setOnClickListener(this);

        submitImageBackground = activity.findViewById(R.id.sendViewBackground);
        submitImageBackground.setClickable(true);
        submitImageBackground.setOnClickListener(this);
    }

    public void openDialog(int count) {
        EndGameDialogue endGameDialogue = new EndGameDialogue(count);
        endGameDialogue.show(GameEnvironment.main_game.getSupportFragmentManager(), "end game dialogue");
    }

    public void done_btn_logic() {
        GuessManager guessmngr = GameEnvironment.guessmngr;
        GuessHistory ghist = GameEnvironment.guesshistmngr;
        KeyboardManager keybdmngr = GameEnvironment.keymgr;
        LetterImageManager ltrmngr = GameEnvironment.ltrmngr;
        WordGenerator wgen = GameEnvironment.wgen;
        GuessInputBox gbox = GameEnvironment.gbox;
        int diff = GameEnvironment.diff;

        String combo_guess;

        if (guessmngr.numFilledPos(0) != diff) {
            return;
        }

        combo_guess = guessmngr.makeString();

        // Create a new row to be added to ScrollView.
        // newrow should consist of the just completed guess and its evaluation
        /* LinearLayout newrow = new LinearLayout(myctxt);
        newrow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        newrow.setOrientation(LinearLayout.HORIZONTAL); */

        // newrow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        // newrow.setOrientation(LinearLayout.HORIZONTAL);

        int[] result = wgen.evaluateGuess(combo_guess);

        ghist.AddEntry(combo_guess, result, false);

        keybdmngr.updateKeyboard(combo_guess, result);

        // newrow.addView(word_and_eval_layout);
        gbox.reset_guess_box(true);
        guessmngr.populate_gbox(gbox, ltrmngr);

        count += 1;


        /* if (! redesign_images(newrow, word_image_layout, word_eval_layout)) {
            try {
                newrow.addView(word_image_layout);
                newrow.addView(word_eval_layout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } */

        if (result[2] == diff) {
            GameEnvironment.SetGameOver(true);
            gbox.reset_guess_box(false);
            openDialog(count);
        }

        guessmngr.reset();
        gbox.getUserSelectedPos(true);
    }

    @Override
    public void onClick(View v) {
        done_btn_logic();
    }
}