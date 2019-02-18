package com.example.codename_ludos.Games.TestGame;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.R;
import com.example.codename_ludos.RectPlayer;

public class TestGame extends ArcadeGame {
    private RectPlayer player;
    private Point playerPoint;
    private SpriteMap playerImage;
    private SpriteMap.Animation playerAnim;

    private SpriteMap explodeSprt;
    private SpriteMap.Animation explodeAnim;

    public TestGame() {
        super("TestGame");
    }

    @Override
    public void setup() {
        playerPoint = new Point(550, 150);
        player = new RectPlayer(new Rect(100, 100, 200, 200),
                Color.rgb(0, 250, 255));

        playerImage = new SpriteMap(R.drawable.rubigo, 384, 96);
        playerImage.bindSprite("a1", 0, 0, 48, 48);
        playerAnim = new SpriteMap.
                Animation(0, 7, 8, 0.1f);

        explodeAnim = new SpriteMap.Animation(0, 80, 9, 0.01f);
        explodeAnim.setReversed(true);
        explodeSprt = new SpriteMap(R.drawable.exp, 900, 900);
        explodeSprt.bindSprite("anim", 0, 0, 100, 100);
    }

    @Override
    public void update() {
        player.update(playerPoint);
    }

    @Override
    public void draw() {
        explodeSprt.Animate("anim", explodeAnim);
        explodeSprt.drawAt("anim", 320, 320, 90*3, 90*3);

        playerImage.Animate("a1", playerAnim);
        playerImage.drawAt("a1", playerPoint.x - 48*2, playerPoint.y - 48*2, 48*4, 48*4);

    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                playerPoint.set((int)event.getX() + 100, (int)event.getY() - 50);
                break;
        }
    }
}
