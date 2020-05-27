package com.example.cb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;



public class game_screen extends AppCompatActivity {

    ScrollView histbox;
    LinearLayout guess_box;
    GuessInputBox gbox;
    Button done_btn;
    TextView testing_dash;
    Random myrand;
    WordGenerator wgen;
    UserPrefs ups;
    HintManager hintmngr;
    HintManager guessmngr;
    GuessHistory ghist;
    LetterImageManager ltrmngr;
    int[] id_array;
    Context myctxt, myappctxt;
    String guess;
    String combo_guess;
    LinearLayout row;
    int diff;
    boolean keyboard_status;
    String word;
    ImageButton hint_btn;
    boolean gameHasEnded;
    boolean[] posHasCome;
    boolean hintPressed;
    String hasTyped;
    GuessInputBox GIB;
    KeyStrokeManager KSM;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Create the list View

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        histbox = findViewById(R.id.history);
        final Button keyboard_btn = (Button) findViewById(R.id.keyboard);
        keyboard_btn.setFocusable(true);
        keyboard_btn.setFocusableInTouchMode(true);
        keyboard_status = false;
        // v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        myctxt = getApplicationContext();
        myappctxt = getApplicationContext();
        guess_box = findViewById(R.id.guess_box);
        done_btn = findViewById(R.id.done_btn);
        testing_dash = findViewById(R.id.word);
        myrand= new Random();
        ltrmngr = new LetterImageManager();
        id_array = new int[6];
        guess = "";
        combo_guess = "";
        hint_btn = findViewById(R.id.hint_btn);
        gameHasEnded = false;
        hintPressed =  false;
        hasTyped = "";


        // This LinearLayout is the layout organizer for the ScrollView, histbox
        row = new LinearLayout(myctxt);
        row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        row.setOrientation(LinearLayout.VERTICAL);

        histbox.addView(row);

        String dash = "";
        if(getIntent().hasExtra("com.mailronav.cb.three")){
            diff = 3;
        } else if(getIntent().hasExtra("com.mailronav.cb.four")){
            diff = 4;
        } else if(getIntent().hasExtra("com.mailronav.cb.five")){
            diff = 5;
        } else {
            diff = 6;
        }

        gbox = new GuessInputBox(myappctxt, guess_box, diff);
        gbox.build_guess_box();
        KSM = new KeyStrokeManager();
        ups = GameEnvironment.ups;

        posHasCome = new boolean[diff];

        for (int i = 0; i < diff; i++) {
            posHasCome[i] = false;
        }

        GameEnvironment.wgen = new WordGenerator(myctxt, diff);
        wgen = GameEnvironment.wgen;

        // Get a new target word
        word = wgen.getWord();


        ghist = new GuessHistory();
        hintmngr = new HintManager(diff);
        guessmngr = new HintManager(diff);

        GIB = new GuessInputBox(myappctxt, guess_box, diff);
        //ghist.CreateListView(game_screen.this, histbox);


