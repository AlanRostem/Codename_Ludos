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

    protected Vector2D mVel;
    protected Vector2D mPos;
    protected Vector2D mAcc;

    protected int width;
    protected int height;

    public static class Side {
        public boolean left = false;
        public boolean right = false;
        public boolean top = false;
        public boolean bottom = false;
        public void reset() {
            left = right = top = bottom = false;
        }
    }

    protected Side side = new Side();


    protected GameTile[][] tiles = {
            {new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0)},
            {new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0)},
            {new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0)},
            {new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0)}
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
        return mPos.y > e.mPos.y
                &&  mPos.y < (e.mPos.y + e.height)
                && mPos.getX() > e.mPos.x
                &&  mPos.getX() < (e.mPos.x + e.width);
    }

    public boolean overlap(GameTile t, int tileSize) {
        return mPos.x < t.cx * tileSize + tileSize
                && mPos.x + width > t.cx * tileSize
                && mPos.y  < t.cy * tileSize + tileSize
                && mPos.y + height  > t.cy * tileSize;
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
        int offSettX = map.offSettX + ArcadeMachine.SCREEN_OFFSET_X;
        int offSettY = map.offSettY + ArcadeMachine.SCREEN_OFFSET_Y;
        int cx = (int)(mPos.x - offSettX) / map.getTileSize();
        int cy = (int)(mPos.y - offSettY) / map.getTileSize();
        int ox = -1;
        int oy = -1;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                int xx = cx + ox;
                int yy = cy + oy;
                GameTile tile = tiles[y][x];
                tile.cx = xx;
                tile.cy = yy;
                try { tile.type = map.get(yy).get(xx); }
                catch (Exception e){ tile.type = 0; }
                if (tile.type >= minSolidTileID) {
                    Shapes.setColor(Color.YELLOW);
                    Shapes.drawRect(tiles[y][x].cx * map.getTileSize() + offSettX,
                            tiles[y][x].cy  * map.getTileSize() + offSettY, map.getTileSize(), map.getTileSize());
                    if (overlap(tile, map.getTileSize())) {
                        if (mVel.x != 0) {
                            if (mPos.x + width < tile.x(map.getTileSize(), offSettX)) {
                                //mPos.x = tile.x(map.getTileSize(), offSettX) - width + offSettX;
                                side.right = true;
                            }
                            //} else if (mVel.x > 0) {
                            if (mPos.x > tile.x(map.getTileSize(), offSettX) + map.getTileSize()) {
                                //mPos.x = tile.x(map.getTileSize(), offSettX) + map.getTileSize() + offSettX;
                                side.left = true;
                            }
                        }
                    }
                }
                ox++;
            }
            ox -= 4;
            oy++;
        }
    }

    public void manageTileCollisionY(TileMap map, int minSolidTileID) {
        int offSettX = map.offSettX + ArcadeMachine.SCREEN_OFFSET_X;
        int offSettY = map.offSettY + ArcadeMachine.SCREEN_OFFSET_Y;

        int cx = (int)(mPos.x - offSettX) / map.getTileSize();
        int cy = (int)(mPos.y - offSettY) / map.getTileSize();
        int ox = -1;
        int oy = -1;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                int xx = cx + ox;
                int yy = cy + oy;
                GameTile tile = tiles[y][x];
                tile.cx = xx;
                tile.cy = yy;
                try { tile.type = map.get(yy).get(xx); }
                catch (Exception e){ tile.type = 0; }
                if (tile.type >= minSolidTileID) {
                    Shapes.setColor(Color.RED);
                    Shapes.drawRect(tiles[y][x].cx * map.getTileSize() + offSettX,
                            tiles[y][x].cy  * map.getTileSize() + offSettY, map.getTileSize(), map.getTileSize());
                    if (overlap(tile, map.getTileSize())) {
                        if (mVel.y > 0) {
                            if (mPos.y + height > tile.y(map.getTileSize(), offSettY)) {
                                //mPos.y = tile.y(map.getTileSize(), offSettY) - height;
                                side.bottom = true;
                            }
                        } else if (mVel.y < 0) {
                            if (mPos.y < tile.y(map.getTileSize(), offSettY) + map.getTileSize()) {
                               //mPos.y = tile.y(map.getTileSize(), offSettY) + map.getTileSize();
                                side.top = true;
                            }
                        }
                    }
                }
                ox++;
            }
            ox-=4;
            oy++;
        }
    }

    public void draw() {

    }
    public void update() {

    }
}