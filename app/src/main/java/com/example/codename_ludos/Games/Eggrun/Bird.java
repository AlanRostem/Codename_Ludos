package com.example.codename_ludos.Games.Eggrun;

import android.util.Log;

import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.R;

public class Bird extends EggrunEntity {
    private static SpriteMap sprite = new SpriteMap(R.drawable.chicken);

    public Bird(float x, float y) {
        super(x, y, 80,80);

    }

    @Override
    public void update() {
        moveX(-800f);
    }

    @Override
    public void draw() {
        sprite.drawAt("full", mPos.x, mPos.y, width, height);
    }
}
