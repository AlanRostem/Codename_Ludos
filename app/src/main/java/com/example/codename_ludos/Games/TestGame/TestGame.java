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
    private SpriteMap playerImage;
    private SpriteMap.Animation playerAnim;

    private SpriteMap explodeSprt;
    private SpriteMap.Animation explodeAnim;

    public TestGame() {
        super("TestGame");
    }

    @Override
    public void setup() {

        playerImage = new SpriteMap(R.drawable.rubigo, 384, 96);
        playerImage.bindSprite("a1", 0, 0, 48, 48);
        playerAnim = new SpriteMap.
                Animation(0, 7, 8, 0.1f);

        explodeAnim = new SpriteMap.Animation(0, 80, 9, 0.01f);
        explodeAnim.setReversed(true);
        explodeSprt = new SpriteMap(R.drawable.exp, 900, 900);
        explodeSprt.bindSprite("anim", 0, 0, 100, 100);

        controls.createController(new Controller(320, 320, 90*3 ,90*3) {
            public void onTouch(float eX, float eY) {
                explodeAnim.setReversed(false);
            }
        });

        controls.createController(new Button( 10 + 3*80, 1920 - 350, 120, 120) {
            private int color = Color.GREEN;

            public void onHolding(float x, float y) {
                color = Color.BLUE;
                Log.i("Works?", "I mean I guess...");
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
                Log.i("Works?", "I mean I guess...");
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
    }

    @Override
    public void draw() {
        explodeSprt.Animate("anim", explodeAnim);
        explodeSprt.drawAt("anim", 320, 320, 90*3, 90*3);

        //playerImage.Animate("a1", playerAnim);
        //playerImage.drawAt("a1", playerPoint.x - 48*2, playerPoint.y - 48*2, 48*4, 48*4);
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
