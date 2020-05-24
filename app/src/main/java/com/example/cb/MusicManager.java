package com.example.cb;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Toast;

import java.io.IOException;

public class MusicManager {

    private Context myctxt;
    private MediaPlayer player;

    MusicManager(Context appctxt) {
        myctxt = appctxt;
        player = null;
    }

    public void play() {
        if (player == null) {
            player = new MediaPlayer();
            Uri mediaUri = Uri.parse("android.resource://" + myctxt.getPackageName() + "/" + R.raw.world);

            try {
                player.setDataSource(myctxt, mediaUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // player = MediaPlayer.create(myctxt, R.raw.world);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        if (player != null) {
            player.start();
        }
    }
    public void stop() {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(myctxt, "player released", Toast.LENGTH_SHORT).show();
        }
    }
}
