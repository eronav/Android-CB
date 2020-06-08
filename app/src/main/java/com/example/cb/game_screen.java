package com.example.cb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import androidx.appcompat.widget.Toolbar;

import java.util.Random;



public class game_screen extends AppCompatActivity{

    ScrollView histbox;
    LinearLayout guess_box;
    GuessInputBox gbox;
    Button done_btn;
    TextView testing_dash;
    Random myrand;
    WordGenerator wgen;
    UserPrefs ups;
    HintManager guessmngr;
    GuessHistory ghist;
    LetterImageManager ltrmngr;
    int[] id_array;
    Context myctxt, myappctxt;
    String guess;
    String combo_guess;
    LinearLayout row;
    int diff;
    boolean keyboard_visible;
    String word;
    ImageButton hint_btn;
    boolean[] posHasCome;
    boolean hintPressed;
    String hasTyped;
    KeyStrokeManager KSM;
    Button keyboard_btn;
    public int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Create the list View

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        GameEnvironment.main_game = this;

        histbox = findViewById(R.id.history);
        // RDCHG
        keyboard_visible = false;
        // v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        myctxt = getApplicationContext();
        myappctxt = getApplicationContext();
        guess_box = findViewById(R.id.guess_box);
        guess_box.setFocusable(true);
        guess_box.setFocusableInTouchMode(true);
        // guess_box.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        // guess_box.setDividerPadding((int) UIUtil.convertDpToPx(myappctxt,15));
        guess_box.setGravity(Gravity.CENTER_HORIZONTAL);
        myrand= new Random();
        ltrmngr = new LetterImageManager();
        id_array = new int[6];
        guess = "";
        combo_guess = "";
        hintPressed =  false;
        hasTyped = "";
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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

        setKeyboard_status(guess_box);
        setKeyboard_status(guess_box);
        setKeyboard_status(guess_box);

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
        guessmngr = new HintManager(diff);

        //ghist.CreateListView(game_screen.this, histbox);


        // RDCHG
        /* keyboard_btn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                setKeyboard_status(keyboard_btn);

            }
        });

        keyboard_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setKeyboard_status(keyboard_btn);
            }
        }); */

