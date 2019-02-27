package com.example.codename_ludos.Entity;

public class GameTile {
    public int type = 0;
    public int cx;
    public int cy;
    public GameTile(int x, int y) {
        cx = x;
        cy = y;
    }

    public int x(int tileSize, int offSett) { return cx * tileSize + offSett; }

    public int y(int tileSize, int offSett) { return cy * tileSize + offSett; }

}
