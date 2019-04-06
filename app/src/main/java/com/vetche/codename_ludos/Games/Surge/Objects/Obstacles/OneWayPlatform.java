package com.vetche.codename_ludos.Games.Surge.Objects.Obstacles;

import android.graphics.Color;

import com.vetche.codename_ludos.Assets.Graphics.Shapes;
import com.vetche.codename_ludos.Games.Surge.Player;
import com.vetche.codename_ludos.Games.Surge.Surge;
import com.vetche.codename_ludos.LibraryTools.Math.Vector2D;

public class OneWayPlatform extends Obstacle {
    protected boolean collisionEnabled = true;

    public OneWayPlatform(String drawName, float x, float y, int width, int height) {
        super(drawName, x, y, width, height);
    }

    public OneWayPlatform(float x, float y, int width, int height) {
        super("none", x, y, width, height);
    }

    static {
        objects.bindSprite("oneWay", 80, 0, 96, 16);
    }

    public OneWayPlatform(float x, float y) {
        super("oneWay", x, y, 96 + 96/2, 16 + 8);
    }

    @Override
    public void playerXCollision(Player player) {
        OneWayPlatform self = this;
        if (player.mPos.y + player.height < self.mPos.y + self.height && player.mVel.y > 0)
            if (player.mVel.x > 0) {
                if (player.mPos.x + player.width > self.mPos.x) {
                    collisionEnabled = false;
                }
            } else if (player.mVel.x < 0) {
                if (player.mPos.x < self.mPos.x + self.width) {
                    collisionEnabled = false;
                }
            }
    }

    @Override
    public void playerYCollision(Player player) {
        Vector2D selfA = new Vector2D(this.mPos.x, this.mPos.y);
        Vector2D selfB = new Vector2D(this.mPos.x + this.width, this.mPos.y);

        Vector2D playerA1 = new Vector2D(player.mPos.x, player.mPos.y);
        Vector2D playerB1 = new Vector2D(player.mPos.x, player.mPos.y + player.height);

        Vector2D playerA2 = new Vector2D(player.mPos.x, player.mPos.y);
        Vector2D playerB2 = new Vector2D(player.mPos.x + player.width, player.mPos.y + player.height);

        if (player.mVel.y > 0 && collisionEnabled) {
            if (player.mPos.y + player.height - this.height < this.mPos.y &&
                    (Vector2D.intersect(selfA, selfB, playerA1, playerB1) || (Vector2D.intersect(selfA, selfB, playerA2, playerB2)))){
                player.mPos.y = this.mPos.y - player.height;
                player.mVel.y = 0;
                player.onGround();
            }
        } else collisionEnabled = true;
    }

    @Override
    public void draw() {
        try {
            objects.drawAt(this.drawName, (int)mPos.x, (int)mPos.y + (int)Surge.camera.y, width, height);
        } catch (Exception e) {
            Shapes.setColor(Color.YELLOW);
            Shapes.drawRect((int)mPos.x + Surge.camera.x, (int)mPos.y + (int)Surge.camera.y, width, height);
        }
    }

}

