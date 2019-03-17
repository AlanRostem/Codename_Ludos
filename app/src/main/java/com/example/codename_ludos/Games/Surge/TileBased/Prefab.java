package com.example.codename_ludos.Games.Surge.TileBased;

import com.example.codename_ludos.Entity.EntityManager;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.Games.Surge.Surge;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class Prefab extends TileMap {
    private EntityManager game;
    private Vector2D pos = new Vector2D(0, 0);

    public Prefab(float offsetX, float offsetY, EntityManager mgr, int[][] array) {
        super(Surge.TILE_SIZE, array);
        setOffset(offsetX, offsetY);
        pos.set(offsetX, offsetY);
        game = mgr;
        onCreate();
    }

    public Prefab(float offsetX, float offsetY, EntityManager mgr) {
        super(Surge.TILE_SIZE);
        setOffset(offsetX, offsetY);
        pos.set(offsetX, offsetY);
        game = mgr;
        onCreate();
    }

    public Vector2D getPos() {
        return new Vector2D(getOffset().x + pos.x, getOffset().y + pos.y);
    }

    private void onCreate() {
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.get(i).size(); j++) {
                int tile = this.get(i).get(j);
                if (tile >= 3 && tile <= 6) {
                    game.spawnEntity(Surge.tileMap.powerUpSpawns.get(tile).apply(new Vector2D(j * getTileSize(), i * getTileSize())));
                }
            }
        }
    }
}
