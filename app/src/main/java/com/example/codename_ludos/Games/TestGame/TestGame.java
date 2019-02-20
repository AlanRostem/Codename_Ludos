package com.example.codename_ludos.Games.TestGame;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Controllers.Button;
import com.example.codename_ludos.Controllers.Controller;
import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.R;

public class TestGame extends ArcadeGame {
    private Dude dude;

    public TestGame() {
        super("TestGame");
    }

    @Override
    public void setup() {

        dude = new Dude();

        controls.createController(new Button( 10 + 10*80, 1920 - 350, 170, 170) {
            private int color = Color.GREEN;

            public void onPressed(float x, float y) {
                color = Color.BLUE;
                dude.jumpNow = true;
            }

            public void onReleased(float x, float y) {
                color = Color.GREEN;
                dude.jumpNow = false;
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

        final int speed = 5;

        controls.createController(new Button( 10 + 3*80, 1920 - 350, 120, 120) {
            private int color = Color.GREEN;

            public void onHolding(float x, float y) {
                color = Color.BLUE;
                dude.addX(speed);
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
        controls.createController(new Button(80, 1920 - 350, 120, 120) {
            private int color = Color.GREEN;

            public void onHolding(float x, float y) {
                color = Color.BLUE;
                dude.addX(-speed);
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
        dude.update();
    }

    @Override
    public void draw() {
        dude.draw();
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
