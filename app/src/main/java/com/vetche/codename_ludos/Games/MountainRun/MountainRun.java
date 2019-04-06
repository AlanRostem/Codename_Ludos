package com.vetche.codename_ludos.Games.MountainRun;

import android.view.MotionEvent;

import com.vetche.codename_ludos.ArcadeMachine.ArcadeGame;


public class MountainRun extends ArcadeGame {

    private WorldGeneration world;

    public MountainRun() {
        super("MountainRun");
    }

    @Override
    public void setup() {
        world = new WorldGeneration();
    }

    @Override
    public void update() {
        updateEntities();
        world.update();
    }

    @Override
    public void draw() {
        drawEntities();
        world.draw();
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
