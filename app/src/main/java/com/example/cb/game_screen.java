package com.example.cb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
    KeyboardManager keymngr;
    int[] id_array;
    Context myctxt, myappctxt;
    String guess;
    String combo_guess;
    LinearLayout row;
    int diff;
    boolean keyboard_visible;
    String word;
    boolean[] posHasCome;
    boolean hintPressed;
    String hasTyped;
    KeyStrokeManager KSM;
    public int count = 0;

    public ImageView deleteImage;
    public ImageView deleteImageBackground;

    public ImageView submitImage;
    public ImageView submitImageBackground;

    public ImageView hintImage;



    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Create the list View

        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

        id_array = new int[6];
        guess = "";
        combo_guess = "";
        hintPressed =  false;
        hasTyped = "";
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindowDims(myappctxt);


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


        deleteImage = findViewById(R.id.deleteView);
        deleteImage.setClickable(true);
        deleteImageBackground = findViewById(R.id.deleteViewBackground);
        deleteImageBackground.setClickable(true);

        submitImage = findViewById(R.id.sendView);
        submitImage.setClickable(true);
        submitImageBackground = findViewById(R.id.sendViewBackground);
        submitImageBackground.setClickable(true);

        hintImage = findViewById(R.id.questionImage);
        hintImage.setClickable(true);

        gbox = new GuessInputBox(myappctxt, guess_box, diff);
        gbox.build_guess_box();
        KSM = new KeyStrokeManager();
        ups = GameEnvironment.ups;


        GameEnvironment.ltrmngr = new LetterImageManager();
        ltrmngr = GameEnvironment.ltrmngr;

        GameEnvironment.keymgr = new KeyboardManager(this);
        keymngr = GameEnvironment.keymgr;
        keymngr.initialize_keyboard();

        GameEnvironment.wgen = new WordGenerator(myctxt, diff);
        wgen = GameEnvironment.wgen;

        ghist = new GuessHistory();
        guessmngr = new HintManager(diff);


        posHasCome = new boolean[diff];
        for (int i = 0; i < diff; i++) {
            posHasCome[i] = false;
        }
        GameEnvironment.hintCount = 3;

        submitImageBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done_btn_logic();
            }
        });
        submitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done_btn_logic();
            }
        });

        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_logic(false);
            }
        });
        deleteImageBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_logic(false);
            }
        });

        deleteImageBackground.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                delete_logic(true);
                return true;
            }
        });

        deleteImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                delete_logic(true);
                return true;
            }
        });

        hintImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                hint_logic();
                return true;
            }
        });

        /* LinearLayout shadow_guess_box = new MyLL(myctxt, guess_box, ltrmngr, guess, diff);
        shadow_guess_box.setFocusable(true);
        shadow_guess_box.setFocusableInTouchMode(true);
        shadow_guess_box.requestFocus();
         */

        // Get a new target word
        word = wgen.getWord();

    } // onCreate()

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
        int[] result = ghist.get_img_cb_eval(combo_guess, wgen, myctxt, ltrmngr, diff, word_and_eval_layout);

        keymngr.updateKeyboard(combo_guess, result);

        newrow.addView(word_and_eval_layout);
        gbox.reset_guess_box(true);
        guessmngr.populate_gbox(gbox, ltrmngr);

        ghist.addToHistoryList(combo_guess, result[0], result[1]);

        /* if (! redesign_images(newrow, word_image_layout, word_eval_layout)) {
            try {
                newrow.addView(word_image_layout);
                newrow.addView(word_eval_layout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } */

        row.addView(newrow, 0);
        count += 1;

        if (wgen.evaluateGuess(combo_guess)[2] == diff) {
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
        keyboard_visible = false;
        Intent startIntent = new Intent(getApplicationContext(), play_game_level.class);
        startIntent.putExtra("com.mailronav.cb.playAgain", "");
        startActivity(startIntent);
    }

    public boolean onKeyLogic (KeyEvent event, int keyCode) {
        if (event.getAction() != event.ACTION_UP)
            return false;

        if (keyCode == event.KEYCODE_ENTER) {
            done_btn_logic();
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
            delete_logic(false);

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

    public void quit_logic () {
        Intent intent = new Intent(getApplicationContext(), game_2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    public void delete_logic(boolean delHint) {
        int delPos;
        int userPos = gbox.getUserSelectedPos(false);

        if (delHint) {
            if (userPos != 7) {
                delPos = userPos;
                guessmngr.deleteHint(userPos);
            } else {
                // ignore
                delPos = -1;
            }
        } else {
            // Deleting a letter - do so at the user-specified position or the last one
            if (userPos == 7) {
                delPos = guessmngr.delLast();
            } else {
                guessmngr.deleteLetter(userPos);
                delPos = userPos;
            }
        }

        // Reset the image at the deleted position
        if (delPos >= 0) {
            gbox.deleteImageAt(delPos);
            gbox.setBorderAt(delPos);
        }
    }

    public void hint_logic() {
        hintPressed = true;
        boolean[] filledPos = new boolean[diff];

        guessmngr.getAllPositions(filledPos);

        int filledCount = 0;
        for (int i = 0; i < diff; i++) {
            if (filledPos[i] == true) {
                filledCount += 1;
            }
        }

        if (filledCount == diff) {
            Toast.makeText(myctxt, "No hint position available", Toast.LENGTH_SHORT).show();
            return;
        }

        int chrWithPos = wgen.getHint(word, filledPos, diff, posHasCome);
        int pos = chrWithPos >> 8;
        char letter = (char) (chrWithPos & 0xFF);
        if (GameEnvironment.hintCount != 0) {
            guessmngr.setHint(pos, letter);
            GameEnvironment.keymgr.set_hint_image(letter);
            gbox.setImageAt(pos, ltrmngr.getHintLetter(letter));
            gbox.removeBorderAt(pos);
            GameEnvironment.hintCount--;
        } else {
            Toast.makeText(myappctxt, "No more hints", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);
        return true;
    }


    public void getWindowDims (Context myappctxt) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) myappctxt.getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        GameEnvironment.phoneDims = new int[2];
        GameEnvironment.phoneDims[0] = displayMetrics.widthPixels;
        GameEnvironment.phoneDims[1] = displayMetrics.heightPixels;
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
            case R.id.Help:
                Intent startIntent = new Intent(myctxt, help_activity.class);
                startIntent.putExtra("com.mailronav.cb.Help", "");
                startActivity(startIntent);
            default:
                return false;
        }
        return true;
    }

    public void openDialog() {
        EndGameDialogue endGameDialogue = new EndGameDialogue(count);
        endGameDialogue.show(getSupportFragmentManager(), "end game dialogue");
    }

    public void InitEnvironment() {

    }
}