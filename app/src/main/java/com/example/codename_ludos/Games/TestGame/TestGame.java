package com.example.codename_ludos.Games.TestGame;

import android.graphics.Color;
import android.view.MotionEvent;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.Assets.Shapes;
import com.example.codename_ludos.Controllers.Button;
import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainThread;

public class TestGame extends ArcadeGame {
    private Dude dude;

    public TestGame() {
        super("TestGame");
    }

    @Override
    public void setup() {

        dude = new Dude();

        controls.createController("jump", new Button( 10, 170, 10, 10) {
            private int color = Color.GREEN;

            public void onPressed(float x, float y) {
                color = Color.BLUE;
                //dude.jumpNow = true;
            }

            public void onReleased(float x, float y) {
                color = Color.GREEN;
                //dude.jumpNow = false;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
            }
        });
        controls.createController("right", new Button( 30 , 170, 10, 10) {
            private int color = Color.GREEN;

            public void onHolding(float x, float y) {
                color = Color.BLUE;
                //dude.addX(speed);
                //dude.right = true;
                //dude.left = false;
                //dude.walk = true;
            }

            public void onReleased(float x, float y) {
                color = Color.GREEN;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
            }
        });
        controls.createController("left", new Button(80, 150, 20, 20) {
            private int color = Color.GREEN;

            public void onHolding(float x, float y) {
                color = Color.BLUE;
                //dude.addX(-speed);
                //dude.left = true;
                //dude.right = false;
                //dude.walk = true;
            }

            public void onReleased(float x, float y) {
                color = Color.GREEN;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
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
