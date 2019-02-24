package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;
import android.view.MotionEvent;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.Controllers.Button;
import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainThread;

public class Eggrun extends ArcadeGame {
    private Ali ali;
    private Map map;

    public Eggrun(){ super("Eggrun"); }

    @Override
    public void setup() {
        ali = new Ali();
<<<<<<< HEAD
        map = new Map();
=======
        spawnEntity(ali);
>>>>>>> 179bd9a0044d38b59b7d8756832548c86f199f95

        controls.createController("Jump", new Button(1000,1000,170,170){
            private int color = Color.GRAY;

            public void onPressed(float x, float y) {
                color = Color.rgb(190,190,190);
            }

            public void onReleased(float x, float y) {
                color = Color.GRAY;
            }

            public void draw() {
                GamePanel.paint.setColor(this.color);
                MainThread.canvas.drawRect(
                        this.getX(), this.getY(),
                        this.getX() + this.getWidth(),
                        this.getY() + this.getHeight(),
                        GamePanel.paint);
            }
        });
    }

    @Override
    public void update() {
        controls.update();
<<<<<<< HEAD
        map.update();
        ali.update();
=======
        updateEntities();
>>>>>>> 179bd9a0044d38b59b7d8756832548c86f199f95
    }

    @Override
    public void draw() {
<<<<<<< HEAD
        map.draw();
        ali.draw();
=======
        drawEntities();
>>>>>>> 179bd9a0044d38b59b7d8756832548c86f199f95
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
