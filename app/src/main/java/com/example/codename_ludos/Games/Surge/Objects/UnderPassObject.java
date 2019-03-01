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
        Vector2D a = new Vector2D(player.mPos.x, player.mPos.y);
        Vector2D b = new Vector2D(player.mPos.x, player.mPos.y + player.height);

        Vector2D c = new Vector2D(self.mPos.x, self.mPos.y);
        Vector2D d = new Vector2D(self.mPos.x + self.width, self.mPos.y);

        if (player.mVel.y > 0) {
            if ((int)self.mPos.y != (int)Vector2D.getIntersectPos(a, b, c, d).y &&
                    (int)(player.mPos.y + player.height) > (int)Vector2D.getIntersectPos(a, b, c, d).y) {
                player.mPos.y = self.mPos.y - player.height;
                player.mVel.y = 0;
            }
        }
    }

    @Override
    public void draw() {
        Surge.objects.drawAt(this.drawName, (int)mPos.x, (int)mPos.y + (int)Surge.camera.y, width, height);
    }

}
