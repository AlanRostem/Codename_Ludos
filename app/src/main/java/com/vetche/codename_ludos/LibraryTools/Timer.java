package com.vetche.codename_ludos.LibraryTools;

import com.vetche.codename_ludos.Core.MainThread;

public class Timer {
    private float currentTime = 0;
    private float deltaTime;

    public Timer(float deltaTime) {
        this.deltaTime = deltaTime;
    }

    public Timer() {
        deltaTime = 1f;
    }

    public void setBPM(int bpm) {
        deltaTime = (60f / bpm);
    }

    protected void callback() {

    }

    public float tick() {
        currentTime += MainThread.getAverageDeltaTime();
        if (currentTime >= deltaTime) {
            currentTime = 0;
            callback();
        }
        return currentTime;
    }
}
