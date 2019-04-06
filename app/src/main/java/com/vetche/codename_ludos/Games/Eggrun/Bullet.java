package com.vetche.codename_ludos.Games.Eggrun;

import android.graphics.Color;

import com.vetche.codename_ludos.Assets.Graphics.Shapes;
import com.vetche.codename_ludos.Core.MainThread;
import com.vetche.codename_ludos.LibraryTools.Math.Vector2D;

public class Bullet extends EggrunEntity {

    public Bullet(Vector2D pos){
        super(pos.x, pos.y,20,20);
    }

    private void outOfScreen(){
        if (mPos.x > 1080){
            remove();
        }
    }

    public void update(){
        mVel.setX(300f * MainThread.getAverageDeltaTime());
        mPos.addVec(mVel);
        outOfScreen();
    }

    public void draw(){
        Shapes.setColor(Color.BLACK);
        Shapes.drawRect(mPos.x, mPos.y, width, height);
    }
}
