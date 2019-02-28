package com.example.codename_ludos.Entity;

import java.util.ArrayList;

public class TileMap extends ArrayList<ArrayList<Integer>> {

    private int mTileSize;

    public TileMap(int tileSize) {
        mTileSize = tileSize;
    }

    public TileMap(int tileSize, int[][] array) {
        mTileSize = tileSize;
        for (int i = 0; i < array.length; i++) {
            add(new ArrayList<Integer>());
            for (int j = 0; j< array[i].length; j++) {
                get(i).add(array[i][j]);
            }
        }
    }

    public int getTileSize() { return mTileSize; }

}
