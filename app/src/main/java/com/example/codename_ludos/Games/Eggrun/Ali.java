package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Shapes;
import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.R;

public class Ali extends EggrunEntity {
    private SpriteMap sprite;
    private SpriteMap.Animation run;
    private SpriteMap.Animation jump;
    private SpriteMap.Animation djump;
    private SpriteMap.Animation djumpf;
    private SpriteMap.Animation slide;

    private boolean jumping = false;
    private boolean djumping = false;
    private boolean djumpingf = false;
    private boolean sliding = false;

    public Ali() {
        super(300 ,10, 80 ,80);

        sprite = new SpriteMap(R.drawable.alisheet); // Dimensions of the raw image
        sprite.bindSprite("Ali",0,0,40,40);

        run = new SpriteMap.Animation(0,1,4,.13f);
        jump = new SpriteMap.Animation(1,1,4,0f);
        djump = new SpriteMap.Animation(4,7,4,0.26f);
        djumpf = new SpriteMap.Animation(7,7,4,0f);
        slide = new SpriteMap.Animation(2,3,4,.13f);
    }

    private void controls(){
        if (ArcadeMachine.getCurrentGame().getControls().isTouched("Jump")) {
            if (!jumping && !djumping) {
                mVel.setY(-15f);
                jumping = true;
                djumping = false;
            }
            else if(!djumping){
                    mVel.setY(-15f);
                    djumping = true;
                    jumping = false;
            }
        }
        if (ArcadeMachine.getCurrentGame().getControls().isTouched("Slide") && !sliding) {
            if (jumping || djumping)
            {
                djumpingf=true;
                mAcc.setY(15f);
            }
            else sliding = true;
        } else if(!ArcadeMachine.getCurrentGame().getControls().isTouched("Slide"))
            sliding = false;
    }

    public void update() {
        mVel.addY(gravity * MainThread.getAverageDeltaTime());
        mVel.addVec(mAcc);
        if(mVel.y>0) djumpingf = true;
        side.reset();

        mVel.x = 1f;

        manageTileCollisionY(gameMap.level,1);
        manageTileCollisionX(gameMap.level,1);

        if (side.right)
            mVel.x = -4;
        else
            mVel.x = 1f;

        if (side.bottom || mPos.y > 29*gameMap.tileSize){
            mVel.setY(0);
            mAcc.setY(0);
            jumping = false;
            djumping = false;
            djumpingf = false;
        }

        controls();
        //mPos.addVec(mVel);
        mPos.y += mVel.y;
    }

    public Vector2D getPosition(){
        return mPos;
    }

    public void draw() {
        if (djumpingf && !jumping) sprite.Animate("Ali", djumpf);
        else if (jumping) sprite.Animate("Ali", jump);
        else if (djumping) sprite.Animate("Ali", djump);
        else if (sliding) sprite.Animate("Ali", slide);
        else sprite.Animate("Ali", run);
        manageTileCollisionY(gameMap.level,1);
        manageTileCollisionX(gameMap.level,1);
        sprite.drawAt("Ali", (int)mPos.x, (int)mPos.y, width, height);
    }
}