package com.example.codename_ludos.Games.Eggrun;

import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.Entity.GameTile;
import com.example.codename_ludos.R;

public class Egg extends EggrunEntity {

    private static SpriteMap sprite = new SpriteMap(R.drawable.eggroll);
    private SpriteMap.Animation anim = new SpriteMap.Animation(0, 3, 4, 0.08f);

    private float collsionTime = 0;
    private float jump = 0f;

    public Egg(){
        super(26*gameMap.tileSize,500, 80, 80);
        sprite.bindSprite("start", 0, 0, 20, 20);
    }

    private void outOfScreen() {
        if (mPos.x < 0) {
            remove();
        }
    }

    public void update() {
        side.reset();
        mVel.setX(-850f);

        accelerateY(gravity);

        if (jump != 0){
            mVel.y = jump;
            jump = 0;
        }

        moveX(mVel.x);
        manageTileCollisionX(gameMap.level,1);

        moveY(mVel.y);
        manageTileCollisionY(gameMap.level, 1);

        outOfScreen();
    }

    @Override
    protected void onLeftCollision(GameTile tile) {
        collsionTime += MainThread.getAverageDeltaTime();
        if (collsionTime >= 0.5f){
            jump = -1500.f;
            collsionTime = 0;
        }
    }


    public void draw(){
        if (!side.left) {
            sprite.Animate("start", anim);
        }
        sprite.drawAt((int)this.mPos.x, (int)this.mPos.y, this.width, this.height);
    }
}
