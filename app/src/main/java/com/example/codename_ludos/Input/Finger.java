package com.example.codename_ludos.Input;

import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class Finger extends Vector2D {

    private int mID;
    private boolean down;
    public Finger(boolean isDown, int id, float x, float y) {
        super(x, y);
        this.mID = id;
        this.down = isDown;
    }

    public boolean isDown() {
        return down;
    }

    public Vector2D set(boolean isDown, int id, float x, float y) {
        set(x, y);
        this.down = isDown;
        this.mID = id;
        return this;
    }

    public int getID() {
        return mID;
    }
}
