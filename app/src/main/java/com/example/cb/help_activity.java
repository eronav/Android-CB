package com.example.cb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class help_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_activity);
        ImageButton bck_btn_help = (ImageButton) findViewById(R.id.help_bck_btn);
        Button next = findViewById(R.id.next);
        Button prev = findViewById(R.id.prev);

        if (getIntent().hasExtra("com.mailronav.cb.A")){

        }
        if (getIntent().hasExtra("com.mailronav.cb.Help")) {

        }
        bck_btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), game_2.class);
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
