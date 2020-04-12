package com.example.cb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class gameActivity extends AppCompatActivity {
    Random myrand = new Random();
    EditText diffbox;
    TextView errbox;
    int culty;
    String emptyString = "No input found";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Button homescreen2 = (Button) findViewById(R.id.homescreen2);
        diffbox = (EditText) findViewById(R.id.editText2);
        errbox = (TextView) findViewById(R.id.textView2);
        Button diffDone = (Button) findViewById(R.id.diffDone);
        Button guessDone = (Button) findViewById(R.id.wordBtn);

        /* MDCHG
        final int diff = Integer.parseInt(diffbox.getText().toString());
        String word = "";
        for(int i = 0; i < diff;){
            int r = random.nextInt(26) + 97;
            String r1 = String.valueOf((char)r);
            if(word.contains(r1)){
                continue;
            } else {
                word += r1;
                i++;
            }
        }
        final String word1 = word;
        errbox.setText(word1);

        if(getIntent().hasExtra("com.mailronav.cb.ANYTHING")){
            random = new Random();
            String r = "";

        }
         */
        diffDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputstr = diffbox.getText().toString();
                try {
                    int culty = Integer.parseInt(inputstr);
                    String word = "";

                    for (int i = 0; i < culty;) {
                        int r = myrand.nextInt(26) + 65;
                        String r1 = String.valueOf((char) r);
                        if (word.contains(r1)) {
                            continue;
                        } else {
                            word += r1;
                            i++;
                        }
                    }
                    errbox.setText(word);

                } catch (NumberFormatException e){
                    errbox.setText(e.toString());
                }
            }
        });

        guessDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                String guess = null;
                if (count == 0) {
                    // diffbox.setHint("enter a word");
                    guess = String.valueOf(diffbox.getText());
                    if (guess.isEmpty()) {
                        errbox.setText(emptyString);
                    } else {
                        errbox.setText(guess);
                    }
                    /*
                    while(guess.length() != word1.length()){
                        errbox.setText("Wrong number of letters. Please enter a word with " + diff + " letters");
                        int diff1 = Integer.parseInt(diffbox.getText().toString());
                    }
                    */
                    /*int cows = 0;
                    int bulls = 0;
                    for(int i = 0; i < guess.length(); i++){
                        if(guess.equals(word1)){
                            count = word1.length();
                            break;
                        } else if((guess.charAt(i) == word1.charAt(i))){
                            bulls += 1;
                        } else {
                            for(int l = 0; l < word1.length(); l++){
                                if(guess.charAt(i) == word1.charAt(l)){
                                    cows += 1;
                                }
                            }
                        }
                    }
                    if(count != word1.length()){
                        errbox.setText("Your word has " + bulls + " bulls and " +
                                "" + cows + " cows. Please guess again");
                    } else {
                        errbox.setText("You are correct!");
                    }
                    final int cows1 = cows;
                    final int bulls1 = bulls;*/
                }
            }
        });

        homescreen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startIntent.putExtra("com.mailronav.cb.THING", "");
                startActivity(startIntent);
            }
        });
    }
}

