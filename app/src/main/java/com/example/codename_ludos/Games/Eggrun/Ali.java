package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.SpriteMap;
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

    private int jumps = 0;
    private int maxJumps = 1;

    private void controls() {
        if (ArcadeMachine.getCurrentGame().getControls().isTouched("Jump")) {
            if (!jumping) {
                mVel.y = -1000.f;
                jumping = true;
            }
        } else {
            if (jumps < maxJumps) {
                if (jumping) {
                    jumps++;
                    djumping = true;
                    jumping = false;
                }
            } else {
                if (mVel.y == 0.0f) {
                    jumps = 0;

                }
            }
        }

        if (ArcadeMachine.getCurrentGame().getControls().isTouched("Slide") && !sliding) {
            if (jumping || djumping)
            {
                djumpingf = true;
                mAcc.setY(15f);
            }
            else sliding = true;
        } else if (!ArcadeMachine.getCurrentGame().getControls().isTouched("Slide"))
            sliding = false;
    }

    public void update() {
        mVel.x=0;
        mVel.addY(gravity);

        controls();

        if(mVel.y > 0) djumpingf = true;



        if (!side.right && mPos.x < 7 *gameMap.tileSize)
            mVel.x = 100f;
        else
            mVel.x = 0;

        side.reset();

        mVel.x = 1;
        accelerate();
        moveX(mVel.x);
        manageTileCollisionX(gameMap.level,1);
        moveY(mVel.y);
        manageTileCollisionY(gameMap.level, 1);


        if (side.bottom){
            //mPos.y = 29 * gameMap.tileSize;
            mVel.setY(0);
            mAcc.setY(0);
            jumping = false;
            djumping = false;
            djumpingf = false;
        }

    }

    public Vector2D getPosition(){
        return mPos;
    }

    public void draw() {
        //if (side.bottom)
        sprite.Animate("Ali", run);
       // else if (djumpingf && !jumping) sprite.Animate("Ali", djumpf);
       // else if (jumping) sprite.Animate("Ali", jump);
        //else if (djumping) sprite.Animate("Ali", djump);
        //else if (sliding) sprite.Animate("Ali", slide);
        sprite.drawAt("Ali", (int)mPos.x, (int)mPos.y, width, height);
    }
}