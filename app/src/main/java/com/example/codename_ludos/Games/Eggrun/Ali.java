package com.example.codename_ludos.Games.Eggrun;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.R;

public class Ali extends EggrunEntity {
    private SpriteMap sprite;
    private SpriteMap.Animation run;
    private SpriteMap.Animation jump;
    private SpriteMap.Animation slide;

    private boolean jumping = false;
    private boolean sliding = false;

    public Ali() {
        super(300 ,10, 80 ,80);

        sprite = new SpriteMap(R.drawable.alisheet); // Dimensions of the raw image
        sprite.bindSprite("Ali",0,0,40,40);

        run = new SpriteMap.Animation(0,1,4,.13f);
        jump = new SpriteMap.Animation(1,1,4,0f);
        slide = new SpriteMap.Animation(2,3,4,.13f);
    }

    private void controls(){
        if (ArcadeMachine.getCurrentGame().getControls().isTouched("Jump")
                && !jumping) { mVel.setY(-15f); jumping = true; }
        if (ArcadeMachine.getCurrentGame().getControls().isTouched("Slide")
                && !sliding) {
            if (jumping) mAcc.setY(15f);
            else sliding = true;
        } else if(!ArcadeMachine.getCurrentGame().getControls().isTouched("Slide"))
            sliding = false;
    }

    public void update() {
        mVel.addY(gravity * MainThread.getAverageDeltaTime());
        mVel.addVec(mAcc);
        if (false){
            mVel.setY(0);
            mAcc.setY(0);
            jumping = false;
        }
        controls();
        mPos.addVec(mVel);
    }

    public Vector2D getPosition(){
        return mPos;
    }

    public void draw() {
        if (jumping) sprite.Animate("Ali", jump);
        else if (sliding) sprite.Animate("Ali", slide);
        else sprite.Animate("Ali", run);
        sprite.drawAt("Ali", (int)mPos.x, (int)mPos.y, width, height);
    }
}