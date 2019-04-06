package com.vetche.codename_ludos.Games.Surge.Objects.Obstacles;

import android.graphics.Color;

import com.vetche.codename_ludos.Assets.Graphics.Shapes;

import com.vetche.codename_ludos.Games.Surge.Objects.SurgeEntity;
import com.vetche.codename_ludos.Games.Surge.Player;

import com.vetche.codename_ludos.LibraryTools.Math.Vector2D;

public class Slope extends SurgeEntity {
    Vector2D a;
    Vector2D b;
    Vector2D TR;
    Vector2D BR;

    Vector2D[] vertices;

    public Slope(String drawName, float x, float y, int width, int height) {
        super(drawName, x, y, width, height);
    }

    public Slope(String drawName, float x, float y) {
        super(drawName, x, y, 100, 200);
        width = 100;
        height = 200;
        a = new Vector2D(x, y + height);
        b = new Vector2D(x + width, y + height);
        TR = new Vector2D(x + width, y);
        BR = new Vector2D(x + width, y + height);

        Vector2D[] v = { a, b, b, TR, TR, BR };
        vertices = v;
    }

    public void collide(Player player) {
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

        if (Vector2D.intersect(a, b, pTR, pBR)) {
            Vector2D pos = Vector2D.getIntersectPos(a, b, pTR, pBR);
            player.mPos.x = pos.x - player.width;
            player.mPos.y = pos.y - player.height;
        }

        if (Vector2D.intersect(a, b, pBL, pBR)) {
            Vector2D pos = Vector2D.getIntersectPos(a, b, pBL, pBR);
            player.mPos.x = pos.x - player.width;
            player.mPos.y = pos.y - player.height;
        }
    }

    @Override
    public void draw() {
        Shapes.setColor(Color.YELLOW);
        Shapes.drawRect(a.x, a.y, 10, 10);
        Shapes.drawRect(b.x, b.y, 10, 10);
        Shapes.drawRect(TR.x, TR.y, 10, 10);
        Shapes.drawRect(BR.x, BR.y, 10, 10);
        /*
        Canvas ctx = MainThread.canvas;
        ctx.drawLine(a.x, a.y, b.x, b.y, GamePanel.paint);
        ctx.drawLine(b.x, b.y, TR.x, TR.y, GamePanel.paint);
        ctx.drawLine(TR.x, TR.y, BR.x, BR.y, GamePanel.paint);
        */
    }
}
