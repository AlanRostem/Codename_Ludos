package com.example.codename_ludos.Games.Surge.Objects;

import com.example.codename_ludos.Entity.EntityManager;
import com.example.codename_ludos.Entity.EntitySpawner;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.Games.Surge.Surge;
import com.example.codename_ludos.Games.Surge.TileManager;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class Prefab extends TileMap {
    private EntityManager game;

    public Prefab(float offsetX, float offsetY, EntityManager mgr, int[][] array) {
        super(Surge.TILE_SIZE, array);
        setOffset(offsetX, offsetY);
        game = mgr;
        onCreate();
    }

    private void onCreate() {
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.get(i).size(); j++) {
                int tile = this.get(i).get(j);
                if (tile >= 3 && tile <= 6) {
                    game.spawnEntity(TileManager.powerUpSpawns.get(tile).apply(new Vector2D(j * getTileSize(), i * getTileSize())));
                }
            }
        }
    }
}
