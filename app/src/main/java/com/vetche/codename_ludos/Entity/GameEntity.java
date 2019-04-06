package com.vetche.codename_ludos.Entity;

import com.vetche.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.vetche.codename_ludos.Core.MainThread;
import com.vetche.codename_ludos.LibraryTools.Math.Vector2D;

public class GameEntity {
    // Subclass for objects that will be pushed into EntityManager

    private boolean toRemove = false;

    public Vector2D mVel;
    public Vector2D mPos;
    public Vector2D mAcc;
    public Vector2D mFric;

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
        mFric = new Vector2D(0, 0);
    }

    public void remove() {
        toRemove = true;
        onRemoved();
    }
    public void onRemoved() {

    }

    public boolean isRemoved() {
        return toRemove;
    }

    public void glide() {
        mVel.x *= mFric.x;
        mVel.y *= mFric.y;
    }

    public void glide(float x, float y) {
        mVel.x *= x;
        mVel.y *= y;
    }

    public void glideX(float x) {
        mVel.x *= x;
    }

    public void glideY(float y) {
        mVel.y *= y;
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
        mVel.x += mAcc.x * MainThread.getAverageDeltaTime();
        mVel.y += mAcc.y * MainThread.getAverageDeltaTime();
    }

    public void accelerate(float x, float y) {
        mVel.x += x * MainThread.getAverageDeltaTime();
        mVel.y += y * MainThread.getAverageDeltaTime();
    }

    public void accelerateX(float x) {
        mVel.x += x * MainThread.getAverageDeltaTime();
    }

    public void accelerateY(float y) {
        mVel.y += y * MainThread.getAverageDeltaTime();
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
        return mPos.x < t.cx * tileSize + tileSize + ox
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

        int tileX = width / map.getTileSize() + 1;
        int tileY = height / map.getTileSize() + 1;

        for (int y = -1; y < tileY; y++) {
            for (int x = -1; x < tileX; x++) {
                int xx = cx + x;
                int yy = cy + y;

                GameTile tile = new GameTile(xx, yy);

                try {
                    tile.ID = map.get(yy).get(xx);
                }
                catch (Exception e) {
                    tile.ID = 0;
                }

                if (tile.ID >= minSolidTileID) {
                    if (overlap(tile, map.getTileSize())) {
                        if (mVel.x > 0) {
                            if (mPos.x + width < tile.x(map.getTileSize())) {
                                onRightCollision(tile);
                                side.right = true;
                            }
                        }
                        if (mVel.x < 0) {
                            if (mPos.x > tile.x(map.getTileSize()) + map.getTileSize()) {
                                onLeftCollision(tile);
                                side.left = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public void manageTileCollisionY(TileMap map, int minSolidTileID) {
        int cx = (int)(mPos.x) / map.getTileSize();
        int cy = (int)(mPos.y) / map.getTileSize();

        int tileX = width / map.getTileSize() + 1;
        int tileY = height / map.getTileSize() + 1;

        for (int y = -1; y < tileY; y++) {
            for (int x = -1; x < tileX; x++) {
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

                if (tile.ID >= minSolidTileID) {

                    if (overlap(tile, map.getTileSize())) {
                        if (mVel.y > 0) {
                            if (mPos.y + height > tile.y(map.getTileSize())) {
                                onBottomCollision(tile);
                                side.bottom = true;
                            }

                        } else if (mVel.y < 0) {
                            if (mPos.y < tile.y(map.getTileSize()) + map.getTileSize()) {
                                onTopCollision(tile);
                                side.top = true;
                            }
                        }
                    }
                }
            }
        }
    }

    protected void onLeftCollision(GameTile tile) { mPos.x = tile.x(tile.tileSize) + tile.tileSize; }
    protected void onRightCollision(GameTile tile) { mPos.x = tile.x(tile.tileSize) - width; }
    protected void onTopCollision(GameTile tile) { mPos.y = tile.y(tile.tileSize) + tile.tileSize; }
    protected void onBottomCollision(GameTile tile) {
        mPos.y = tile.y(tile.tileSize) - height;
    }

    public void draw() {

    }
    public void update() {

    }
}