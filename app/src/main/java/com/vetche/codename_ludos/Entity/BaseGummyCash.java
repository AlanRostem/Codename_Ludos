package com.vetche.codename_ludos.Entity;

import com.vetche.codename_ludos.Assets.Graphics.SpriteMap;
import com.vetche.codename_ludos.R;

public class BaseGummyCash extends GameEntity {
    protected static SpriteMap sprite = new SpriteMap(R.drawable.dropani);
    protected static SpriteMap.Animation anim = new SpriteMap.Animation(0, 3, 4, 0.2f);

    public BaseGummyCash(float x, float y) {
        super(x, y);
        width = 30;
        height = 30;
        sprite.bindSprite("start", 0, 0, 20, 20);
    }

    @Override
    public void draw() {
        sprite.Animate("start", anim);
        sprite.drawAt("start", (int)this.mPos.x, (int)this.mPos.y, this.width, this.height);
    }
}
