package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;
import android.view.MotionEvent;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.Assets.Shapes;
import com.example.codename_ludos.Controllers.Button;

public class Eggrun extends ArcadeGame {
    private Ali ali;
    private Map map;

    public Eggrun(){ super("Eggrun"); }

    @Override
    public void setup() {
        ali = new Ali();
        map = new Map();
        spawnEntity(ali);

        controls.createController("Jump", new Button(800, 1500, 100, 100){
            private int color = Color.GRAY;

            public void onPressed(float x, float y) {
                color = Color.rgb(190,190,190);
            }

            public void onReleased(float x, float y) {
                color = Color.GRAY;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(x, y, getWidth(), getHeight());
            }
        });
    }

    @Override
    public void update() {
        controls.update();
        map.update();
        ali.update();
    }

    @Override
    public void draw() {
        map.draw();
        ali.draw();
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
