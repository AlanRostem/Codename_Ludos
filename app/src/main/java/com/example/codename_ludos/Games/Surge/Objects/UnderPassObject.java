package com.example.codename_ludos.Games.Surge.Objects;

import android.util.Log;

import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.Games.Surge.Player;
import com.example.codename_ludos.Games.Surge.Surge;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class UnderPassObject extends SurgeEntity {
    private String drawName;
    private boolean collisionEnabled = true;
    public UnderPassObject(String drawName, float x, float y, int width, int height) {
        super(x, y);
        this.drawName = drawName;
        this.height = height;
        this.width = width;
    }

    @Override
    public void playerXCollision(Player player) {
        UnderPassObject self = this;
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
        Surge.objects.drawAt(this.drawName, (int)mPos.x, (int)mPos.y + (int)Surge.camera.y, width, height);
    }

}
