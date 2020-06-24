package com.example.cb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class play_game_level extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_play_game_level);
        Button btn3 = (Button) findViewById(R.id.button3);
        Button btn4 = (Button) findViewById(R.id.button4);
        Button btn5 = (Button) findViewById(R.id.button5);
        Button btn6 = (Button) findViewById(R.id.button6);

        if(getIntent().hasExtra("com.mailronav.cb.YOU")){

        }
        if(getIntent().hasExtra("com.mailronav.cb.again")){

        }
        if(getIntent().hasExtra("com.mailronav.cb.playAgain")){

        }
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GameEnvironment.word = GameEnvironment.wgen.targetGenFromDict(3);
                Intent startIntent = new Intent(getApplicationContext(), game_screen.class);
                startIntent.putExtra("com.mailronav.cb.three", "");
                startActivity(startIntent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GameEnvironment.word = GameEnvironment.wgen.targetGenFromDict(4);
                Intent startIntent = new Intent(getApplicationContext(), game_screen.class);
                startIntent.putExtra("com.mailronav.cb.four", "");
                startActivity(startIntent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GameEnvironment.word = GameEnvironment.wgen.targetGenFromDict(5);
                Intent startIntent = new Intent(getApplicationContext(), game_screen.class);
                startIntent.putExtra("com.mailronav.cb.five", "");
                startActivity(startIntent);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GameEnvironment.word = GameEnvironment.wgen.targetGenFromDict(6);
                Intent startIntent = new Intent(getApplicationContext(), game_screen.class);
                startIntent.putExtra("com.mailronav.cb.six", "");
                startActivity(startIntent);
            }
        });
    }
}
