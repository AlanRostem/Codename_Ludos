package com.example.codename_ludos.Entity;

import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class GameEntity {
    // Subclass for objects that will be pushed into EntityManager

    private boolean toRemove = false;

    protected Vector2D mVel;
    protected Vector2D mPos;
    protected Vector2D mAcc;

    public GameEntity(float x, float y) {
        mPos = new Vector2D(x, y);
        mVel = new Vector2D(0, 0);
        mAcc = new Vector2D(0, 0);
    }

    public void remove() {
        toRemove = true;
    }

    public boolean isRemoved() {
        return toRemove;
    }

    public void move() {
        mPos.x += mVel.x * MainThread.getAverageDeltaTime();
        mPos.y += mVel.y * MainThread.getAverageDeltaTime();
    }

    public void move(float x, float y) {
        mPos.x += x * MainThread.getAverageDeltaTime();
        mPos.y += y * MainThread.getAverageDeltaTime();
    }

    public void moveX(float x) {
        mPos.x += x * MainThread.getAverageDeltaTime();
    }

    public void moveY(float y) {
        mPos.y += y * MainThread.getAverageDeltaTime();
    }

    public void accelerate() {
        mVel.x += mAcc.x;
        mVel.y += mAcc.y;
    }

    public void accelerate(float x, float y) {
        mVel.x += x;
        mVel.y += y;
    }

    public void accelerateX(float x) {
        mVel.x += x;
    }

    public void accelerateY(float y) {
        mVel.y += y;
    }

    public void draw() {

    }
    public void update() {

    }
}