package com.example.cb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class game_2 extends AppCompatActivity {

    public Context myappctxt;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_game_2);
        myappctxt = getApplicationContext();

        TextView playView = (TextView) findViewById(R.id.playView);
        TextView helpView = (TextView) findViewById(R.id.helpView);
        TextView optionsView = (TextView) findViewById(R.id.optionsView);

        playView.setClickable(true);
        helpView.setClickable(true);
        optionsView.setClickable(true);

        if(getIntent().hasExtra("com.mailronav.cb.DUDE")){

        }
        if(getIntent().hasExtra("com.mailronav.cb.B")){

        }
        if(getIntent().hasExtra("com.mailronav.cb.quit")){

        }
        if(getIntent().hasExtra("com.mailronav.cb.option")) {

        }
        if(getIntent().hasExtra("com.mailronav.cb.help")){

        }

        GameEnvironment.ups = new UserPrefs();
        GameEnvironment.musicman = new MusicManager(myappctxt);

        playView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), play_game_level.class);
                startIntent.putExtra("com.mailronav.cb.YOU", "");
                startActivity(startIntent);
            }
        });
        optionsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), options_activity.class);
                startIntent.putExtra("com.mailronav.cb.U", "");
                startActivity(startIntent);
            }
        });
        helpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), help_activity.class);
                startIntent.putExtra("com.mailronav.cb.A", "");
                startActivity(startIntent);
            }
        });
    }
}
