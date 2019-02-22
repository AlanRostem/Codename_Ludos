package com.example.codename_ludos.Entity;

import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class GameEntity {
    // Subclass for objects that will be pushed into EntityManager

    private boolean toRemove = false;

    private Vector2D mVel;
    private Vector2D mPos;
    private Vector2D mAcc;

    public GameEntity() {

    }

    public boolean isRemoved() {
        return toRemove;
    }

    public void draw() {

    }
    public void update() {

    }
}