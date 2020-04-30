package com.example.cb;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GuessHistory {
    ScrollView scrollBox;
    ListView listBox;
    List<String> List_file;
    ArrayAdapter<String> listAdapter;
    Context appcontext;
    int [] id_array;

    GuessHistory() {
        List_file = new ArrayList<String>();
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

    public LinearLayout AddEntry(WordGenerator wgen, String guess, LinearLayout guess_pict, Context myctxt) {
        String errmsg;
        int[] result;
        int attempts = List_file.size() + 1;
        boolean game_status = false;
        LinearLayout myeval = new LinearLayout(myctxt);
        myeval.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout c_b_layout = new LinearLayout(myctxt);
        c_b_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        c_b_layout.setOrientation(LinearLayout.HORIZONTAL);
        TextView c_b = new TextView(myctxt);
        result = wgen.evaluateGuess(guess);

        // ((game_screen)appcontext).setDebugBox(sz_a + "." + sz_b + "." + sz_c);
        if (result[0] >= 0) {
            int cows;
            int bulls;
            //int [] img_array = ltrmngr.cows_bulls(cows, bulls, id_array);
            //return true;
            cows = result[1];
            bulls = result[2];
            String stringCow = String.valueOf(cows);
            String stringBull = String.valueOf(bulls);
            errmsg = ("Your word has " + bulls + " bulls and " + cows + " cows. Please guess again");
            c_b.setText(stringBull + "B " + stringCow + "C");
            c_b_layout.addView(c_b);

            // Add the last row and the CB structure
            myeval.setOrientation(LinearLayout.HORIZONTAL);
            myeval.addView(guess_pict);
            myeval.addView(c_b_layout);

            //List_file.add(guess + ": " + stringCow + "C " + stringBull + "B: guess " + String.valueOf(attempts));
        } else if (result[0] == 1) {
            //List_file.add(guess + ": Correct guess in: " + String.valueOf(attempts) + " guesses!");
            c_b.setText("You got it correct");
            myeval.addView(c_b);
        } else {
            errmsg = ("Incompatible guess");
            c_b.setText(errmsg);
            myeval.addView(c_b);
        }

        //listAdapter.notifyDataSetChanged();
        return myeval;
    }

    public void ProcessInput(CharSequence s, int start, int before, int count) {

        boolean ignoreChange = false;
        String laststr = "";
        String diffstr = "Diffs: ";


        // String msgstr = "Input is " + s + " and change started at " + String.valueOf(start) + " for " + String.valueOf(count);
        // errbox.setText(msgstr);

        int[] differs = new int[10];
        int idx = 0, i = 0, oldlen = laststr.length();
        String instr = s.toString();
        int minlen = oldlen <= instr.length() ? oldlen : s.length();

        diffstr = "VisToken: ";
        for (i = 0; i < minlen; i++) {
            if (instr.charAt(i) != laststr.charAt(i)) {
                // diffstr.concat("<" + String.valueOf(i) + ":" + instr.charAt(i) + "> ");
                diffstr.concat("Has min changes");
            }
        }
        if (instr.length() > minlen) {
            diffstr.concat("INstr longer");
            // diffstr.concat("<" + String.valueOf(minlen) + ":" + instr.substring(minlen));
        } else if (oldlen > minlen) {
            diffstr.concat("Laststr longer");
            // diffstr.concat("<" + String.valueOf(minlen) + ":" + laststr.substring(minlen));
        } else {
            diffstr.concat("SHould not be here");
        }

        // diffstr = "Last - " + laststr + " Curr - " + instr + " has " + String.valueOf(idx) + " changes";
        // errbox.setText(diffstr);

        // cloning s
        laststr = "";
        for (i = 0; i < s.length(); i++)
            laststr += s.charAt(i);

    }

}