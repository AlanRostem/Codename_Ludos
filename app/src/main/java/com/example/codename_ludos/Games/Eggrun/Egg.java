package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;
import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.R;

public class Egg extends EggrunEntity {

    private static SpriteMap sprite = new SpriteMap(R.drawable.eggroll);
    private SpriteMap.Animation anim = new SpriteMap.Animation(0, 3, 4, 0.08f);

    private float gravity = 25f * 400f;

    public Egg(){
        super(26*gameMap.tileSize,0, 80, 80);
        sprite.bindSprite("start", 0, 0, 20, 20);
    }

    private void outOfScreen() {
        if (mPos.x < 0) {
            remove();
        }
    }

    public void update() {
        //side.reset();
        mVel.setX(-850f);

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
        if (!side.left) {
            sprite.Animate("start", anim);
        }
        sprite.drawAt("start", (int)this.mPos.x, (int)this.mPos.y, this.width, this.height);
    }
}
