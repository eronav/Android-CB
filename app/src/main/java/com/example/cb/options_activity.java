package com.example.cb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class options_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_activity);
        ImageButton bck_btn_option = (ImageButton) findViewById(R.id.bck_btn_option);
        if(getIntent().hasExtra("com.mailronav.cb.U")){

        }
        bck_btn_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), game_2.class);
                startIntent.putExtra("com.mailronav.cb.option", "");
                startActivity(startIntent);
            }
        });
    }
}
