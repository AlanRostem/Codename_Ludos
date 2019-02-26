package com.example.codename_ludos.Audio;

import android.media.MediaPlayer;

import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainActivity;

public class Music {
    private int resourceID;
    private MediaPlayer mp;

    public Music(int resourceID) {
        this.resourceID = resourceID;
        mp = MediaPlayer.create(MainActivity.gamePanel.getContext(), resourceID);
    }

    public void play() {
        mp.start();
    }
}
