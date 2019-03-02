package com.example.codename_ludos.Entity;

import android.graphics.Color;
import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Shapes;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

import java.util.ArrayList;

public class GameEntity {
    // Subclass for objects that will be pushed into EntityManager

    private boolean toRemove = false;

    public Vector2D mVel;
    public Vector2D mPos;
    public Vector2D mAcc;

    public int width;
    public int height;

    public static class Side {
        public boolean left = false;
        public boolean right = false;
        public boolean top = false;
        public boolean bottom = false;
        public void reset() {
            left = right = top = bottom = false;
        }
    }

    public Side side = new Side();

    protected GameTile[][] tiles = {
            {new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0)},
            {new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0)},
            {new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0)},
    };

    public GameEntity(float x, float y) {
        mPos = new Vector2D(x, y);
        mVel = new Vector2D(0, 0);
        mAcc = new Vector2D(0, 0);
    }

    public void remove() {
        toRemove = true;
    }

    public boolean isRemoved() {
        return toRemove;
    }

    public void move() {
        mPos.x += mVel.x * MainThread.getAverageDeltaTime();
        mPos.y += mVel.y * MainThread.getAverageDeltaTime();
    }

    public void move(float x, float y) {
        mPos.x += x * MainThread.getAverageDeltaTime();
        mPos.y += y * MainThread.getAverageDeltaTime();
    }

    public void moveX(float x) {
        mPos.x += x * MainThread.getAverageDeltaTime();
    }

    public void moveY(float y) {
        mPos.y += y * MainThread.getAverageDeltaTime();
    }

    public void accelerate() {
        mVel.x += mAcc.x;
        mVel.y += mAcc.y;
    }

    public void accelerate(float x, float y) {
        mVel.x += x;
        mVel.y += y;
    }

    public void accelerateX(float x) {
        mVel.x += x;
    }

    public void accelerateY(float y) {
        mVel.y += y;
    }

    /*
    *  int cx = (int)mPos.x / gameMap.tileSize;
        int cy = (int)mPos.y / gameMap.tileSize;
        int ox = -1;
        int oy = -1;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                int xx = cx + ox;
                int yy = cy + oy;
                tiles[y][x].cx = xx;
                tiles[y][x].cy = yy;
                if (gameMap.level.get(y).get(x) == 1) {
                    tiles[y][x].type = gameMap.level.get(y).get(x);
                    if (mPos.getY() > tiles[y][x].cy * gameMap.tileSize
                            &&  mPos.getY() < (tiles[y][x].cy * gameMap.tileSize + gameMap.tileSize)
                            && mPos.getX() > tiles[y][x].cx * gameMap.tileSize
                            &&  mPos.getX() < (tiles[y][x].cx * gameMap.tileSize + gameMap.tileSize)) {

                    }
                }
                ox++;
            }
            oy++;
        }*/

    public boolean overlap(GameEntity e) {
        return mPos.y + height > e.mPos.y
                &&  mPos.y < (e.mPos.y + e.height)
                && mPos.x + width > e.mPos.x
                &&  mPos.x < (e.mPos.x + e.width);
    }

    public boolean overlap(GameTile t, int tileSize) {
        int ox = ArcadeMachine.SCREEN_OFFSET_X;
        int oy = ArcadeMachine.SCREEN_OFFSET_Y;
        return mPos.x < t.cx + ox * tileSize + tileSize + ox
                && mPos.x + width > t.cx * tileSize + ox
                && mPos.y < t.cy * tileSize + tileSize + oy
                && mPos.y + height  > t.cy * tileSize + oy;
    }

    public int getTileMapValue(TileMap map) {
        try {
            int cx = (int)mPos.x / map.getTileSize();
            int cy = (int)mPos.y / map.getTileSize();
            return map.get(cy).get(cx);
        } catch (Exception e) {
            return 0;
        }
    }

    public void manageTileCollisionX(TileMap map, int minSolidTileID) {
        int cx = (int)(mPos.x) / map.getTileSize();
        int cy = (int)(mPos.y) / map.getTileSize();
        int ox = -1;
        int oy = -1;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                int xx = cx + ox;
                int yy = cy + oy;

                GameTile tile = tiles[y][x];
                tile.cx = xx;
                tile.cy = yy;

                try {
                    tile.type = map.get(yy).get(xx);
                }
                catch (Exception e){
                    tile.type = 0;
                }

                if (tile.type >= minSolidTileID) {
                    if (overlap(tile, map.getTileSize())) {
                        if (mVel.x != 0) {

                            if (mPos.x + width < tile.x(map.getTileSize())) {
                                //mPos.x = tile.x(map.getTileSize()) - width;
                                side.right = true;
                            }

                            if (mPos.x > tile.x(map.getTileSize()) + map.getTileSize()) {
                                //mPos.x = tile.x(map.getTileSize()) + map.getTileSize();
                                side.left = true;
                            }
                        }
                    }
                }
                ox++;
            }
            oy++;
        }
    }

    public void manageTileCollisionY(TileMap map, int minSolidTileID) {
        int cx = (int)(mPos.x) / map.getTileSize();
        int cy = (int)(mPos.y) / map.getTileSize();
        int ox = -1;
        int oy = -1;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                int xx = cx + ox;
                int yy = cy + oy;

                GameTile tile = tiles[y][x];
                tile.cx = xx;
                tile.cy = yy;

                try {
                    tile.type = map.get(yy).get(xx);
                }
                catch (Exception e){
                    tile.type = 0;
                }

                if (tile.type >= minSolidTileID) {
                    Shapes.setColor(Color.RED);


                    if (overlap(tile, map.getTileSize())) {
                        Shapes.drawRect(tiles[y][x].cx * map.getTileSize(),
                                tiles[y][x].cy  * map.getTileSize(), map.getTileSize(), map.getTileSize());
                        if (mVel.y > 0) {
                            if (mPos.y + height > tile.y(map.getTileSize())) {
                                //mPos.y = tile.y(map.getTileSize()) - height;
                                side.bottom = true;
                            }

                        } else if (mVel.y < 0) {
                            if (mPos.y < tile.y(map.getTileSize()) + map.getTileSize()) {
                               //mPos.y = tile.y(map.getTileSize()) + map.getTileSize();
                                side.top = true;
                            }
                        }
                    }
                }
                ox++;
            }
            oy++;
        }
    }

    public void draw() {

    }
    public void update() {

    }
}