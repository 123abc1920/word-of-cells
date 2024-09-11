package com.example.planetgame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class EndGameDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("The End")
                .setMessage(MainActivity.game.text)
                .setPositiveButton("Start new", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Context context = MainActivity.game.context;
                        FragmentManager manager = MainActivity.game.manager;
                        MainActivity.game = new Game(manager, context);
                        dialog.cancel();
                    }
                });
        return builder.create();
    }
}
