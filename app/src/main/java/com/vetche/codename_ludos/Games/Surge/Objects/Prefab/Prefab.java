package com.vetche.codename_ludos.Games.Surge.Objects.Prefab;

import com.vetche.codename_ludos.Entity.EntityManager;
import com.vetche.codename_ludos.Entity.TileMap;
import com.vetche.codename_ludos.Games.Surge.Surge;
import com.vetche.codename_ludos.LibraryTools.Math.Vector2D;

public class Prefab extends TileMap {
    private EntityManager game;
    private Vector2D pos = new Vector2D(0, 0);

    public Prefab(float offsetX, float offsetY, EntityManager mgr, int[][] array) {
        super(Surge.TILE_SIZE, array);
        setOffset(offsetX, offsetY);
        pos.set(offsetX, offsetY);
        game = mgr;
    }

    public Prefab(float offsetX, float offsetY, EntityManager mgr) {
        super(Surge.TILE_SIZE);
        setOffset(offsetX, offsetY);
        pos.set(offsetX, offsetY);
        game = mgr;

        setArray(PrefabManager.getPrefabArrays().getArray(0));
    }

    public int getMapHeight() {
        return size() * getTileSize();
    }

    public Vector2D getPos() {
        return new Vector2D(getOffset().x + pos.x, getOffset().y + pos.y);
    }
}
