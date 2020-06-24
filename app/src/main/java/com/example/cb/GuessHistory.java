package com.example.cb;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GuessHistory {
    ScrollView scrollBox;
    ListView listBox;
    List<String> List_file;
    List<Guess> historyList;
    ArrayAdapter<String> listAdapter;
    AppCompatActivity appcontext;

    private ScrollView histbox;
    private LinearLayout row;


    GuessHistory(AppCompatActivity appcontext) {

        this.appcontext = appcontext;

        List_file = new ArrayList<String>();
        historyList = new ArrayList<Guess>();

        // This LinearLayout is the layout organizer for the ScrollView, histbox
        // TBD: See if we can do this in XML and then remove from there
        row = new LinearLayout(appcontext);
        row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        row.setOrientation(LinearLayout.VERTICAL);

        histbox = appcontext.findViewById(R.id.history);
        histbox.addView(row);
    }


    public void CreateListView(AppCompatActivity ctxt, ListView lbox) {
        //List_file.add("Apple");
        //Create an adapter for the listView and add the ArrayList to the adapter.

        appcontext = ctxt;
        listBox = lbox;

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

    public LinearLayout AddEntry_old(WordGenerator wgen, String guess, LinearLayout guess_pict, Context myctxt) {
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
            //myeval.addView(guess_pict);
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

    public void AddEntry(String guess, int[] result, boolean restore) {

        LinearLayout word_and_eval_layout = new LinearLayout(appcontext);

        // Fill out a layout with the guess
        GameEnvironment.ltrmngr.get_image_for_word(guess, GameEnvironment.guessmngr, word_and_eval_layout, appcontext);

        // Add some spacing
        ImageView word_eval_spacing = new ImageView(appcontext);
        word_eval_spacing.setLayoutParams(LetterImageManager.getLayoutParams());
        word_and_eval_layout.addView(word_eval_spacing);

        // Evaluate the result
        get_img_cb_eval(guess, result, appcontext, GameEnvironment.ltrmngr, GameEnvironment.diff, word_and_eval_layout);

        // Add a new row to the guess history box
        row.addView(word_and_eval_layout, 0);

        if (!restore)
            addToHistoryList(guess, result[0], result[1]);
    }

    public void get_img_cb_eval(String guess, int[] result, Context myctxt, LetterImageManager ltrmngr, int diff, LinearLayout cb_eval) {
        int cow = result[1];
        int bull = result[2];

        if(bull == diff) {
            fill_layout_with_eval_img(1, R.drawable.firework, cb_eval);
        } else if(cow == 0 && bull == 0) {
            fill_layout_with_eval_img(1, R.drawable.bomb, cb_eval);
        } else {
            fill_layout_with_eval_img(bull, R.drawable.bulls_head, cb_eval);
            fill_layout_with_eval_img(cow, R.drawable.cow_head, cb_eval);
        }
    }

    public LinearLayout get_img_cb_eval_numeric(String guess, WordGenerator wgen, Context myctxt, LetterImageManager ltrmngr, int diff) {
        int[] result = wgen.evaluateGuess(guess);
        int cow = result[1];
        int bull = result[2];
        int cow_num_id = ltrmngr.get_cow_num_id(cow);
        int bull_num_id = ltrmngr.get_bull_num_id(bull);
        LinearLayout cb_eval = new LinearLayout(myctxt);
        ImageView cow_text = new ImageView(myctxt);
        ImageView bull_text = new ImageView(myctxt);
        ImageView cow_img = new ImageView(myctxt);
        ImageView bull_img = new ImageView(myctxt);
        cb_eval.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        cow_img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bull_img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        cow_text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bull_text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        cb_eval.setOrientation(LinearLayout.HORIZONTAL);

        ViewGroup.LayoutParams cow_layout = cow_img.getLayoutParams();
        cow_layout.height = 60;
        cow_layout.width = 60;
        cow_img.setScaleType(ImageView.ScaleType.FIT_CENTER);

        ViewGroup.LayoutParams bull_layout = bull_img.getLayoutParams();
        bull_layout.height = 60;
        bull_layout.width = 60;
        bull_img.setScaleType(ImageView.ScaleType.FIT_CENTER);

        ViewGroup.LayoutParams cow_num_layout = cow_text.getLayoutParams();
        cow_num_layout.height = 60;
        cow_num_layout.width = 60;
        cow_text.setScaleType(ImageView.ScaleType.FIT_CENTER);

        ViewGroup.LayoutParams bull_num_layout = bull_text.getLayoutParams();
        bull_num_layout.height = 60;
        bull_num_layout.width = 60;
        bull_text.setScaleType(ImageView.ScaleType.FIT_CENTER);

        cow_text.setImageResource(cow_num_id);
        bull_text.setImageResource(bull_num_id);
        cow_img.setImageResource(R.drawable.cow_head);
        bull_img.setImageResource(R.drawable.bulls_head);

        if(cow == 0 && bull == 0) {
            ImageView bomb_pic = new ImageView(myctxt);
            bomb_pic.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ViewGroup.LayoutParams bomb_layout = bomb_pic.getLayoutParams();
            bomb_layout.height = 60;
            bomb_layout.width = 60;
            bomb_pic.setImageResource(R.drawable.bomb);
            cb_eval.addView(bomb_pic);
        } else if(bull == diff) {
            ImageView firework_pic = new ImageView(myctxt);
            firework_pic.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ViewGroup.LayoutParams firework_layout = firework_pic.getLayoutParams();
            firework_layout.height = 60;
            firework_layout.width = 60;
            firework_pic.setImageResource(R.drawable.firework);
            cb_eval.addView(firework_pic);
        } else {
            cb_eval.addView(bull_text);
            cb_eval.addView(bull_img);
            cb_eval.addView(cow_text);
            cb_eval.addView(cow_img);
        }

        return cb_eval;
    }

    public String end_game_new_word(WordGenerator wgen, String guess, int diff, String word) {
        int[] result = wgen.evaluateGuess(guess);
        if(result[2] == diff) {
            String new_word = wgen.targetGen(diff);
            return new_word;
        } else {
            return word;
        }

    }

    public boolean check_game(WordGenerator wgen, String guess, int diff) {
        int[] result = wgen.evaluateGuess(guess);
        if(result[2] == diff) {
            return true;
        } else {
            return false;
        }
    }

    // Fills the provided LinearLayout with the specified image up to count times
    public void fill_layout_with_eval_img(int count, @DrawableRes int img_id, LinearLayout layout) {
        for (int i = 0; i < count; i++) {
            ImageView animal_img = new ImageView(appcontext);
            animal_img.setLayoutParams(LetterImageManager.getLayoutParams());
            // animal_img.setBackgroundDrawable(new Border(0xffff0000, 50));
            animal_img.setImageResource(img_id);
            layout.addView(animal_img);
        }
    }

    public LinearLayout restoreHistory () {
        // TBD: Do I need to clear up contents of the previous row
        for ( Guess guess: historyList) {
            AddEntry(guess.getWord(), guess.getEval(), true);
        }
        return row;
    }

    public void addToHistoryList (String word, int cows, int bulls) {
        historyList.add(new Guess(word, cows, bulls));
    }

}