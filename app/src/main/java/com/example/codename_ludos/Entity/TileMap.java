package com.example.codename_ludos.Entity;

import java.util.ArrayList;

public class TileMap extends ArrayList<ArrayList<Integer>> {

    private int mTileSize;
    public int offsetX = 0;
    public int offsetY = 0;

    public TileMap(int tileSize) {
        mTileSize = tileSize;
    }

    public int getTileSize() { return mTileSize; }

}
