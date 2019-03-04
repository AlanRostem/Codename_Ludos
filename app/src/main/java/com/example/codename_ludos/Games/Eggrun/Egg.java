package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;
import com.example.codename_ludos.Assets.Shapes;

public class Egg extends EggrunEntity {

    private float gravity = 25f;

    public Egg(){
        super(1000,26*gameMap.tileSize, 80, 80);
    }

    private void outOfScreen(){
        if (mPos.x < 0){
            remove();
        }
    }

    public void update() {

        mVel.setX(-500f);
        mVel.addY(gravity);



        if (side.right)
            mVel.x = 0f;
        else
            mVel.x = -500f;

        side.reset();

        accelerate();
        moveX(mVel.x);
        manageTileCollisionX(gameMap.level,1);
        moveY(mVel.y);
        manageTileCollisionY(gameMap.level, 1);

        if (side.bottom){
            mVel.setY(0);
            mAcc.setY(0);
        }

        outOfScreen();
    }

    public void draw(){
        Shapes.setColor(Color.RED);
        Shapes.drawRect(mPos.x, mPos.y, width, height);
    }
}
