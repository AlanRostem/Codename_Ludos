package com.example.codename_ludos.Games.Surge.Objects.Obstacles;

import com.example.codename_ludos.Games.Surge.Player;
import com.example.codename_ludos.Games.Surge.Surge;

public class SolidObject extends Obstacle {
    public SolidObject(String drawName, float x, float y, int width, int height) {
        super(drawName, x, y, width, height);
    }

    public SolidObject(float x, float y) {
        super("solidWall", x, y, 30, 40+80);
    }

    @Override
    public void draw() {
        objects.drawAt(this.drawName, (int)mPos.x, (int)mPos.y + (int)Surge.camera.y, width, height);
    }

    @Override
    public void playerXCollision(Player player) {
        if (player.mVel.x > 0.f)
            if (player.mPos.x + player.width > mPos.x) {
                player.mVel.x = 0;
                player.mPos.x = mPos.x - player.width;
                player.side.right = true;
            }

        if (player.mVel.x < 0.f)
            if (player.mPos.x < mPos.x + width) {
                player.mVel.x = 0;
                player.mPos.x = mPos.x + width;
                player.side.left = true;
            }
    }

    @Override
    public void playerYCollision(Player player) {
            if (player.mVel.y > 0.f)
                if (player.mPos.y + player.height > mPos.y) {
                    player.mVel.y = 0;
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
}
