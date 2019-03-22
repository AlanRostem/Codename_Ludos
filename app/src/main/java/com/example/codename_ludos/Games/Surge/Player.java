package com.example.codename_ludos.Games.Surge;

import android.graphics.Color;
import android.service.quicksettings.Tile;
import android.util.Log;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.Entity.GameTile;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.Games.Surge.TileBased.MainTileMap;
import com.example.codename_ludos.UserInterface.Controllers.Controls;
import com.example.codename_ludos.Entity.BasePlayer;
import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.Games.Surge.Objects.Items.PowerUp;
import com.example.codename_ludos.Games.Surge.Objects.SurgeEntity;
import com.example.codename_ludos.R;

import java.util.ArrayList;

public class Player extends BasePlayer {
    private SpriteMap sprite;

    private ArrayList<PowerUp> activePowerUps = new ArrayList<>();


    public Player() {
        super(320, ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT);
        mPos.y *= 2f/3f;
        sprite = new SpriteMap(R.drawable.rubigo);
        sprite.bindSprite("a1", 0, 0, 48, 48);
        width = 32 * 2;
        height = 64 * 2;
        for (PowerUp p : activePowerUps) {
            p.setInactive();
        }
    }

    public void setPowerUp(int i, PowerUp p) {
        if (i < activePowerUps.size()) {
            activePowerUps.set(i, p);
        }
    }

    public void onGround() {
        djumping = false;
        jumping = false;
        jumps = 0;
    }

    private void controlling() {
        Controls controls = ArcadeMachine.getCurrentGame().getControls();

        if (controls.isTouched("jump")) {
            //mVel.y = (300.f);

            ///*
            if (!jumping) {
                jumping = true;
                if (djumping) {
                    if (mVel.y > 0) {
                        mVel.y = (300.f);
                    } else {
                        jumps = 0;
                    }
                }
                else
                    mVel.y = (900.f);
            }//*/
        }

        glideX(0.9f);
        if (controls.isTouched("right"))
            accelerateX(2000.f);

        if (controls.isTouched("left"))
            accelerateX(-2000.f);
    }

    public boolean jumping = false;
    public int jumps = 0;
    public int maxJumps = 1;
    public boolean djumping = false;
    int gravty = 1700;

    private void step() {
        if (!side.bottom) {
            accelerateY(-1700);
        } else {
            mVel.y = 0;
        }

        side.reset();

        MainTileMap tm = Surge.tileMap;
        tm.setMapOffset(tm.getMapOffset() + mVel.y * MainThread.getAverageDeltaTime());
        manageCollisionY();
        manageTileCollisionY(Surge.tileMap, 0);

        moveX(mVel.x);
        manageCollisionX();
        //manageTileCollisionX(Surge.tileMap, 0);

        //moveY(mVel.y);

        for (int i = 0; i < activePowerUps.size(); i++) {
            if (activePowerUps.get(i).isRemoved()) {
                activePowerUps.remove(i);
                break;
            }
            if (activePowerUps.get(i).isUsing() && !activePowerUps.get(i).isDone()) {
                activePowerUps.get(i).buff(this);
            } else {
                activePowerUps.get(i).remove();
                activePowerUps.remove(activePowerUps.get(i));
            }
        }

        int H = ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT;
        if (mPos.y + height > H) {
            mPos.y = H - height;
            onGround();
            side.bottom = true;
        }

        /*if (Surge.tileMap.getMapOffset() < ArcadeMachine.SCREEN_OFFSET_Y) {
            Surge.tileMap.setMapOffset(ArcadeMachine.SCREEN_OFFSET_Y);
            onGround();
            side.bottom = true;
        }*/

        Surge.camera.update(0, mPos.y, 0, (ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT) / 3.f);
    }

    @Override
    public boolean overlap(GameTile t, int tileSize) {
        int ox = (int)Surge.tileMap.getOffset().x;
        int oy = (int)Surge.tileMap.getOffset().y;
        return mPos.x < t.cx * tileSize + tileSize + ox
                && mPos.x + width > t.cx * tileSize + ox
                && mPos.y < t.cy * tileSize + tileSize + oy
                && mPos.y + height > t.cy * tileSize + oy;
    }

    @Override
    public void update() {
        controlling();
        step();
    }

    @Override
    public void draw() {

        Shapes.setColor(Color.argb(0.5f, 0f,1f,1f));

        for (int i = 0; i < activePowerUps.size(); i++) {
            if (activePowerUps.get(i).isUsing() && !activePowerUps.get(i).isDone()) {
                Shapes.drawRect(
                        Surge.camera.x + mPos.x + i * width / 2 - activePowerUps.get(i).width / 2 - i * (activePowerUps.get(i).width) + (activePowerUps.size() - i) * activePowerUps.get(i).width / 2,
                        Surge.camera.y + mPos.y - activePowerUps.get(i).height - 10,
                        (int)(activePowerUps.get(i).width * (activePowerUps.get(i).getDuration() / activePowerUps.get(i).getMaxDuration())),
                        10);
                activePowerUps.get(i).draw(
                        mPos.x + i * width / 2 - activePowerUps.get(i).width / 2 - i * (activePowerUps.get(i).width)
                            + (activePowerUps.size() - i) * activePowerUps.get(i).width / 2,
                        mPos.y - activePowerUps.get(i).height);

            }
        }

        Shapes.setColor(Color.argb(1f, 1f,1f,1f));
        sprite.drawAt("a1", (int) mPos.x, (int) mPos.y + (int) Surge.camera.y, width, height);
    }

    @Override
    public void manageTileCollisionX(TileMap map, int minSolidTileID) {
        int cx = (int)(mPos.x - map.getOffset().x) / map.getTileSize();
        int cy = (int)(mPos.y - map.getOffset().y) / map.getTileSize();

        int tileX = width / map.getTileSize() + 1;
        int tileY = height / map.getTileSize() + 2;

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
                    Surge.tileMap.callXCollision(tile, this);
                }
            }
        }
    }

    @Override
    public void manageTileCollisionY(TileMap map, int minSolidTileID) {
        int cx = (int)(mPos.x - map.getOffset().x) / map.getTileSize();
        int cy = (int)(mPos.y - map.getOffset().y) / map.getTileSize();

        int tileX = width / map.getTileSize() + 1;
        int tileY = height / map.getTileSize() + 2;

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
                    Surge.tileMap.callYCollision(tile, this);
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
                            if (activePowerUps.size() < 4) {
                                PowerUp p = (PowerUp) e;
                                boolean remove = false;
                                if (!p.isUsing()) {
                                    for (PowerUp a : activePowerUps) {
                                        if (a.getClass().equals(p.getClass())) {
                                            a.setDuration(p.getDuration());
                                            remove = true;
                                        }
                                    }
                                    activePowerUps.add(p);
                                    if (!remove) {
                                        p.use();
                                    } else {
                                        p.remove();
                                    }
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