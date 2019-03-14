package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;
import com.example.codename_ludos.Assets.Graphics.Shapes;

public class Egg extends EggrunEntity {

    private float gravity = 25f * 800f;

    public Egg(){
        super(20*gameMap.tileSize,2*gameMap.tileSize, 80, 80);
        mVel.setX(-500f);
    }

    private void outOfScreen(){
        if (mPos.x < 0) {
            remove();
        }
    }

    public void update() {
        side.reset();
        mVel.setX(-500f);

        accelerateY(gravity);

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