        guess_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setKeyboard_status(guess_box);
            }
        });

        guess_box.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() != event.ACTION_UP)
                    return false;

                if (keyCode == event.KEYCODE_ENTER) {
                    done_btn_logic();
                    count += 1;
                    return true;
                }

                if (keyCode == event.KEYCODE_DEL && event.isShiftPressed()) {
                    int delPos = guessmngr.delLastHint();

                    if (delPos >= 0) {
                        gbox.deleteImageAt(delPos);
                        gbox.setBorderAt(delPos);
                    }
                    return true;
                }

                if (keyCode == event.KEYCODE_SLASH && event.isShiftPressed()) {
                    hint_logic();
                }

                if (keyCode == event.KEYCODE_DEL) {
                    int delPos;

                    if (gbox.getUserSelectedPos(false) == 7) {
                        delPos = guessmngr.delLast();
                    } else {
                        delPos = gbox.getUserSelectedPos(false);
                    }

                    if (delPos >= 0) {
                        gbox.deleteImageAt(delPos);
                        gbox.setBorderAt(delPos);
                    }

                    return true;

                } else if ((guessmngr.numFilledPos(0)) == diff) {
                    return false;
                } else {
                    ////////////////////// we have a number or an alphabet eeeeoooooeeeee!
                    int img_resid = 0;
                    char c = KSM.getCharForKeycode(keyCode, event);
                    img_resid = ltrmngr.get_imgres_for_key(keyCode);

                    if (!ups.IsGameOver()) {
                        if (c != ' ') { // GOTCHA, a valid character
                            if (!guessmngr.hasCharacter(c) || ups.IsDupsOn()) {
                                // either not dup or dup is on
                                int nextPos;
                                if (gbox.getUserSelectedPos(false) == 7) {
                                    nextPos = guessmngr.getNextAvailPos();
                                } else {
                                    nextPos = gbox.getUserSelectedPos(true);
                                }
                                guessmngr.setGuess(nextPos, c);
                                gbox.removeBorderAt(nextPos);
                                gbox.setImageAt(nextPos, img_resid);
                            } else {
                                // we have duplicate character; reject if duplicate is not allowed
                                /* if (!ups.IsDupsOn()) {
                                // Ignore all possible outcomes and fly away to another world like Dr. Strange! :)> */
                            }
                        }
                    } else {
                        // Ignore all possible outcomes and fly away to another world like Dr. Strange! :)>
                    }
                }
                return true;
            }
        });

        /* LinearLayout shadow_guess_box = new MyLL(myctxt, guess_box, ltrmngr, guess, diff);
        shadow_guess_box.setFocusable(true);
        shadow_guess_box.setFocusableInTouchMode(true);
        shadow_guess_box.requestFocus();
         */

        setKeyboard_status(guess_box);

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

    private void setKeyboard_status(View v) {
        v.requestFocus();
        if(keyboard_visible) {
            hideSoftKeyboard(v);
            keyboard_visible = false;
        } else {
            showSoftKeyboard(v);
            keyboard_visible = true;
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

    public void done_btn_logic() {
        if (guessmngr.numFilledPos(0) != diff) {
            return;
        }

        combo_guess = guessmngr.makeString();

        // Create a new row to be added to ScrollView.
        // newrow should consist of the just completed guess and its evaluation
        LinearLayout newrow = new LinearLayout(myctxt);
        newrow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        newrow.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout word_and_eval_layout = new LinearLayout(myctxt);
        newrow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        newrow.setOrientation(LinearLayout.HORIZONTAL);

        ltrmngr.get_image_for_word(combo_guess, guessmngr, word_and_eval_layout, myctxt);
        ImageView word_eval_spacing = new ImageView(myappctxt);
        word_eval_spacing.setLayoutParams(LetterImageManager.getLayoutParams());
        word_and_eval_layout.addView(word_eval_spacing);
        ghist.get_img_cb_eval(combo_guess, wgen, myctxt, ltrmngr, diff, word_and_eval_layout);

        newrow.addView(word_and_eval_layout);
        gbox.reset_guess_box(true);
        guessmngr.populate_gbox(gbox, ltrmngr);

        /* if (! redesign_images(newrow, word_image_layout, word_eval_layout)) {
            try {
                newrow.addView(word_image_layout);
                newrow.addView(word_eval_layout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } */

        row.addView(newrow, 0);

        if (wgen.evaluateGuess(combo_guess)[2] == diff) {
            hideSoftKeyboard(guess_box);
            openDialog();
            ups.SetGameOver(true);
            gbox.reset_guess_box(false);
        }
        combo_guess = "";
        guessmngr.reset();
        gbox.getUserSelectedPos(true);
    }

    public void play_again_logic () {
        ups.SetGameOver(false);
        hideSoftKeyboard(guess_box);
        keyboard_visible = false;
        Intent startIntent = new Intent(getApplicationContext(), play_game_level.class);
        startIntent.putExtra("com.mailronav.cb.playAgain", "");
        startActivity(startIntent);
    }

    public void quit_logic () {
        hideSoftKeyboard(guess_box);
        Intent intent = new Intent(getApplicationContext(), game_2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    public void hint_logic() {
        hintPressed = true;
        boolean[] filledPos = new boolean[diff];

        guessmngr.getAllPositions(filledPos);

        int chrWithPos = wgen.getHint(word, filledPos, diff, posHasCome);
        int pos = chrWithPos >> 8;
        char letter = (char) (chrWithPos & 0xFF);

        guessmngr.setHint(pos, letter);
        gbox.setImageAt(pos, ltrmngr.getHintLetter(letter));
        gbox.removeBorderAt(pos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Play_again:
                play_again_logic();
                break;
            case R.id.Quit:
                quit_logic();
                break;
            case R.id.Hint:
                hint_logic();
                break;
            case R.id.Keyboard:
                setKeyboard_status(guess_box);
            default:
                return false;
        }
        return true;
    }

    public void openDialog() {
        EndGameDialogue endGameDialogue = new EndGameDialogue(count);
        endGameDialogue.show(getSupportFragmentManager(), "end game dialogue");
    }
}