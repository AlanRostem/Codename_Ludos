package com.example.codename_ludos.ArcadeMachine;

import android.view.MotionEvent;

import com.example.codename_ludos.Controllers.Controls;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.Entity.EntityManager;

public class ArcadeGame extends EntityManager {
    // Subclass for each arcade game that we develop

    private boolean mStarted = false;
    private String id;
    private String displayName;
    protected Controls controls;

    // Upon extending this class make sure to create a public
    // static variable for the id
    public ArcadeGame(String ID) {
        this.id = ID;
        controls = new Controls();
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

    /**
     * Upon starting a game, this function is called. Use this to set up the game.
     * This includes member initializations and calling other methods for things
     * like animations or intros the game may or may not have.
     */

    public void coreUpdate() {
        updateEntities();
        update();
    }

    public void coreDraw() {
        drawEntities(MainThread.canvas);
        draw();
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
