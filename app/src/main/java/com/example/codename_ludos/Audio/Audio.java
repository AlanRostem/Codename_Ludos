package com.example.codename_ludos.Audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.codename_ludos.Core.MainActivity;

public class Audio {
    private int resourceID;
    private int soundID;

    private static Context context = MainActivity.gamePanel.getContext();
    private static SoundPool soundPool = MainActivity.soundPool;

    public Audio(int resourceID) {
        this.resourceID = resourceID;
        soundID = soundPool.load(context, this.resourceID, 1);
    }

    public void play() {
        if (MainActivity.soundLoaded) {
            soundPool.play(this.soundID, 1,1, 0, 0, 1);
        }
    }
}
