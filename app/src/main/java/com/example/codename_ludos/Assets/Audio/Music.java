package com.example.codename_ludos.Assets.Audio;

import android.media.MediaPlayer;
import android.util.Log;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.Assets.Asset;
import com.example.codename_ludos.Core.MainActivity;

public class Music extends Asset {
    private int resourceID;
    private MediaPlayer mp;
    private boolean completed = false;

    public Music(int resourceID, ArcadeGame myGame) {
        super("");
        this.resourceID = resourceID;
        mp = MediaPlayer.create(MainActivity.gamePanel.getContext(), resourceID);
        MainActivity.songList.add(this);
        myGame.createSong(this);
    }

    public Music(int resourceID) {
        super("");
        this.resourceID = resourceID;
        mp = MediaPlayer.create(MainActivity.gamePanel.getContext(), resourceID);
        MainActivity.songList.add(this);
    }

    public void play() {
        if (mp == null) {
            mp = MediaPlayer.create(MainActivity.gamePanel.getContext(), resourceID);
            mp.setOnCompletionListener((m) -> {
                stopPlayer();
                completed = true;
            });
        }
        completed = false;
        mp.start();
    }

    public boolean isCompleted() {
        return completed;
    }

    public void stop() {
        stopPlayer();
    }

    public void pause() {
        if (mp != null) {
            mp.pause();
        }
    }

    private void stopPlayer() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
