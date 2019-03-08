package com.example.codename_ludos.UserInterface;

import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class Finger extends Vector2D {

    private int mID;
    private boolean down;
    private int action;
    public Finger(boolean isDown, int id, float x, float y, int action) {
        super(x, y);
        this.mID = id;
        this.down = isDown;
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean on) {
        down = on;
    }

    public Vector2D set(boolean isDown, int id, float x, float y, int action) {
        set(x, y);
        this.down = isDown;
        this.mID = id;
        this.action = action;
        return this;
    }

    public int getID() {
        return mID;
    }
}
