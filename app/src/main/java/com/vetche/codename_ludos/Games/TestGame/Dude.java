package com.vetche.codename_ludos.Games.TestGame;

import com.vetche.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.vetche.codename_ludos.Assets.Graphics.SpriteMap;
import com.vetche.codename_ludos.Assets.Audio.Audio;
import com.vetche.codename_ludos.Entity.BasePlayer;
import com.vetche.codename_ludos.R;

public class Dude extends BasePlayer {

    private SpriteMap sprite;
    private SpriteMap.Animation walkL;
    private SpriteMap.Animation walkR;

    int width = 48*3;
    int height = 48*3;

    public static Audio keem;

    public boolean jumping = false;

    public boolean right = true;
    public boolean left = true;
    public boolean walk = false;
    float gravity = 25f;

   public Dude() {
        super(600, 0);
        sprite = new SpriteMap(R.drawable.rubigo);
        sprite.bindSprite("a1", 0, 0, 48, 48);

        walkL = new SpriteMap.
                Animation(0, 7, 8, 0.1f);
        walkR = new SpriteMap.
                Animation(8, 15, 8, 0.1f);
        keem = ArcadeMachine
                .getGame("TestGame")
                .getAssetLoader()
                .getAsset("a_jump")
                .asAudio();
    }

    public void update() {
        mVel.x = 0;
        mVel.y += gravity;
        if (ArcadeMachine.getCurrentGame().getControls().isTouched("jump"))
            if (!jumping) {
                jumping = true;
                mVel.y = -500.f;
                keem.play();
            }

        if (ArcadeMachine.getCurrentGame().getControls().isTouched("right"))
            mVel.x = 400.f;

        if (ArcadeMachine.getCurrentGame().getControls().isTouched("left"))
            mVel.x = -400.f;

        move();
        int H = ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT;
        if (mPos.y + height > H) {
            mPos.y = H - height;
            jumping = false;
        }
    }

    public void draw() {
       if (left) {
           if (walk)
               sprite.Animate("a1", walkR);
       } else if (right) {
           if (walk)
               sprite.Animate("a1", walkL);
       }
       sprite.drawAt("a1", (int)mPos.x, (int)mPos.y, width, height);
    }
}
