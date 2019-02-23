package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;
import android.view.MotionEvent;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.Controllers.Button;
import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainThread;

public class Eggrun extends ArcadeGame {
    private Ali ali;

    public Eggrun(){ super("Eggrun"); }

    @Override
    public void setup() {

        ali = new Ali();

        controls.createController("meh1", new Button( 10 + 10*80, 1920 - 350, 170, 170) {
            private int color = Color.GREEN;

            public void onPressed(float x, float y) {
                color = Color.BLUE;
            }

            public void onReleased(float x, float y) {
                color = Color.GREEN;
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
        controls.createController("meh2", new Button( 10 + 3*80, 1920 - 350, 120, 120) {
            private int color = Color.GREEN;

            public void onHolding(float x, float y) {
                color = Color.BLUE;
            }

            public void onReleased(float x, float y) {
                color = Color.GREEN;
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
        controls.createController("meh3", new Button(80, 1920 - 350, 120, 120) {
            private int color = Color.GREEN;

            public void onHolding(float x, float y) {
                color = Color.BLUE;
            }

            public void onReleased(float x, float y) {
                color = Color.GREEN;
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
        ali.update();
    }

    @Override
    public void draw() {
        ali.draw();
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
