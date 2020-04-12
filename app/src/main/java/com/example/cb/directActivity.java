package com.example.cb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class directActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct);
        Button homeScreenBtn = (Button) findViewById(R.id.homescreen);
        if(getIntent().hasExtra("com.mailronav.cb.SOMETHING")){
            TextView tv = (TextView) findViewById(R.id.textView);
            String text = getIntent().getExtras().getString("com.mailronav.cb.SOMETHING");
            tv.setText(text);
        }
        homeScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startIntent.putExtra("com.mailronav.cb.NOTHING", "");
                startActivity(startIntent);
            }
        });
    }
}
