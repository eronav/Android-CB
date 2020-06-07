package com.example.cb;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MusicManager {

    private Context myctxt;
    private MediaPlayer player;

    MusicManager(Context appctxt) {
        myctxt = appctxt;
        player = null;
    }

    public void play() {
        if (player == null) {
            player = MediaPlayer.create(myctxt, R.raw.world);
        } else {
            player.reset();
        }
        if (player != null) {
            player.start();
        }
    }

    public void play_works_but_trying_simpler() {
        if (player == null) {
            Resources res = myctxt.getResources();
            AssetFileDescriptor afd = res.openRawResourceFd(R.raw.world);

            player = new MediaPlayer();
            /*
            Uri mediaUri = Uri.parse("android.resource://" + myctxt.getPackageName() + "/" + R.raw.world);

            try {
                player.setDataSource(myctxt, mediaUri);
                Toast.makeText(myctxt, String.valueOf(player.getDuration()), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            } */
            // player = MediaPlayer.create(myctxt, R.raw.world);
            if (player != null) {
                try {
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    player.prepare();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            player.reset();
        }

        if (player != null) {
            Toast.makeText(myctxt, "Starting play", Toast.LENGTH_SHORT).show();
            player.start();
        }
    }


    public void stop() { stopPlayer(); }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(myctxt, "player released", Toast.LENGTH_SHORT).show();
        }
    }
}
