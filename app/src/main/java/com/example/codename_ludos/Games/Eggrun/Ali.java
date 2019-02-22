package com.example.codename_ludos.Games.Eggrun;

import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Entity.BasePlayer;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.R;

public class Ali extends BasePlayer {
    private SpriteMap sprite;
    private SpriteMap.Animation run;

    public Ali() {
        super(320 ,100);

        sprite = new SpriteMap(R.drawable.alirun2); // Dimensions of the raw image
        sprite.bindSprite("Ali",0,0,40,40);
        run = new SpriteMap.Animation(0,2,2,0.13f);
    }

    private void jump(){

    }

    public void update() {

    }

    public void draw() {
        sprite.drawAt("Ali", (int)mPos.x, (int)mPos.x, 100, 50);
    }
}
