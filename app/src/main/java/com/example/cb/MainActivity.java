package com.example.cb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button directBtn = (Button) findViewById(R.id.direction);
        Button gameBtn = (Button) findViewById(R.id.game);
        Button newGameBtn = (Button) findViewById(R.id.newGameBtn);
        directBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), directActivity.class);
                startIntent.putExtra("com.mailronav.cb.SOMETHING", "The game cows and bulls is a simple game. It is a word " +
                        "guessing game. I will tell you how many letters my word has. Then enter a word with that many letters and press " +
                        "enter or return. Then I will say whether you are correct or not. If not then I will say how many cows and/or bulls " +
                        "you have. A bull is one of the letters in your word in the correct place as in my word. For example: If my word is " +
                        "tire and you enter cake, then you will have 1 bull because the 'e' is the correct letter in the correct place. A " +
                        "cow is the correct letter in the wrong place. For example: my word is still tire but you enter stop then you will " +
                        "get 1 cow because the 't' in your word is the correct letter but in the wrong place. To find out which letter is " +
                        "the cow or the bull you have to test it with other words. Now the Game!");
                startActivity(startIntent);
            }
        });
        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), gameActivity.class);
                startIntent.putExtra("com.mailronav.cb.ANYTHING", "");
                startActivity(startIntent);
            }
        });
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), game_2.class);
                startIntent.putExtra("com.mailronav.cb.DUDE", "");
                startActivity(startIntent);
            }
        });
        if(getIntent().hasExtra("com.mailronav.cb.NOTHING")){

        }
        if(getIntent().hasExtra("com.mailronav.cb.THING")){

        }

    }
}
