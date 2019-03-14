package com.example.codename_ludos.Games.Surge.Objects;

import com.example.codename_ludos.Entity.BaseGummyCash;
import com.example.codename_ludos.Games.Surge.Surge;

public class SurgeGummyCash extends BaseGummyCash {
    public SurgeGummyCash(float x, float y) {
        super(x, y);
    }

    @Override
    public void draw() {
        sprite.Animate("start", anim);
        sprite.drawAt("start", (int)(this.mPos.x + Surge.camera.x), (int)(this.mPos.y + Surge.camera.y), this.width, this.height);
    }
}
