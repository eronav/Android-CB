package com.example.cb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class play_again extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_again);
        Button play_again = (Button) findViewById(R.id.play_again);
        Button quit_btn = (Button) findViewById(R.id.quit);
        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), play_game_level.class);
                startIntent.putExtra("com.mailronav.cb.again", "");
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
        if(getIntent().hasExtra("com.mailronav.cb.one")){

        }
    }
}
