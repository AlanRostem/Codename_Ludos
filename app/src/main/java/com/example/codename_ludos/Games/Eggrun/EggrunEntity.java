package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.Entity.GameTile;
import com.example.codename_ludos.Entity.TileMap;


public class EggrunEntity extends GameEntity {

    protected int width;
    protected int height;
    protected float gravity = 50f;

    public EggrunEntity(float x, float y, int w, int h){
        super(x,y);
        width = w;
        height = h;
    }

    public boolean overlap(GameTile t, int tileSize) {
        int ox = gameMap.offSet + ArcadeMachine.SCREEN_OFFSET_X;
        int oy = ArcadeMachine.SCREEN_OFFSET_Y;
        return mPos.x - ox < t.cx * tileSize + tileSize
                && mPos.x - ox + width > t.cx * tileSize
                && mPos.y - oy < t.cy * tileSize + tileSize
                && mPos.y - oy + height > t.cy * tileSize;
    }

    @Override
    public void manageTileCollisionX(TileMap map, int minSolidTileID) {
        int offSettX = gameMap.offSet + ArcadeMachine.SCREEN_OFFSET_X;
        int offSettY = ArcadeMachine.SCREEN_OFFSET_Y;
        int cx = (int)(mPos.x - offSettX) / map.getTileSize();
        int cy = (int)(mPos.y - offSettY) / map.getTileSize();
        int ox = -1;
        int oy = 0;

        GameTile tile = new GameTile(0, 0);

        for (int y = -1; y < height/map.getTileSize() + 1; y++) {
            for (int x = -1; x < width/map.getTileSize() + 1; x++) {
                int xx = cx + ox;
                int yy = cy + oy;

                tile.cx = xx;
                tile.cy = yy;

                try {
                    tile.ID = map.get(yy).get(xx);
                }
                catch (Exception e){
                    tile.ID = 0;
                }



                if (tile.ID >= minSolidTileID) {

                    if (overlap(tile, map.getTileSize())) {
                            if (mPos.x + width > tile.x(map.getTileSize(), offSettX) && x >= (width/map.getTileSize() + 2)/2) {
                                mVel.x = 0;
                                mPos.x = tile.x(map.getTileSize(), offSettX) - width;
                                side.right = true;
                            }

                            if (mPos.x <= tile.x(map.getTileSize(), offSettX) + map.getTileSize() && x < (width/map.getTileSize() + 2)/2) {
                                mVel.x = 0;
                                mPos.x = tile.x(map.getTileSize(), offSettX) + map.getTileSize();
                                side.left = true;
                            }
                    }
                }
                ox++;
            }
            ox = -1; oy++;
        }
    }

    @Override
    public void manageTileCollisionY(TileMap map, int minSolidTileID) {
        int offSettX = gameMap.offSet + ArcadeMachine.SCREEN_OFFSET_X;
        int offSettY = ArcadeMachine.SCREEN_OFFSET_Y;
        int cx = (int)(mPos.x - offSettX) / map.getTileSize();
        int cy = (int)(mPos.y - offSettY) / map.getTileSize();
        int ox = 0;
        int oy = -1;

        GameTile tile = new GameTile(0, 0);

        for (int y = -1; y < height/map.getTileSize() + 1; y++) {
            for (int x = -1; x < width/map.getTileSize() + 1; x++) {
                int xx = cx + ox;
                int yy = cy + oy;

                tile.cx = xx;
                tile.cy = yy;

                try {
                    tile.ID = map.get(yy).get(xx);
                }
                catch (Exception e) {
                    tile.ID = 0;
                }

                if (tile.ID >= minSolidTileID) {

                    if (overlap(tile, map.getTileSize())) {
                        if (mVel.y > 0.f)
                            if (mPos.y + height >= tile.y(map.getTileSize(), offSettY) && y >= (height/map.getTileSize() + 2)/2) {
                                mPos.y = tile.y(map.getTileSize(), offSettY) - height;
                                mVel.y = 0;
                                side.bottom = true;
                            }
                       if (mVel.y < 0.f)
                            if (mPos.y <= tile.y(map.getTileSize(), offSettY) + map.getTileSize() && y < (height/map.getTileSize() + 2)/2) {
                                mVel.y = 0;
                                mPos.y = tile.y(map.getTileSize(), offSettY) + map.getTileSize();
                                side.top = true;
                            }
                    }
                }
                ox++;
            }
            ox = 0; oy++;
        }
    }

    public static GameMap gameMap;

}
