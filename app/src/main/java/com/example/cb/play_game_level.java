package com.example.cb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class play_game_level extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_play_game_level);
        
        TextView view3 = findViewById(R.id.view3);
        TextView view4 = findViewById(R.id.view4);
        TextView view5 = findViewById(R.id.view5);
        TextView view6 = findViewById(R.id.view6);

        view3.setClickable(true);
        view4.setClickable(true);
        view5.setClickable(true);
        view6.setClickable(true);
        
        if(getIntent().hasExtra("com.mailronav.cb.YOU")){

        }
        if(getIntent().hasExtra("com.mailronav.cb.again")){

        }
        if(getIntent().hasExtra("com.mailronav.cb.playAgain")){

        }
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GameEnvironment.word = GameEnvironment.wgen.targetGenFromDict(3);
                Intent startIntent = new Intent(getApplicationContext(), game_screen.class);
                startIntent.putExtra("com.mailronav.cb.three", "");
                startActivity(startIntent);
            }
        });
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GameEnvironment.word = GameEnvironment.wgen.targetGenFromDict(4);
                Intent startIntent = new Intent(getApplicationContext(), game_screen.class);
                startIntent.putExtra("com.mailronav.cb.four", "");
                startActivity(startIntent);
            }
        });
        view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GameEnvironment.word = GameEnvironment.wgen.targetGenFromDict(5);
                Intent startIntent = new Intent(getApplicationContext(), game_screen.class);
                startIntent.putExtra("com.mailronav.cb.five", "");
                startActivity(startIntent);
            }
        });
        view6.setOnClickListener(new View.OnClickListener() {
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
