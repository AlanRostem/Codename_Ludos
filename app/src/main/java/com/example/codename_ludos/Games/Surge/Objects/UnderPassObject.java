package com.example.codename_ludos.Games.Surge.Objects;

import android.util.Log;

import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.Games.Surge.Player;
import com.example.codename_ludos.Games.Surge.Surge;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class UnderPassObject extends SurgeEntity {
    private String drawName;
    public UnderPassObject(String drawName, float x, float y, int width, int height) {
        super(x, y);
        this.drawName = drawName;
        this.height = height;
        this.width = width;
    }

    @Override
    public void playerXCollision(Player player) {
        UnderPassObject self = this;
        if (player.mPos.y + player.height < self.mPos.y + self.height && player.mVel.y >= 0)
            if (player.mVel.x > 0) {
                if (player.mPos.x + player.width > self.mPos.x) {
                    player.mPos.x = self.mPos.x - player.width;
                    player.mVel.x = 0;
                }
            } else if (player.mVel.x < 0) {
                if (player.mPos.x < self.mPos.x + self.width) {
                    player.mPos.x = self.mPos.x + self.width;
                    player.mVel.x = 0;
                }
            }
    }

    @Override
    public void playerYCollision(Player player) {
        UnderPassObject self = this;
        if (player.mVel.y > 0 && player.mVel.y >= 0) {
            if (player.mPos.y + player.height > self.mPos.y &&
            player.mPos.y + player.height < self.mPos.y + self.height
            ) {
                    player.mPos.y = self.mPos.y - player.height;
                    player.mVel.y = 0;
                    player.onGround();
            }
        }
    }

    @Override
    public void draw() {
        Surge.objects.drawAt(this.drawName, (int)mPos.x, (int)mPos.y + (int)Surge.camera.y, width, height);
    }

}