        done_btn.setOnClickListener(new View.OnClickListener() {
            boolean check_game;
            @Override
            public void onClick(View v) {


                if (done_btn.getText().equals("Play Again")) {
                    hideSoftKeyboard(keyboard_btn);
                    keyboard_status = false;
                    Intent startIntent = new Intent(getApplicationContext(), play_game_level.class);
                    startIntent.putExtra("com.mailronav.cb.playAgain", "");
                    startActivity(startIntent);
                    gameHasEnded = true;
                }

                if (guessmngr.num_of_hints(0) + hintmngr.num_of_hints(0) != diff) {
                    return;
                }

                combo_guess = "";
                combo_guess = hintmngr.addToString(combo_guess);
                combo_guess = guessmngr.addToString(combo_guess);

                // Create a new row to be added to ScrollView.
                // newrow should consist of the just completed guess and its evaluation
                LinearLayout newrow = new LinearLayout(myctxt);
                newrow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                newrow.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout word_eval_layout = ghist.get_img_cb_eval(combo_guess, wgen, myctxt, ltrmngr, diff);
                LinearLayout word_image_layout = new LinearLayout(myctxt);
                word_image_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                word_image_layout.setOrientation(LinearLayout.HORIZONTAL);

                word_image_layout = ltrmngr.get_img_word(combo_guess, ltrmngr, word_image_layout, myctxt);
                gbox.reset_guess_box();
                hintmngr.populate_gbox(gbox, ltrmngr);

                if (! redesign_images(newrow, word_image_layout, word_eval_layout)) {
                    try {
                        newrow.addView(word_image_layout);
                        newrow.addView(word_eval_layout);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                row.addView(newrow);
                keyboard_btn.requestFocus();

                if (wgen.evaluateGuess(combo_guess)[2] == diff) {
                    done_btn.setText("Play Again");
                    keyboard_btn.setText("Quit");
                }
                combo_guess = "";
                guessmngr.reset();
            }
        });

        hint_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hintPressed = true;
                boolean[] filledPos = new boolean[diff];

                guessmngr.getPositions(filledPos, true);
                hintmngr.getPositions(filledPos, false);

                int chrWithPos = wgen.getHint(word, filledPos, diff, posHasCome);
                int pos = chrWithPos >> 8;
                char letter = (char) (chrWithPos & 0xFF);

                hintmngr.setLetter(pos, letter);
                gbox.setImageAt(pos, ltrmngr.getLetter(letter));
            }
        });

        keyboard_btn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (keyboard_btn.getText().equals("Quit")) {
                    hideSoftKeyboard(keyboard_btn);
                    keyboard_status = false;
                    Intent startIntent = new Intent(getApplicationContext(), game_2.class);
                    startIntent.putExtra("com.mailronav.cb.quit", "");
                    startActivity(startIntent);
                    gameHasEnded = true;
                } else {
                    setKeyboard_status(keyboard_btn);
                }

            }
        });

        keyboard_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (keyboard_btn.getText().equals("Quit")) {
                    Intent startIntent = new Intent(getApplicationContext(), game_2.class);
                    startIntent.putExtra("com.mailronav.cb.quit", "");
                    startActivity(startIntent);
                } else {
                    setKeyboard_status(keyboard_btn);
                }
            }
        });

        keyboard_btn.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() != KeyEvent.ACTION_UP)
                    return false;

                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    int delPos = guessmngr.delLast();

                    if (delPos >= 0)
                        gbox.deleteImageAt(delPos);

                } else if ((guessmngr.num_of_hints(0) + hintmngr.num_of_hints(0)) == diff) {
                    // Ignore characters if already at max acceptable
                } else {
                    int img_resid = 0;
                    char c = KSM.getCharForKeycode(keyCode, event);
                    img_resid = ltrmngr.get_imgres_for_key(keyCode);

                    if (c != ' ') {
                        if (hintmngr.hasChar(c) || guessmngr.hasChar(c)) {
                            if (!ups.IsDupsOn()) {
                                // Ignore all possible outcomes and fly away to another world like Dr. Strange! :)
                            } else {
                                int nextPos = guessmngr.getHighestPos() + 1;
                                nextPos = hintmngr.getNextPos(nextPos);
                                guessmngr.setLetter(nextPos, c);
                                gbox.setImageAt(nextPos, img_resid);
                            }
                        } else {
                            int nextPos = guessmngr.getHighestPos() + 1;
                            nextPos = hintmngr.getNextPos(nextPos);
                            guessmngr.setLetter(nextPos, c);
                            gbox.setImageAt(nextPos, img_resid);
                        }
                    }
                }
                return true;
            }
        });

        testing_dash.setText(word);

        /* LinearLayout shadow_guess_box = new MyLL(myctxt, guess_box, ltrmngr, guess, diff);
        shadow_guess_box.setFocusable(true);
        shadow_guess_box.setFocusableInTouchMode(true);
        shadow_guess_box.requestFocus();
         */

    } // onCreate()

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return false;
        // return procesesKey(keyCode, event);
    }

    //Create the creation method
    public ImageView get_cow_img() {
        ImageView cow_img = new ImageView(myctxt);
        cow_img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ViewGroup.LayoutParams cow_layout = cow_img.getLayoutParams();
        cow_layout.height = 60;
        cow_layout.width = 60;
        cow_img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        cow_img.setImageResource(R.drawable.cow_head);
        return cow_img;
    }



    public ImageView get_bull_img() {
        ImageView bull_img = new ImageView(myctxt);
        bull_img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ViewGroup.LayoutParams bull_layout = bull_img.getLayoutParams();
        bull_layout.height = 60;
        bull_layout.width = 60;
        bull_img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        bull_img.setImageResource(R.drawable.bulls_head);
        return bull_img;
    }

    public boolean hasGameEnded ()
    {
        return gameHasEnded;
    }

    public LinearLayout add_row() {
        ImageView cow = new ImageView(myctxt);
        ImageView bull = new ImageView(myctxt);
        ImageView cow1 = new ImageView(myctxt);
        ImageView bull1 = new ImageView(myctxt);
        ImageView cow2 = new ImageView(myctxt);
        ImageView bull2 = new ImageView(myctxt);

        LinearLayout cows_layout = new LinearLayout(myctxt);
        LinearLayout bulls_layout = new LinearLayout(myctxt);
        LinearLayout final_layout = new LinearLayout(myctxt);


        cow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        cow1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        cow2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bull.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bull1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bull2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        ViewGroup.LayoutParams cow_layout = cow.getLayoutParams();
        ViewGroup.LayoutParams cow1_layout = cow1.getLayoutParams();
        ViewGroup.LayoutParams cow2_layout = cow2.getLayoutParams();
        ViewGroup.LayoutParams bull_layout = bull.getLayoutParams();
        ViewGroup.LayoutParams bull1_layout = bull1.getLayoutParams();
        ViewGroup.LayoutParams bull2_layout = bull2.getLayoutParams();

        bull_layout.height = 200;
        bull_layout.width = 200;

        bull1_layout.height = 200;
        bull1_layout.width = 200;

        bull2_layout.height = 200;
        bull2_layout.width = 200;

        cow_layout.height = 200;
        cow_layout.width = 200;

        cow1_layout.height = 200;
        cow1_layout.width = 200;

        cow2_layout.height = 200;
        cow2_layout.width = 200;

        bull.setScaleType(ImageView.ScaleType.FIT_CENTER);
        bull1.setScaleType(ImageView.ScaleType.FIT_CENTER);
        bull2.setScaleType(ImageView.ScaleType.FIT_CENTER);
        cow.setScaleType(ImageView.ScaleType.FIT_CENTER);
        cow1.setScaleType(ImageView.ScaleType.FIT_CENTER);
        cow2.setScaleType(ImageView.ScaleType.FIT_CENTER);

        bull.setImageResource(R.drawable.bulls_head);
        bull1.setImageResource(R.drawable.bulls_head);
        bull2.setImageResource(R.drawable.bulls_head);
        cow.setImageResource(R.drawable.cow_head);
        cow1.setImageResource(R.drawable.cow_head);
        cow2.setImageResource(R.drawable.cow_head);

        cows_layout.addView(cow);
        cows_layout.addView(cow1);
        cows_layout.addView(cow2);
        bulls_layout.addView(bull);
        bulls_layout.addView(bull1);
        bulls_layout.addView(bull2);

        final_layout.addView(cows_layout);
        final_layout.addView(bulls_layout);

        return final_layout;
    }

    public void setDebugBox(String s) {
        testing_dash.setText(s);
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public void openKeyboard() {
        InputMethodManager imm =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    public LinearLayout set_word_img(String sequence) {
        int[] letter_ids = ltrmngr.getLetter(sequence, id_array);
        LinearLayout word_str_layout = new LinearLayout(myctxt);
        word_str_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        word_str_layout.setOrientation(LinearLayout.HORIZONTAL);
        for(int i = 0; i < sequence.length(); i++){
            ImageView letterView = new ImageView(myctxt);
            LinearLayout.LayoutParams letter_layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            letterView.setLayoutParams(letter_layout);
            letter_layout.height = 60;
            letter_layout.width = 60;
            letterView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            letter_layout.setMargins(10, 10, 10, 10);
            letterView.setImageResource(letter_ids[i]);
            word_str_layout.addView(letterView);
            // newrow.addView(letterView);
        }
        return word_str_layout;
    }

    public String getViewText(CharSequence c) {
        return String.valueOf(c);
    }

    private void setKeyboard_status(View keyboard_btn) {
        if(keyboard_status) {
            hideSoftKeyboard(keyboard_btn);
            keyboard_status = false;
        } else {
            showSoftKeyboard(keyboard_btn);
            keyboard_status = true;
            // dummy_input.performClick();
        }

    }

    private boolean redesign_images(LinearLayout layout, LinearLayout word_layout, LinearLayout eval_layout) {

        return false;

        /*
        View template_guess_box = findViewById(R.id.design_guess);
        View template_eval_box = findViewById(R.id.design_eval);
        View[] template_letters = {
                findViewById(R.id.design_letter1),
                findViewById(R.id.design_letter2),
                findViewById(R.id.design_letter3),
                findViewById(R.id.design_letter4)
        };
        View[] template_evals = {
                findViewById(R.id.design_eval1),
                findViewById(R.id.design_eval2),
                findViewById(R.id.design_eval3),
                findViewById(R.id.design_eval4)
        };

        ViewGroup.LayoutParams vglp;

        vglp = template_guess_box.getLayoutParams();
        word_layout.setLayoutParams(vglp);
        vglp = template_eval_box.getLayoutParams();
        eval_layout.setLayoutParams(vglp);

        for (int i=0; i < word_layout.getChildCount(); i++) {
            vglp = template_letters[i].getLayoutParams();
            word_layout.getChildAt(i).setLayoutParams(vglp);
        }

        for (int i=0; i < eval_layout.getChildCount(); i++) {
            vglp = template_evals[i].getLayoutParams();
            eval_layout.getChildAt(i).setLayoutParams(vglp);
        }

        layout.addView(word_layout);
        layout.addView(eval_layout);

        return true;

         */
    }

    public void showSoftKeyboard(View view) {
        // if(true /* view.requestFocus() */)
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT); // could use SHOW_FORCED if need be
        // InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        // this worked: imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        // InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        // imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
