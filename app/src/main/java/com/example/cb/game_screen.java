package com.example.cb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class game_screen extends AppCompatActivity {

    ListView histbox;
    EditText guess_box;
    Button done_btn;
    TextView testing_dash;
    Random myrand;
    WordGenerator wgen;
    GuessHistory ghist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Create the list View

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        histbox = findViewById(R.id.history);
        guess_box = (EditText) findViewById(R.id.guess_box);
        done_btn = (Button) findViewById(R.id.done_btn);
        testing_dash = (TextView) findViewById(R.id.word);
        myrand= new Random();

        int diff;
        String dash = "";
        int i = 0;
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
        ghist.CreateListView(game_screen.this, histbox);


        done_btn.setOnClickListener(new View.OnClickListener() {
            String guess = "";
            boolean check_game;
            @Override
            public void onClick(View v) {
                guess = String.valueOf(guess_box.getText());
                check_game = ghist.AddEntry(wgen, guess);
                guess_box.setText("");
                if (check_game) {
                    Intent startIntent = new Intent(getApplicationContext(), play_again.class);
                    startIntent.putExtra("com.mailromav.cb.one", "");
                    startActivity(startIntent);
                }
            }
        });
        testing_dash.setText(word);


    }
    //Create the creation method

    public void setDebugBox(String s) {
        testing_dash.setText(s);
    }

}
