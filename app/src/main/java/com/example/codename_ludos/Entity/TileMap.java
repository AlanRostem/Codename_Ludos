package com.example.codename_ludos.Entity;

import java.util.ArrayList;

public class TileMap extends ArrayList<ArrayList<Integer>> {

    private int mTileSize;

    public TileMap(int tileSize) {
        mTileSize = tileSize;
    }

    public int getTileSize() { return mTileSize; }

}
