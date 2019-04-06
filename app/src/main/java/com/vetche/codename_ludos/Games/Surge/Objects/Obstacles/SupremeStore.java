package com.vetche.codename_ludos.Games.Surge.Objects.Obstacles;

import com.vetche.codename_ludos.Games.Surge.Surge;

public class SupremeStore extends OneWayPlatform {
    private float speed = (float)Math.random() * 200f - 100f;

    public SupremeStore(float x, float y) {
        super("supreme", x, y, (int)(180*1.5f), 16+8);
    }

    static {
        objects.bindSprite("supreme", 0, 81, 181, 140);
    }

    @Override
    public void update() {
        super.update();
        moveX(speed);
    }

    @Override
    public void draw() {
        objects.drawAt(drawName,
                (int) mPos.x + (int) Surge.camera.x,
                (int) mPos.y + (int) Surge.camera.y, width, (int)(140*1.5f));
    }
}
