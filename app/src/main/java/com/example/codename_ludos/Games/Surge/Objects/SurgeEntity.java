package com.example.codename_ludos.Games.Surge.Objects;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.Games.Surge.Objects.Items.PowerUp;
import com.example.codename_ludos.Games.Surge.Player;
import com.example.codename_ludos.Games.Surge.Surge;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.R;

public class SurgeEntity extends GameEntity {
    protected static SpriteMap objects = ArcadeMachine
            .getGame("Surge")
            .getAssetLoader()
            .getAsset("s_surge_world")
            .asSpriteMap();
    protected String drawName;

    public static void initializeSpriteMap() {
        objects.bindSprite("doublejump", 0, 0, 20, 20);
        objects.bindSprite("walljump", 0, 20, 20, 20);

        objects.bindSprite("oneWay", 80, 0, 96, 16);
        objects.bindSprite("solidWall", 180, 0, 20, 80);
        objects.bindSprite("supreme", 0, 40, 181, 140);
    }

    public SurgeEntity(float x, float y) {
        super(x, y);

    }

    public SurgeEntity(String drawName, float x, float y, int width, int height) {
        super(x, y);
        this.height = height;
        this.width = width;
        this.drawName = drawName;
    }

    public void playerXCollision(Player player) {

    }

    public void playerYCollision(Player player) {

    }

    @Override
    public void update() {
        if (Math.abs(mPos.y) - Math.abs(Surge.player.mPos.y) < -(ArcadeMachine.SCREEN_HEIGHT + ArcadeMachine.SCREEN_OFFSET_Y)) {
            remove();
        }
    }

    @Override
    public void draw() {
        objects.drawAt(drawName,
                (int) mPos.x + (int) Surge.camera.x,
                (int) mPos.y + (int) Surge.camera.y, width, height);
    }
}
