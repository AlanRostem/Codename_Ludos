package com.example.codename_ludos.Games.Surge;

import android.graphics.Color;
import android.util.Log;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.Entity.GameTile;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.UserInterface.Controllers.Controls;
import com.example.codename_ludos.Entity.BasePlayer;
import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.Games.Surge.Objects.Items.PowerUp;
import com.example.codename_ludos.Games.Surge.Objects.SurgeEntity;
import com.example.codename_ludos.R;

import java.util.ArrayList;

public class Player extends BasePlayer {
    private SpriteMap sprite;

    PowerUp[] activePowerUps = {
            new PowerUp("none", 0, 0, 0, 0),
            new PowerUp("none", 0, 0, 0, 0),
            new PowerUp("none", 0, 0, 0, 0),
            new PowerUp("none", 0, 0, 0, 0)
    };


    public Player() {
        super(320, ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT);
        sprite = new SpriteMap(R.drawable.rubigo);
        sprite.bindSprite("a1", 0, 0, 48, 48);
        width = 32 * 2;
        height = 64 * 2;
        for (PowerUp p : activePowerUps) {
            p.setInactive();
        }
    }

    public void onGround() {
        djumping = false;
        jumping = false;
        jumps = 0;
    }

    private void controlling() {
        Controls controls = ArcadeMachine.getCurrentGame().getControls();

        if (controls.isTouched("jump"))
            if (!jumping) {
                jumping = true;
                if (djumping) {
                    if (mVel.y > 0) {
                        mVel.y = (-300.f);
                    } else {
                        jumps = 0;
                    }
                }
                else
                    mVel.y = (-900.f);
            }

        mVel.setX(0);
        if (controls.isTouched("right"))
            mVel.x = (400.f);

        if (controls.isTouched("left"))
            mVel.x = (-400.f);
    }

    public boolean jumping = false;
    public int jumps = 0;
    public int maxJumps = 1;
    public boolean djumping = false;


    private void step() {
        accelerateY(1700);
        side.reset();

        moveX(mVel.x);
        manageCollisionX();
        manageTileCollisionX(TileManager.tileMap, 0);

        moveY(mVel.y);
        manageCollisionY();
        manageTileCollisionY(TileManager.tileMap, 0);

        for (int i = 0; i < activePowerUps.length; i++) {
            if (activePowerUps[i].isUsing() && !activePowerUps[i].isDone()) {
                activePowerUps[i].buff(this);
            }
        }

        int H = ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT;
        if (mPos.y + height > H) {
            mPos.y = H - height;
            onGround();
            side.bottom = true;
        }

        Surge.camera.update(0, mPos.y, 0, (ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT) / 3.f);
    }

    @Override
    public void update() {
        controlling();
        step();
    }

    @Override
    public void draw() {
        /*int cx = (int)(mPos.x) / TileManager.tileMap.getTileSize();
        int cy = (int)(mPos.y) / TileManager.tileMap.getTileSize();

        int tileX = width / TileManager.tileMap.getTileSize() + 2;
        int tileY = height / TileManager.tileMap.getTileSize() + 2;
        for (int y = -1; y < tileY; y++) {
            for (int x = -1; x < tileX; x++) {
                int xx = cx + x;
                int yy = cy + y;
                Shapes.drawRect(xx * TileManager.tileMap.getTileSize(), yy * TileManager.tileMap.getTileSize(),
                        TileManager.tileMap.getTileSize(), TileManager.tileMap.getTileSize());
            }
        }*/


        Shapes.setColor(Color.argb(0.5f, 1f,0f,0f));
        Shapes.drawRect(
                mPos.x,
                mPos.y,
                width, height
        );
        Shapes.setColor(Color.argb(1f, 1f,1f,1f));
        sprite.drawAt("a1", (int) mPos.x, (int) mPos.y + (int) Surge.camera.y, width, height);
    }

    @Override
    public void manageTileCollisionX(TileMap map, int minSolidTileID) {
        int cx = (int)(mPos.x) / map.getTileSize();
        int cy = (int)(mPos.y) / map.getTileSize();

        int tileX = width / map.getTileSize() + 1;
        int tileY = height / map.getTileSize();

        for (int y = -3; y < tileY; y++) {
            for (int x = -2; x < tileX; x++) {
                int xx = cx + x;
                int yy = cy + y;

                GameTile tile = new GameTile(xx, yy);
                tile.tileSize = map.getTileSize();

                try {
                    tile.ID = map.get(yy).get(xx);
                }
                catch (Exception e) {
                    tile.ID = 0;
                }

                if (overlap(tile, map.getTileSize())) {
                    TileManager.callXCollision(tile, this);
                }
            }
        }
    }

    @Override
    public void manageTileCollisionY(TileMap map, int minSolidTileID) {
        int cx = (int)(mPos.x) / map.getTileSize();
        int cy = (int)(mPos.y) / map.getTileSize();

        int tileX = width / map.getTileSize() + 1;
        int tileY = height / map.getTileSize();

        for (int y = -3; y < tileY; y++) {
            for (int x = -2; x < tileX; x++) {
                int xx = cx + x;
                int yy = cy + y;

                GameTile tile = new GameTile(xx, yy);
                tile.tileSize = map.getTileSize();

                try {
                    tile.ID = map.get(yy).get(xx);
                }
                catch (Exception e) {
                    tile.ID = 0;
                }

                if (overlap(tile, map.getTileSize())) {
                    TileManager.callYCollision(tile, this);
                }
            }
        }
    }

    public void manageCollisionX() {
        ArrayList<GameEntity> list = ArcadeMachine.getCurrentGame().getEntityList();
        for (GameEntity e : list) {
            if (e != this)
                if (overlap(e)) {
                    if (e instanceof SurgeEntity) {
                        ((SurgeEntity) e).playerXCollision(this);
                    }
                }
        }
    }

    public void manageCollisionY() {
        ArrayList<GameEntity> list = ArcadeMachine.getCurrentGame().getEntityList();
        for (GameEntity e : list) {
            if (e != this)
                if (overlap(e)) {
                    if (e instanceof SurgeEntity) {
                        if (e instanceof PowerUp) {
                            for (int i = 0; i < activePowerUps.length; i++) {
                                if (!activePowerUps[i].isUsing() && activePowerUps[i].isDone()) {
                                    activePowerUps[i] = (PowerUp) e;
                                    activePowerUps[i].use();
                                    break;
                                }
                            }
                        } else {
                            ((SurgeEntity) e).playerYCollision(this);
                        }
                    }
                }
        }
    }

}