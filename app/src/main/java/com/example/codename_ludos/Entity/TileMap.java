package com.example.codename_ludos.Entity;

import com.example.codename_ludos.LibraryTools.Math.Vector2D;

import java.util.ArrayList;

public class TileMap extends ArrayList<ArrayList<Integer>> {

    private int mTileSize;
    Vector2D offset = new Vector2D(0, 0);

    public TileMap(int tileSize) {
        mTileSize = tileSize;
    }

    public void setOffset(float x, float y) {
        this.offset.set(x, y);
    }

    public Vector2D getOffset() {
        return offset;
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

    public TileMap(int tileSize, int[][] array, float x, float y) {
        mTileSize = tileSize;
        setOffset(x ,y);
        for (int i = 0; i < array.length; i++) {
            add(new ArrayList<Integer>());
            for (int j = 0; j< array[i].length; j++) {
                get(i).add(array[i][j]);
            }
        }
    }

    public int getTileSize() { return mTileSize; }

}
