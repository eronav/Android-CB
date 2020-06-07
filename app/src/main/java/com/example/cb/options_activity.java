package com.example.cb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class options_activity extends AppCompatActivity {

    private Switch musicSwitch;
    private Switch doubleLetterSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_activity);

        ImageButton backBtn = (ImageButton) findViewById(R.id.imageButton2);

        musicSwitch = (Switch) findViewById(R.id.musicSwitch);
        doubleLetterSwitch = (Switch) findViewById(R.id.doubleSwitch);

        musicSwitch.setChecked(GameEnvironment.ups.IsMusicOn());
        doubleLetterSwitch.setChecked(GameEnvironment.ups.IsDupsOn());

        if (getIntent().hasExtra("com.mailronav.cb.U")) {

        }
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), game_2.class);
                startIntent.putExtra("com.mailronav.cb.bak", "");
                startActivity(startIntent);
            }
        });

        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (musicSwitch.isChecked()) {
                    GameEnvironment.musicman.play();
                } else {
                    GameEnvironment.musicman.stop();
                }
                GameEnvironment.ups.SetMusic(musicSwitch.isChecked());
                Toast.makeText(getApplicationContext(), String.valueOf(GameEnvironment.ups.IsMusicOn()), Toast.LENGTH_SHORT).show();
            }
        });

        doubleLetterSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                GameEnvironment.ups.SetDups(doubleLetterSwitch.isChecked());
            }
        });
    }

    @Override
    protected void onStop () {
        // This function is for display-purposes only now. Called when the activity is about to die
        Toast.makeText(getApplicationContext(), "OnStop was called", Toast.LENGTH_SHORT).show();
        super.onStop();
    }
}
