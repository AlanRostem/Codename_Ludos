package com.example.codename_ludos.Games.Surge.Objects.Obstacles;

import com.example.codename_ludos.Games.Surge.Objects.SurgeEntity;
import com.example.codename_ludos.Games.Surge.Player;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class Slope extends SurgeEntity {
    Vector2D a;
    Vector2D b;

    public Slope(String drawName, float x, float y, int width, int height) {
        super(drawName, x, y, width, height);
    }

    public Slope(String drawName, float x, float y) {
        super(drawName, x, y, 100, 200);
        a = new Vector2D(x, y + height);
        b = new Vector2D(x + width, y + height);
    }

    @Override
    public void playerXCollision(Player player) {
        Vector2D pTL = new Vector2D(player.mPos.x, player.mPos.y);
        Vector2D pTR = new Vector2D(player.mPos.x + player.width, player.mPos.y);
        Vector2D pBL = new Vector2D(player.mPos.x, player.mPos.y + player.height);
        Vector2D pBR = new Vector2D(player.mPos.x + player.width, player.mPos.y + player.height);
        if (!Vector2D.intersect(a, b, pTR, pBR) && !Vector2D.intersect(a, b, pBL, pBR)) {
            if (player.mVel.x < 0) {
                if (player.mPos.x < mPos.x + width) {
                    player.mVel.x = 0;
                    player.mPos.x = mPos.x + width;
                    player.side.left = true;
                }
            }
        }

    }
}
