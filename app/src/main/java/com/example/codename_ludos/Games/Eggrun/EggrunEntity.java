package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Shapes;
import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.Entity.GameTile;
import com.example.codename_ludos.Entity.TileMap;


public class EggrunEntity extends GameEntity {

    protected int width;
    protected int height;
    protected float gravity = 25f;

    protected GameTile[][] tiles = {
            {new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0)},
            {new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0)},
            {new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0)},
            {new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0), new GameTile(0, 0)}
    };

    public EggrunEntity(float x, float y, int w, int h){
        super(x,y);
        width = w;
        height = h;
    }

    public boolean overlap(GameTile t, TileMap map, int tileSize) {
        int ox = map.offsetX + ArcadeMachine.SCREEN_OFFSET_X;
        int oy = map.offsetY + ArcadeMachine.SCREEN_OFFSET_Y;
        return mPos.x < t.cx + ox * tileSize + tileSize + ox
                && mPos.x + width > t.cx * tileSize + ox
                && mPos.y  < t.cy * tileSize + tileSize + oy
                && mPos.y + height  > t.cy * tileSize + oy;
    }

    @Override
    public void manageTileCollisionX(TileMap map, int minSolidTileID) {
        int offSettX = map.offsetX + ArcadeMachine.SCREEN_OFFSET_X;
        int offSettY = map.offsetY + ArcadeMachine.SCREEN_OFFSET_Y;
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

                try {
                    tile.type = map.get(yy).get(xx);
                }
                catch (Exception e){
                    tile.type = 0;
                }

                if (tile.type >= minSolidTileID) {
                    Shapes.setColor(Color.YELLOW);
                    Shapes.drawRect(tiles[y][x].cx * map.getTileSize() + offSettX,
                            tiles[y][x].cy  * map.getTileSize() + offSettY, map.getTileSize(), map.getTileSize());

                    if (overlap(tile, map, map.getTileSize())) {
                        if (mVel.x != 0) {

                            if (mPos.x + width < tile.x(map.getTileSize(), offSettX)) {
                                //mPos.x = tile.x(map.getTileSize(), offSettX) - width + offSettX;
                                side.right = true;
                            }

                            if (mPos.x > tile.x(map.getTileSize(), offSettX) + map.getTileSize()) {
                                //mPos.x = tile.x(map.getTileSize(), offSettX) + map.getTileSize() + offSettX;
                                side.left = true;
                            }
                        }
                    }
                }
                ox++;
            }
            ox -= 4; oy++;
        }
    }

    @Override
    public void manageTileCollisionY(TileMap map, int minSolidTileID) {
        int offSettX = map.offsetX + ArcadeMachine.SCREEN_OFFSET_X;
        int offSettY = map.offsetY + ArcadeMachine.SCREEN_OFFSET_Y;
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

                try {
                    tile.type = map.get(yy).get(xx);
                }
                catch (Exception e){
                    tile.type = 0;
                }

                if (tile.type >= minSolidTileID) {
                    Shapes.setColor(Color.RED);


                    if (overlap(tile, map, map.getTileSize())) {
                        Shapes.drawRect(tiles[y][x].cx * map.getTileSize() + offSettX,
                                tiles[y][x].cy  * map.getTileSize() + offSettY, map.getTileSize(), map.getTileSize());
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

    public static GameMap gameMap;

}
