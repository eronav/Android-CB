package com.example.cb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class help_activity extends AppCompatActivity {

    String intent;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_help_activity);
        ImageButton bck_btn_help = (ImageButton) findViewById(R.id.help_bck_btn);
        Button next = findViewById(R.id.next);
        Button prev = findViewById(R.id.prev);

        intent = "";

        if (getIntent().hasExtra("com.mailronav.cb.A")){
            intent = "game_2";
        }
        if (getIntent().hasExtra("com.mailronav.cb.Help")) {
            intent = "game_screen";
        }
        bck_btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent;
                if (intent.equals("game_2"))
                    startIntent = new Intent(getApplicationContext(), game_2.class);
                else
                    startIntent = new Intent(getApplicationContext(), game_screen.class);
                startIntent.putExtra("com.mailronav.cb.help", "");
                startActivity(startIntent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent startIntent = new Intent(getApplicationContext(), );
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
