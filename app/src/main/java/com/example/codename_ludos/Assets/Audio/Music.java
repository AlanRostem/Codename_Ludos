package com.example.codename_ludos.Assets.Audio;

import android.media.MediaPlayer;
import android.util.Log;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.Core.MainActivity;

public class Music {
    private int resourceID;
    private MediaPlayer mp;

    public Music(int resourceID, ArcadeGame myGame) {
        this.resourceID = resourceID;
        mp = MediaPlayer.create(MainActivity.gamePanel.getContext(), resourceID);
        MainActivity.songList.add(this);
        myGame.createSong(this);
    }

    public void play() {
        mp.start();
    }

    public void stop() {
        mp.stop();
        mp.release();
    }

    public void pause() {
        mp.pause();
    }
}
