package com.example.cb;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class game_screen extends AppCompatActivity {

    ScrollView histbox;
    LinearLayout guess_box;
    EditText dummy_input;
    Button done_btn;
    TextView testing_dash;
    Random myrand;
    WordGenerator wgen;
    GuessHistory ghist;
    LetterImageManager ltrmngr;
    int[] id_array;
    Context myctxt;
    LinearLayout newrow;
    String guess;
    LinearLayout cb;
    LinearLayout row;
    int i = 0;
    int diff;
    boolean keyboard_status;
    String word;
    LinearLayout quit_layout;
    LinearLayout play_again_layout;
    LinearLayout word_outer_layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Create the list View

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        histbox = findViewById(R.id.history);
        Button keyboard_btn = (Button) findViewById(R.id.keyboard);
        keyboard_status = false;
        dummy_input = findViewById(R.id.dummy_input);
        myctxt = getApplicationContext();
        guess_box = findViewById(R.id.guess_box);
        done_btn = findViewById(R.id.done_btn);
        testing_dash = findViewById(R.id.word);
        myrand= new Random();
        ltrmngr = new LetterImageManager();
        id_array = new int[6];
        guess = "";
        cb = new LinearLayout(myctxt);
        newrow = new LinearLayout(myctxt);
        row = new LinearLayout(myctxt);
        play_again_layout = (LinearLayout) findViewById(R.id.play_again_layout);
        quit_layout = (LinearLayout) findViewById(R.id.quit_layout);
        word_outer_layout = new LinearLayout(myctxt);

        cb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        newrow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        cb.setOrientation(LinearLayout.HORIZONTAL);
        newrow.setOrientation(LinearLayout.HORIZONTAL);
        row.setOrientation(LinearLayout.VERTICAL);

        histbox.addView(row);

        final boolean check_keyboard = false;


        String dash = "";
        final int i = 0;
        if(getIntent().hasExtra("com.mailronav.cb.three")){
            diff = 3;
        } else if(getIntent().hasExtra("com.mailronav.cb.four")){
            diff = 4;
        } else if(getIntent().hasExtra("com.mailronav.cb.five")){
            diff = 5;
        } else {
            diff = 6;
        }
        /*for(int i = 0; i < diff; i++){
            dash += "-";
        }
        testing_dash.setText(dash);*/

        wgen = new WordGenerator(diff);
        word = wgen.getWord();


        ghist = new GuessHistory();
        //ghist.CreateListView(game_screen.this, histbox);


        done_btn.setOnClickListener(new View.OnClickListener() {
            boolean check_game;
            @Override
            public void onClick(View v) {

                /*cb = ghist.AddEntry(wgen, guess, newrow, myctxt);
                // histbox.removeAllViews();
                histbox.addView(cb);
                dummy_input.setText("");
                if (ghist.check_game(wgen, guess, diff)) {
                    Button play_again = new Button(myctxt);
                    Button quit_btn = new Button(myctxt);
                    play_again.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    quit_btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    ViewGroup.LayoutParams btn_layout = play_again.getLayoutParams();
                    ViewGroup.LayoutParams btn1_layout = play_again.getLayoutParams();
                    btn_layout.height = 50;
                    btn_layout.width = 50;
                    btn1_layout.height = 50;
                    btn1_layout.width = 50;
                    play_again.setText("Play Again");
                    quit_btn.setText("Quit");

                    play_again_layout.addView(play_again);
                    quit_layout.addView(quit_btn);
                    play_again.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent startIntent = new Intent(getApplicationContext(), play_game_level.class);
                            startIntent.putExtra("com.mailronav.cb.playAgain", "");
                            startActivity(startIntent);
                        }
                    });
                    quit_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent startIntent = new Intent(getApplicationContext(), game_2.class);
                            startIntent.putExtra("com.mailronav.cb.quit", "");
                            startActivity(startIntent);
                        }
                    });
                }*/

                LinearLayout set_img_eval = ghist.get_img_cb_eval(guess, wgen, myctxt, ltrmngr, diff);
                // LinearLayout set_img_word = set_word_img(guess);
                guess_box.removeAllViews();
                row.addView(set_img_eval);
                // row.addView(set_word_img1(guess));
                dummy_input.setText("");

            }
        });

        keyboard_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyboard_status == true) {
                    hideKeyboardFrom(myctxt, dummy_input);
                } else {
                    openKeyboard();
                }
            }
        });



        dummy_input.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // String msgstr = "Input is " + s + " and change started at " + String.valueOf(start) + " for " + String.valueOf(count);
                // errbox.setText(msgstr);
                /*LinearLayout innerrow = new LinearLayout(myctxt);
                innerrow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                innerrow.setOrientation(LinearLayout.HORIZONTAL);*/

                String sequence = s.toString();
                sequence.toLowerCase();

                guess_box = set_word_img(sequence);

                /*TextView test_text = new TextView(myctxt);
                test_text.setText("testing");
                histbox.removeAllViews();
                histbox.addView(test_text);*/
                //histbox.addView(guess_box);
                //histbox.addView(newrow);
                guess = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        testing_dash.setText(word);


    }  // onCreate()


    //Create the creation method
    public ImageView get_cow_img() {
        ImageView cow_img = new ImageView(myctxt);
        cow_img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ViewGroup.LayoutParams cow_layout = cow_img.getLayoutParams();
        cow_layout.height = 120;
        cow_layout.width = 120;
        cow_img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        cow_img.setImageResource(R.drawable.cow_head);
        return cow_img;
    }



    public ImageView get_bull_img() {
        ImageView bull_img = new ImageView(myctxt);
        bull_img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ViewGroup.LayoutParams bull_layout = bull_img.getLayoutParams();
        bull_layout.height = 120;
        bull_layout.width = 120;
        bull_img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        bull_img.setImageResource(R.drawable.bulls_head);
        return bull_img;
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
        guess_box.removeAllViews();
        for(int i = 0; i < sequence.length(); i++){
            ImageView letterView = new ImageView(myctxt);
            letterView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ViewGroup.LayoutParams btn_layout = letterView.getLayoutParams();
            btn_layout.height = 50;
            btn_layout.width = 50;
            letterView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            letterView.setImageResource(letter_ids[i]);
            guess_box.addView(letterView);
            // newrow.addView(letterView);
        }
        return guess_box;
    }



}
