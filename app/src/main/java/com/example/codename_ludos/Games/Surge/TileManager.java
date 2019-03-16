package com.example.codename_ludos.Games.Surge;

import android.arch.core.util.Function;
import android.graphics.Color;
import android.service.quicksettings.Tile;
import android.util.Log;

import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Entity.GameTile;
import com.example.codename_ludos.Entity.TileMap;

import java.util.ArrayList;
import java.util.HashMap;

public class TileManager {

    static private int[][] array = {
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 10, 10, 10, 10, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 10},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 7, 8, 9, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, 10, 11, 12, 0},
    };

    public static TileMap tileMap = new TileMap(30*2, array);

    private static class TileRegion {
        public int min;
        public int max;
        public TileRegion(int a, int b) {
            min = a;
            max = b;
        }

        public boolean isWithinRegion(int tileID) {
            return min <= tileID && tileID <= max;
        }

        public void Xcollision(Player player, GameTile tile) {

        }
        public void Ycollision(Player player, GameTile tile) {

        }
    }

    private static TileRegion solid = new TileRegion(10, 12) {

        @Override
        public void Xcollision(Player player, GameTile tile) {
            if (player.mVel.x > 0) {
                if (player.mPos.x + player.width > tile.x(tile.tileSize, (int)tileMap.getOffset().x)) {
                    player.side.right = true;
                    player.mVel.x = 0;
                    player.mPos.x = tile.x(tile.tileSize, (int)tileMap.getOffset().x) - player.width;
                }
            }
            if (player.mVel.x < 0) {
                if (player.mPos.x < tile.cx * tile.tileSize + tile.tileSize + (int)tileMap.getOffset().x) {
                    player.side.left = true;
                    player.mVel.x = 0;
                    player.mPos.x = tile.cx * tile.tileSize + tile.tileSize + (int)tileMap.getOffset().x;
                }
            }
        }

        @Override
        public void Ycollision(Player player, GameTile tile) {
            if (player.mVel.y > 0) {
                if (player.mPos.y + player.height > tile.y(tile.tileSize, (int)tileMap.getOffset().y)) {
                    player.side.bottom = true;
                    player.mVel.y = 0;
                    player.onGround();
                    player.mPos.y = tile.y(tile.tileSize, (int)tileMap.getOffset().y) - player.height;
                }
            }
            if (player.mVel.y < 0) {
                if (player.mPos.y < (float)(tile.y(tile.tileSize, (int)tileMap.getOffset().y) + tile.tileSize)) {
                    player.side.top = true;
                    player.mVel.y = 0;
                    player.mPos.y = (float)(tile.y(tile.tileSize, (int)tileMap.getOffset().y) + tile.tileSize);
                }
            }
        }
    };

    private static TileRegion oneWay = new TileRegion(7, 9);

    private static TileRegion transparent = new TileRegion(1, 6);

    public static void callXCollision(GameTile tile, Player player) {
        if (solid.isWithinRegion(tile.ID)) {
            solid.Xcollision(player, tile);
        } else if (oneWay.isWithinRegion(tile.ID)) {
            oneWay.Xcollision(player, tile);
        } else if (transparent.isWithinRegion(tile.ID)) {
            transparent.Xcollision(player, tile);
        }
    }

    public static void callYCollision(GameTile tile, Player player) {
        if (solid.isWithinRegion(tile.ID)) {
            solid.Ycollision(player, tile);
        } else if (oneWay.isWithinRegion(tile.ID)) {
            oneWay.Ycollision(player, tile);
        } else if (transparent.isWithinRegion(tile.ID)) {
            transparent.Ycollision(player, tile);
        }
    }
}
