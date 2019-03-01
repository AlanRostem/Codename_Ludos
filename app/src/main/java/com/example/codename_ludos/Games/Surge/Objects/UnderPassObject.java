package com.example.codename_ludos.Games.Surge.Objects;

import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.Games.Surge.Player;
import com.example.codename_ludos.Games.Surge.Surge;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class UnderPassObject extends GameEntity {
    private String drawName;
    public UnderPassObject(String drawName, float x, float y, int width, int height) {
        super(x, y);
        this.drawName = drawName;
        this.height = height;
        this.width = width;
    }

    public static void playerXCollision(Player player, UnderPassObject self) {

    }

    public static void playerYCollision(Player player, UnderPassObject self) {
        Vector2D pTop = new Vector2D(player.mPos.x, player.mPos.y);
        Vector2D pBot = new Vector2D(player.mPos.x, player.mPos.y + player.height);

        Vector2D sL = new Vector2D(self.mPos.x, self.mPos.y);
        Vector2D sR = new Vector2D(self.mPos.x + self.width, self.mPos.y);

        Vector2D pBL = new Vector2D(player.mPos.x - 300, player.mPos.y + player.height);
        Vector2D pBR = new Vector2D(player.mPos.x + 600, player.mPos.y + player.height);

        Vector2D sTL = new Vector2D(self.mPos.x, self.mPos.y + 1);
        Vector2D sBL = new Vector2D(self.mPos.x, self.mPos.y + self.height);

        if (player.mVel.y > 0) {
            if (Vector2D.intersect(pTop, pBot, sL, sR) && !Vector2D.intersect(pBL, pBR, sTL, sBL)) {
                player.mPos.y = Vector2D.getIntersectPos(pTop, pBot, sL, sR).y - player.height;
                player.mVel.y = 0;
            }
        }
    }

    @Override
    public void draw() {
        Surge.objects.drawAt(this.drawName, (int)mPos.x, (int)mPos.y + (int)Surge.camera.y, width, height);
    }

}
