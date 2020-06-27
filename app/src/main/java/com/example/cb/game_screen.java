package com.example.cb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Random;



public class game_screen extends AppCompatActivity{

    LinearLayout guess_box;
    GuessInputBox gbox;
    Button done_btn;
    TextView testing_dash;
    Random myrand;
    WordGenerator wgen;
    UserPrefs ups;
    GuessManager guessmngr;
    GuessHistory ghist;
    LetterImageManager ltrmngr;
    KeyboardManager keymngr;
    int[] id_array;
    Context myctxt, myappctxt;
    String guess;
    String combo_guess;
    int diff;
    boolean keyboard_visible;
    String word;
    boolean hintPressed;
    String hasTyped;
    KeyStrokeManager KSM;

    public ImageView deleteImage;
    public ImageView deleteImageBackground;



    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        GameEnvironment.main_game = this;

        // RDCHG
        keyboard_visible = false;
        // v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        myctxt = getApplicationContext();
        myappctxt = getApplicationContext();
        myrand= new Random();

        id_array = new int[6];
        guess = "";
        combo_guess = "";
        hintPressed =  false;
        hasTyped = "";
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("com.mailronav.cb.help")) {

            GameEnvironment.gbox = new GuessInputBox(this, GameEnvironment.diff);
            GameEnvironment.gbox.build_guess_box();
            GameEnvironment.keymgr.initialize_keyboard(this);

            GameEnvironment.guesshistmngr.reinitView(this);
            GameEnvironment.gbox.reinitView();
            GameEnvironment.guesshistmngr.restoreHistory();

            InitLocalEnvironment();
        } else {
            if(getIntent().hasExtra("com.mailronav.cb.three")){
                diff = 3;
            } else if(getIntent().hasExtra("com.mailronav.cb.four")){
                diff = 4;
            } else if(getIntent().hasExtra("com.mailronav.cb.five")){
                diff = 5;
            } else if (getIntent().hasExtra("com.mailronav.cb.six")){
                diff = 6;
            } else {
                diff = 3;   // TBD
            }
            GameEnvironment.diff = diff;

            InitEnvironment();
            InitViewElements();
            InitLocalEnvironment();
        }

        KSM = new KeyStrokeManager();

        InstallClickListeners();

        /* LinearLayout shadow_guess_box = new MyLL(myctxt, guess_box, ltrmngr, guess, diff);
        shadow_guess_box.setFocusable(true);
        shadow_guess_box.setFocusableInTouchMode(true);
        shadow_guess_box.requestFocus();
         */

        diff = GameEnvironment.diff;
        word = GameEnvironment.word;

    } // onCreate()


    public void play_again_logic () {
        GameEnvironment.SetGameOver(false);
        keyboard_visible = false;
        Intent startIntent = new Intent(getApplicationContext(), play_game_level.class);
        startIntent.putExtra("com.mailronav.cb.playAgain", "");
        startActivity(startIntent);
    }

    public boolean onKeyLogic (KeyEvent event, int keyCode) {
        if (event.getAction() != event.ACTION_UP)
            return false;

        if (keyCode == event.KEYCODE_ENTER) {
            GameEnvironment.donebutton.done_btn_logic();
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
            GameEnvironment.hintbutton.hint_logic();
        }

        if (keyCode == event.KEYCODE_DEL) {
            delete_logic(false);

            return true;

        } else if ((guessmngr.numFilledPos(0)) == diff) {
            return false;
        } else {
            ////////////////////// we have a number or an alphabet eeeeoooooeeeee!
            int img_resid = 0;
            char c = ltrmngr.getCharForKeycode(keyCode);
            img_resid = ltrmngr.get_imgresid_for_char(keyCode);

            if (!GameEnvironment.IsGameOver()) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);
        return true;
    }


    public int[] getWindowDims (Context myappctxt) {
        int[] phoneDims = new int[2];

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) myappctxt.getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);

        phoneDims[0] = displayMetrics.widthPixels;
        phoneDims[1] = displayMetrics.heightPixels;
        return phoneDims;
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

    @Override
    protected void onDestroy() {
        DeleteActivityObjects();
        super.onDestroy();
    }


    private void InitEnvironment() {
        GameEnvironment.wgen = new WordGenerator(myctxt, diff);
        GameEnvironment.ltrmngr = new LetterImageManager();
        GameEnvironment.guessmngr = new GuessManager(diff);
        GameEnvironment.word = GameEnvironment.wgen.getWord();
        GameEnvironment.keymgr = new KeyboardManager();
        GameEnvironment.guesshistmngr = new GuessHistory(this);

        GameEnvironment.gbox = new GuessInputBox(this, diff);

        GameEnvironment.phoneDims = new int[2];
        GameEnvironment.phoneDims = getWindowDims(myappctxt);
        GameEnvironment.hintCount = 6;

        GameEnvironment.SetGameOver(false);
    }

    private void InitViewElements() {

        // Make the keyboard alphabet clickable
        GameEnvironment.keymgr.initialize_keyboard(this);

        GameEnvironment.gbox.build_guess_box();
    }

    private void InitLocalEnvironment () {
        wgen = GameEnvironment.wgen;
        ltrmngr = GameEnvironment.ltrmngr;
        guessmngr = GameEnvironment.guessmngr;
        keymngr = GameEnvironment.keymgr;
        ghist = GameEnvironment.guesshistmngr;
        gbox = GameEnvironment.gbox;
        ups = GameEnvironment.ups;
    }

    private void InstallClickListeners() {

        GameEnvironment.donebutton = new DoneButton(this);
        GameEnvironment.hintbutton = new HintButton(GameEnvironment.diff, this);

        deleteImage = findViewById(R.id.deleteView);
        deleteImage.setClickable(true);
        deleteImageBackground = findViewById(R.id.deleteViewBackground);
        deleteImageBackground.setClickable(true);


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
    }

    void DeleteActivityObjects() {
        GameEnvironment.gbox.reset_guess_box(false);
        GameEnvironment.gbox = null;
        GameEnvironment.donebutton = null;
        GameEnvironment.hintbutton = null;
    }
}