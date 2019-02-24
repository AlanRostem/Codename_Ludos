package com.example.codename_ludos.Games.Eggrun;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Entity.BasePlayer;
import com.example.codename_ludos.R;

public class Ali extends BasePlayer {
    private SpriteMap sprite;
    private SpriteMap.Animation run;
    float gravity = 0.3f;

    public Ali() {
        super(320 ,100);
        sprite = new SpriteMap(R.drawable.alirun); // Dimensions of the raw image
        sprite.bindSprite("Ali",0,0,40,40);
        run = new SpriteMap.Animation(0,1,2,0.13f);
    }

    private void jump(){
        if (ArcadeMachine.getCurrentGame().getControls().isTouched("Jump")) mVel.setY(-5);
    }

    public void update() {
        jump();
        mVel.addY(gravity);
        mPos.addVec(mVel);



        if (mPos.y >= 400){
            mVel.setY(0);
            mPos.setY(400);
        }
    }


    public void draw() {
        sprite.Animate("Ali", run);
        sprite.drawAt("Ali", (int)mPos.x, (int)mPos.y, 100, 50);
    }
}
