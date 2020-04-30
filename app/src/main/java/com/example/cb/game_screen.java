package com.example.cb;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Create the list View

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        histbox = findViewById(R.id.history);
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

        cb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        newrow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        cb.setOrientation(LinearLayout.HORIZONTAL);
        newrow.setOrientation(LinearLayout.HORIZONTAL);
        row.setOrientation(LinearLayout.VERTICAL);

        histbox.addView(row);

        final boolean check_keyboard = false;

        int diff;
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
        String word = wgen.getWord();


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
                if (check_game) {
                    Intent startIntent = new Intent(getApplicationContext(), play_again.class);
                    startIntent.putExtra("com.mailromav.cb.one", "");
                    startActivity(startIntent);
                }*/

                LinearLayout set_img = add_row();
                row.addView(set_img);
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
                    //newrow.addView(letterView);
                }
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
        LinearLayout innerrow = new LinearLayout(myctxt);
        innerrow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        innerrow.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout cow_img_layout = new LinearLayout(myctxt);
        cow_img_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        cow_img_layout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout bull_img_layout = new LinearLayout(myctxt);
        bull_img_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bull_img_layout.setOrientation(LinearLayout.HORIZONTAL);

        row.setOrientation(LinearLayout.VERTICAL);
        for(int num = 0; num < 3; num++) {

            ImageView my_cow_img = get_cow_img();
            ImageView my_bull_img = get_bull_img();
            cow_img_layout.addView(my_cow_img);
            bull_img_layout.addView(my_bull_img);
        }

        innerrow.addView(bull_img_layout);
        innerrow.addView(cow_img_layout);

        return innerrow;
    }

    public void setDebugBox(String s) {
        testing_dash.setText(s);
    }

}
