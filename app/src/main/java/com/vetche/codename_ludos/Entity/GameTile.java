package com.vetche.codename_ludos.Entity;

public class GameTile {
    public int ID = 0;
    public int cx;
    public int cy;
    public int tileSize = 32;
    public GameTile(int x, int y) {
        cx = x;
        cy = y;
    }

    public GameTile(int x, int y, int ID, int tileSize) {
        cx = x;
        cy = y;
        this.ID = ID;
        this.tileSize = tileSize;
    }

    public int x(int tileSize, int offSet) { return cx * tileSize + offSet; }

    public int y(int tileSize, int offSet) { return cy * tileSize + offSet; }

    public int x(int tileSize) { return cx * tileSize; }

    public int y(int tileSize) { return cy * tileSize; }

}
