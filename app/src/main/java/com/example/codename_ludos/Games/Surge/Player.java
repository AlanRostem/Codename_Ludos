package com.example.codename_ludos.Games.Surge;

import android.graphics.Color;
import android.util.Log;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Shapes;
import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Controllers.Controls;
import com.example.codename_ludos.Entity.BasePlayer;
import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.Entity.GameTile;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.Games.Surge.Objects.UnderPassObject;
import com.example.codename_ludos.R;

import java.util.ArrayList;

public class Player extends BasePlayer {
    private SpriteMap sprite;

    public Player() {
        super(320, ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT);
        sprite = new SpriteMap(R.drawable.rubigo);
        sprite.bindSprite("a1", 0, 0, 48, 48);
        width = 32*2;
        height = 64*2;
    }

    private void controlling() {
        Controls controls = ArcadeMachine.getCurrentGame().getControls();

        if (controls.isTouched("jump"))
            if (!jumping) {
                jumping = true;
                mVel.setY(-900.f);
            }

        mVel.setX(0);
        if (controls.isTouched("right"))
            mVel.setX(400.f);

        if (controls.isTouched("left"))
            mVel.setX(-400.f);
    }

    public boolean jumping = false;

    private void step() {
        accelerateY(25);

        moveX(mVel.x);
        manageCollisionX();
        moveY(mVel.y);
        manageCollisionY();

        int H = ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT;
        if (mPos.y + height > H) {
            mPos.y = H - height;
            jumping = false;
        }

        Surge.camera.update(0, mPos.y, 0, (ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT) / 3.f);
    }

    @Override
    public void update() {
        controlling();
        step();
    }

    @Override
    public void draw() {
        sprite.drawAt("a1", (int)mPos.x, (int)mPos.y + (int)Surge.camera.y, width, height);
    }

    public void manageCollisionX() {
        ArrayList<GameEntity> list = ArcadeMachine.getCurrentGame().getEntityList();
        for (GameEntity e : list) {
            if (e != this)
                if (overlap(e)) {
                    if (e instanceof UnderPassObject && mVel.y >= 0) {
                        UnderPassObject.playerXCollision(this, (UnderPassObject) e);
                    }
                }
        }
    }

    public void manageCollisionY() {
        ArrayList<GameEntity> list = ArcadeMachine.getCurrentGame().getEntityList();
        for (GameEntity e : list) {
            if (e != this)
                if (overlap(e)) {
                    if (e instanceof UnderPassObject && mVel.y >= 0) {
                        UnderPassObject.playerYCollision(this, (UnderPassObject) e);
                    }
                }
        }
    }

}