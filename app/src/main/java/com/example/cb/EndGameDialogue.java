package com.example.cb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class EndGameDialogue extends AppCompatDialogFragment {

    private int count;
    EndGameDialogue (int count) {
        this.count = count;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("You Are A Winner! It took " + (count) + " guesses!")
                .setMessage("Do you want to...")
                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        GameEnvironment.main_game.play_again_logic();
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        GameEnvironment.main_game.quit_logic();
                    }
                });
        return builder.create();
    }
}