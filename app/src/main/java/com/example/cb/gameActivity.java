package com.example.cb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class gameActivity extends AppCompatActivity {
    Random myrand = new Random();
    EditText diffbox;
    TextView errbox;
    int culty;
    String emptyString = "No input found";
    String goal = "";
    ListView listBox;
    List<String> List_file;
    ArrayAdapter<String> listAdapter;
    int count = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Button homescreen2 = (Button) findViewById(R.id.homescreen2);
        diffbox = (EditText) findViewById(R.id.editText2);
        errbox = (TextView) findViewById(R.id.textView2);
        Button diffDone = (Button) findViewById(R.id.diffDone);
        Button guessDone = (Button) findViewById(R.id.wordBtn);


        List_file = new ArrayList<String>();
        listBox = (ListView)findViewById(R.id.histBox);
        CreateListView();

        diffDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputstr = diffbox.getText().toString();
                try {
                    int culty = Integer.parseInt(inputstr);

                    for (int i = 0; i < culty;) {
                        int r = myrand.nextInt(26) + 65;
                        String r1 = String.valueOf((char) r);
                        if (goal.contains(r1)) {
                            continue;
                        } else {
                            goal += r1;
                            i++;
                        }
                    }
                    errbox.setText(goal);
                    diffbox.setText("");
                    diffbox.setHint("enter a word");

                } catch (NumberFormatException e){
                    errbox.setText(e.toString());
                }
            }
        });

        guessDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = null;

                guess = String.valueOf(diffbox.getText());
                if (guess.isEmpty()) {
                    errbox.setText(emptyString);
                } else {
                    guess = guess.toUpperCase();

                    // Verify guess is of the same length
                    if (guess.length() != goal.length()){
                        errbox.setText("Wrong number of letters. Please enter a word with " + culty + " letters");
                    } else {
                        count += 1;
                        // Compare guess against target
                        if (guess.equals(goal)) {
                            errbox.setText("You are correct!");
                            List_file.add(0, guess + ": Correct guess in: " + String.valueOf(count) + " guesses!");
                            listAdapter.notifyDataSetChanged();
                        } else {
                            int cows = 0;
                            int bulls = 0;
                            for (int i = 0; i < guess.length(); i++) {
                                int idx = goal.indexOf(guess.charAt(i));
                                if (i == idx){
                                    bulls += 1;
                                } else if (idx != -1) {
                                    cows += 1;
                                } else {
                                    // Nothing to do
                                    ;
                                }
                            }
                            errbox.setText("Your word has " + bulls + " bulls and " +
                                "" + cows + " cows. Please guess again");
                            String stringCow = String.valueOf(cows);
                            String stringBull = String.valueOf(bulls);
                            List_file.add(0, guess + ": " + stringCow + "C " + stringBull + "B: guess " + String.valueOf(count));
                            listAdapter.notifyDataSetChanged();
                            diffbox.setText("");
                        }
                    }
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

    private void CreateListView() {
        //List_file.add("Apple");
        //Create an adapter for the listView and add the ArrayList to the adapter.

        listAdapter = new ArrayAdapter<String>(gameActivity.this, android.R.layout.simple_list_item_1, List_file);
        listBox.setAdapter(listAdapter);
        listBox.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                //args2 is the listViews Selected index
            }
        });
    }
}

