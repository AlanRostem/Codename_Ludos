package com.example.codename_ludos.Entity;

public class GameTile {
    public int type = 0;
    public int cx;
    public int cy;
    public GameTile(int x, int y) {
        cx = x;
        cy = y;
    }

    public int x(int tileSize) {
        return cx * tileSize;
    }

    public int y(int tileSize) {
        return cy * tileSize;
    }

}
