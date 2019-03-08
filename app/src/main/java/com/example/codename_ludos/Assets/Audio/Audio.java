package com.example.codename_ludos.Assets.Audio;

import android.content.Context;
import android.media.SoundPool;

import com.example.codename_ludos.Core.MainActivity;

public class Audio {
    private int resourceID;
    private int soundID;

    private float pitch = 1;
    private float volume = 1;

    private static Context context = MainActivity.gamePanel.getContext();
    private static SoundPool soundPool = MainActivity.soundPool;

    public Audio(int resourceID) {
        this.resourceID = resourceID;
        soundID = soundPool.load(context, this.resourceID, 1);
    }

    public void setPitch(float val) {
        pitch = val;
    }

    public void setVolume(float val) {
        volume = val;
    }

    public void play() {
        if (MainActivity.soundLoaded) {
            soundPool.play(this.soundID, volume,volume, 0, 0, pitch);
        }
    }
}
