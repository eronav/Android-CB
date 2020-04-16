package com.example.cb;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GuessHistory {
    ListView listBox;
    List<String> List_file;
    ArrayAdapter<String> listAdapter;
    Context appcontext;

    GuessHistory() {
        List_file = new ArrayList<String>();
    }

    public void SetContext(Context ctxt) {
    }

    public void SetListBox(ListView lbox) {
    }

    public void CreateListView(Context ctxt, ListView lbox) {
        //List_file.add("Apple");
        //Create an adapter for the listView and add the ArrayList to the adapter.

        appcontext = ctxt;
        listBox = lbox;

        // List_file.add(0, "Entry1");
        // List_file.add(0, "Entry2");

        listAdapter = new ArrayAdapter<String>(appcontext, android.R.layout.simple_list_item_1, List_file);
        listBox.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();

        listBox.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                //args2 is the listViews Selected index
            }
        });
    }

    public boolean AddEntry(WordGenerator wgen, String guess) {
        String errmsg;
        int[] result = wgen.evaluateGuess(guess);
        int attempts = List_file.size() + 1;
        boolean game_status = false;

        // ((game_screen)appcontext).setDebugBox(sz_a + "." + sz_b + "." + sz_c);
        if (result[0] == 0) {
            int cows, bulls;

            cows = result[1]; bulls = result[2];
            errmsg = ("Your word has " + bulls + " bulls and " + cows + " cows. Please guess again");
            String stringCow = String.valueOf(cows);
            String stringBull = String.valueOf(bulls);
            List_file.add(0, guess + ": " + stringCow + "C " + stringBull + "B: guess " + String.valueOf(attempts));
        } else if (result[0] == 1) {
            List_file.add(0, guess + ": Correct guess in: " + String.valueOf(attempts) + " guesses!");
            game_status = true;
        } else {
            errmsg = ("Incompatible guess");
        }

        listAdapter.notifyDataSetChanged();
        return game_status;
    }
}