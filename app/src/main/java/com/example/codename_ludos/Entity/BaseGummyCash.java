package com.example.codename_ludos.Entity;

import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.R;

public class BaseGummyCash extends GameEntity {
    protected static SpriteMap sprite = new SpriteMap(R.drawable.dropani);
    protected static SpriteMap.Animation anim = new SpriteMap.Animation(0, 3, 4, 0.2f);

    public BaseGummyCash(float x, float y) {
        super(x, y);
        width = 60;
        height = 60;
        sprite.bindSprite("start", 0, 0, 20, 20);
    }

    @Override
    public void draw() {
        sprite.Animate("start", anim);
        sprite.drawAt("start", (int)this.mPos.x, (int)this.mPos.y, this.width, this.height);
    }
}
