package com.example.codename_ludos.Games.Surge.Objects.Obstacles;

import com.example.codename_ludos.Games.Surge.Player;
import com.example.codename_ludos.Games.Surge.Surge;

public class SupremeStore extends Obstacle {
    private float speed = (float)Math.random() * 200f - 100f;

    public SupremeStore(float x, float y) {
        super("supreme", x, y, 180*2, 100*2);
    }

    @Override
    public void playerXCollision(Player player) {
        if (player.mVel.x > 0.f)
            if (player.mPos.x + player.width > mPos.x) {
                player.mVel.x = mVel.x;
                player.mPos.x = mPos.x - player.width;
                player.side.right = true;
            }

        if (player.mVel.x < 0.f)
            if (player.mPos.x < mPos.x + width) {
                player.mVel.x = mVel.x;
                player.mPos.x = mPos.x + width;
                player.side.left = true;
            }
    }

    @Override
    public void playerYCollision(Player player) {
        if (player.mVel.y > 0.f)
            if (player.mPos.y + player.height > mPos.y) {
                player.mVel.y = 0;
                player.mPos.x += mVel.x;
                player.mPos.y = mPos.y - player.height;
                player.onGround();
                player.side.bottom = true;
            }

        if (player.mVel.y < 0.f)
            if (player.mPos.y < mPos.y + height) {
                player.mVel.y = 0;
                player.mPos.y = mPos.y + height;
                player.side.top = true;
            }
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
                (int) mPos.y - (2*140 - height) + (int) Surge.camera.y, width, 2*140);
    }
}
