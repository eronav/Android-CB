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
    public int count = 0;

    public ImageView keyboardButton1;

    public ImageView keyboardButton2;

    public ImageView keyboardButton3;

    public ImageView keyboardButton4;

    public ImageView keyboardButton5;

    public ImageView keyboardButton6;

    public ImageView keyboardButton7;

    public ImageView keyboardButton8;

    public ImageView keyboardButton9;

    public ImageView keyboardButton10;

    public ImageView keyboardButton11;

    public ImageView keyboardButton12;

    public ImageView keyboardButton13;

    public ImageView keyboardButton14;

    public ImageView keyboardButton15;

    public ImageView keyboardButton16;

    public ImageView keyboardButton17;

    public ImageView keyboardButton18;

    public ImageView keyboardButton19;

    public ImageView keyboardButton20;

    public ImageView keyboardButton21;

    public ImageView keyboardButton22;

    public ImageView keyboardButton23;

    public ImageView keyboardButton24;

    public ImageView keyboardButton25;

    public ImageView keyboardButton26;

    public ImageView deleteImage;
    public ImageView deleteImageBackground;

    public ImageView submitImage;
    public ImageView submitImageBackground;

    public ImageView hintImage;



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

        keyboardButton1 = findViewById(R.id.imageView1);
        keyboardButton1.setClickable(true);

        keyboardButton2 = findViewById(R.id.imageView2);
        keyboardButton2.setClickable(true);

        keyboardButton3 = findViewById(R.id.imageView3);
        keyboardButton3.setClickable(true);

        keyboardButton4 = findViewById(R.id.imageView4);
        keyboardButton4.setClickable(true);

        keyboardButton5 = findViewById(R.id.imageView5);
        keyboardButton5.setClickable(true);

        keyboardButton6 = findViewById(R.id.imageView6);
        keyboardButton6.setClickable(true);

        keyboardButton7 = findViewById(R.id.imageView7);
        keyboardButton7.setClickable(true);

        keyboardButton8 = findViewById(R.id.imageView8);
        keyboardButton8.setClickable(true);

        keyboardButton9 = findViewById(R.id.imageView9);
        keyboardButton9.setClickable(true);

        keyboardButton10 = findViewById(R.id.imageView10);
        keyboardButton10.setClickable(true);

        keyboardButton11 = findViewById(R.id.imageView11);
        keyboardButton11.setClickable(true);

        keyboardButton12 = findViewById(R.id.imageView12);
        keyboardButton12.setClickable(true);

        keyboardButton13 = findViewById(R.id.imageView13);
        keyboardButton13.setClickable(true);

        keyboardButton14 = findViewById(R.id.imageView14);
        keyboardButton14.setClickable(true);

        keyboardButton15 = findViewById(R.id.imageView15);
        keyboardButton15.setClickable(true);

        keyboardButton16 = findViewById(R.id.imageView16);
        keyboardButton16.setClickable(true);

        keyboardButton17 = findViewById(R.id.imageView17);
        keyboardButton17.setClickable(true);

        keyboardButton18 = findViewById(R.id.imageView18);
        keyboardButton18.setClickable(true);

        keyboardButton19 = findViewById(R.id.imageView19);
        keyboardButton19.setClickable(true);

        keyboardButton20 = findViewById(R.id.imageView20);
        keyboardButton20.setClickable(true);

        keyboardButton21 = findViewById(R.id.imageView21);
        keyboardButton21.setClickable(true);

        keyboardButton22 = findViewById(R.id.imageView22);
        keyboardButton22.setClickable(true);

        keyboardButton23 = findViewById(R.id.imageView23);
        keyboardButton23.setClickable(true);

        keyboardButton24 = findViewById(R.id.imageView24);
        keyboardButton24.setClickable(true);

        keyboardButton25 = findViewById(R.id.imageView25);
        keyboardButton25.setClickable(true);

        keyboardButton26 = findViewById(R.id.imageView26);
        keyboardButton26.setClickable(true);

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

        /*
        // puts the keyboard up/down
        guess_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setKeyboard_status(guess_box);
            }
        }); */

        keyboardButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton1.getResources().getResourceEntryName(keyboardButton1.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton2.getResources().getResourceEntryName(keyboardButton2.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton3.getResources().getResourceEntryName(keyboardButton3.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton4.getResources().getResourceEntryName(keyboardButton4.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton5.getResources().getResourceEntryName(keyboardButton5.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton6.getResources().getResourceEntryName(keyboardButton6.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton7.getResources().getResourceEntryName(keyboardButton7.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton8.getResources().getResourceEntryName(keyboardButton8.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton9.getResources().getResourceEntryName(keyboardButton9.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton10.getResources().getResourceEntryName(keyboardButton10.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton11.getResources().getResourceEntryName(keyboardButton11.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton12.getResources().getResourceEntryName(keyboardButton12.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton13.getResources().getResourceEntryName(keyboardButton13.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton14.getResources().getResourceEntryName(keyboardButton14.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton15.getResources().getResourceEntryName(keyboardButton15.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton16.getResources().getResourceEntryName(keyboardButton16.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton17.getResources().getResourceEntryName(keyboardButton17.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton18.getResources().getResourceEntryName(keyboardButton18.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton19.getResources().getResourceEntryName(keyboardButton19.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton20.getResources().getResourceEntryName(keyboardButton20.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton21.getResources().getResourceEntryName(keyboardButton21.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton22.getResources().getResourceEntryName(keyboardButton22.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton23.getResources().getResourceEntryName(keyboardButton23.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton24.getResources().getResourceEntryName(keyboardButton24.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton25.getResources().getResourceEntryName(keyboardButton25.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });


        keyboardButton26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = keyboardButton26.getResources().getResourceEntryName(keyboardButton26.getId());
                int newKeyCode = getKeyCodeFromId(idString);
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
                onKeyLogic(event, newKeyCode);
            }
        });

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
        count += 1;

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
        hideSoftKeyboard(guess_box);
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

    public int getKeyCodeFromId (String ID) {
        switch (ID) {
            case "imageView1":
                return KeyEvent.KEYCODE_Q;
            case "imageView2":
                return KeyEvent.KEYCODE_W;
            case "imageView3":
                return KeyEvent.KEYCODE_E;
            case "imageView4":
                return KeyEvent.KEYCODE_R;
            case "imageView5":
                return KeyEvent.KEYCODE_T;
            case "imageView6":
                return KeyEvent.KEYCODE_Y;
            case "imageView7":
                return KeyEvent.KEYCODE_U;
            case "imageView8":
                return KeyEvent.KEYCODE_I;
            case "imageView9":
                return KeyEvent.KEYCODE_O;
            case "imageView10":
                return KeyEvent.KEYCODE_P;
            case "imageView11":
                return KeyEvent.KEYCODE_A;
            case "imageView12":
                return KeyEvent.KEYCODE_S;
            case "imageView13":
                return KeyEvent.KEYCODE_D;
            case "imageView14":
                return KeyEvent.KEYCODE_F;
            case "imageView15":
                return KeyEvent.KEYCODE_G;
            case "imageView16":
                return KeyEvent.KEYCODE_H;
            case "imageView17":
                return KeyEvent.KEYCODE_J;
            case "imageView18":
                return KeyEvent.KEYCODE_K;
            case "imageView19":
                return KeyEvent.KEYCODE_L;
            case "imageView20":
                return KeyEvent.KEYCODE_Z;
            case "imageView21":
                return KeyEvent.KEYCODE_X;
            case "imageView22":
                return KeyEvent.KEYCODE_C;
            case "imageView23":
                return KeyEvent.KEYCODE_V;
            case "imageView24":
                return KeyEvent.KEYCODE_B;
            case "imageView25":
                return KeyEvent.KEYCODE_N;
            case "imageView26":
                return KeyEvent.KEYCODE_M;
            default:
                return 0;
        }
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
}