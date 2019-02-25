package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;

import com.example.codename_ludos.Assets.Shapes;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.Entity.GameEntity;

public class Egg extends GameEntity {

    private float gravity = 25f;

    public Egg(){
        super(1000,200);
        width = 120;
        height = 120;
    }

    private void outOfScreen(){
        if (mPos.x > 1080){
            remove();
        }
    }

    public void update() {
        mVel.setX(-200f* MainThread.getAverageDeltaTime());
        mPos.addVec(mVel);
        outOfScreen();
        mVel.addY(gravity * MainThread.getAverageDeltaTime());
        mVel.addVec(mAcc);
        mPos.addVec(mVel);
        if (mPos.getY() + height >= 920){
            mVel.setY(0);
            mAcc.setY(0);
            mPos.setY(920 - height);
        }
    }

    public void draw(){
        Shapes.setColor(Color.RED);
        Shapes.drawRect(mPos.x, mPos.y, width, height);
    }
}
