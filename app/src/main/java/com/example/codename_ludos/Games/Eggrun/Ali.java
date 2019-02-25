package com.example.codename_ludos.Games.Eggrun;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.Entity.BasePlayer;
import com.example.codename_ludos.R;

public class Ali extends BasePlayer {
    private SpriteMap sprite;
    private SpriteMap.Animation run;
    private float gravity = 25f;
    private int width = 80;
    private int height = 80;
    private boolean jumping = false;

    public Ali() {
        super(500 ,10);
        sprite = new SpriteMap(R.drawable.alirun); // Dimensions of the raw image
        sprite.bindSprite("Ali",0,0,40,40);
        run = new SpriteMap.Animation(0,1,2,0.13f);
    }

    private void jump(){
        if (ArcadeMachine.getCurrentGame().getControls().isTouched("Jump")
                && !jumping) {
            mVel.setY(-10);
            jumping = true;
        }
    }

    public void update() {
        jump();
        mVel.addY(gravity * MainThread.getAverageDeltaTime());
        mPos.addVec(mVel);
        if (mPos.getY() + height >= 600){
            mVel.setY(0);
            mPos.setY(600 - height);
            jumping = false;
        }
    }

    public void draw() {
        sprite.Animate("Ali", run);
        sprite.drawAt("Ali", (int)mPos.x, (int)mPos.y, width, height);
    }
}