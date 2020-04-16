package com.example.cb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class game_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_2);
        Button play_btn = (Button) findViewById(R.id.play_btn);
        final Button options_btn = (Button) findViewById(R.id.option_btn);
        Button help_btn = (Button) findViewById(R.id.help_btn);
        if(getIntent().hasExtra("com.mailronav.cb.DUDE")){

        }
        if(getIntent().hasExtra("com.mailronav.cb.B")){

        }
        if(getIntent().hasExtra("com.mailronav.cb.quit")){

        }
        if(getIntent().hasExtra("com.mailronav.cb.option")){

        }
        if(getIntent().hasExtra("com.mailronav.cb.help")){

        }
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), play_game_level.class);
                startIntent.putExtra("com.mailronav.cb.YOU", "");
                startActivity(startIntent);
            }
        });
        options_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), options_activity.class);
                startIntent.putExtra("com.mailronav.cb.U", "");
                startActivity(startIntent);
            }
        });
        help_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), help_activity.class);
                startIntent.putExtra("com.mailronav.cb.A", "");
                startActivity(startIntent);
            }
        });
    }
}
