package com.example.codename_ludos.Assets.Audio;

import android.content.Context;
import android.media.SoundPool;

import com.example.codename_ludos.Assets.Asset;
import com.example.codename_ludos.Core.MainActivity;

public class Audio extends Asset {
    private int resourceID;
    private int soundID;

    private float pitch = 1;
    private float volume = 1;

    private static Context context = MainActivity.gamePanel.getContext();
    private static SoundPool soundPool = MainActivity.soundPool;
    private final SoundPool spRef;

    public Audio(SoundPool soundPool, int resourceID) {
        super("");
        this.resourceID = resourceID;
        this.soundID = soundPool.load(context, this.resourceID, 1);
        spRef = soundPool;
    }

    public void setPitch(float val) {
        pitch = val;
    }

    public void setVolume(float val) {
        volume = val;
    }

    public void play() {
        spRef.play(this.soundID, volume,volume, 0, 0, pitch);
    }

    public void reload() {
        soundID = soundPool.load(context, this.resourceID, 1);
    }

    @Override
    public void recycle() {
        // Experimental
        //soundPool.unload(this.resourceID);
    }
}
