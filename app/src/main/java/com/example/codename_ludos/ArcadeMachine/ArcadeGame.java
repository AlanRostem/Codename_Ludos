package com.example.codename_ludos.ArcadeMachine;

import android.view.MotionEvent;

import com.example.codename_ludos.Controls;
import com.example.codename_ludos.GameObjectManager;

public class ArcadeGame extends GameObjectManager {
    // Subclass for each arcade game that we develop

    private boolean mStarted = false;
    private String id;
    protected Controls controls;

    // Upon extending this class make sure to create a public
    // static variable for the id
    public ArcadeGame(String ID) {
        this.id = ID;
    }

    public String getID() {
        return id;
    }

    public void start() {
        mStarted = true;
        setup();
    }

    public void togglePause() {
        mStarted = !mStarted;
    }

    public boolean isStarted() {
        return mStarted;
    }

    public void setup() {

    }

    public void draw() {

    }

    public void update() {

    }

    public void onTouchEvent(MotionEvent event) {

    }
}
