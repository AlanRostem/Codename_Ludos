package com.vetche.codename_ludos.Games.Surge.Objects.Prefab;

import android.arch.core.util.Function;
import android.util.Log;

import com.vetche.codename_ludos.ArcadeMachine.ArcadeGame;
import com.vetche.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.vetche.codename_ludos.Assets.Graphics.SpriteMap;
import com.vetche.codename_ludos.Games.Surge.Objects.Items.DoubleJump;
import com.vetche.codename_ludos.Games.Surge.Objects.Items.WallClimb;
import com.vetche.codename_ludos.Games.Surge.Objects.Items.WallJump;
import com.vetche.codename_ludos.Games.Surge.Objects.Obstacles.OneWayPlatform;
import com.vetche.codename_ludos.Games.Surge.Objects.Obstacles.SolidObject;
import com.vetche.codename_ludos.Games.Surge.Objects.Obstacles.SupremeStore;
import com.vetche.codename_ludos.Games.Surge.Objects.SurgeEntity;
import com.vetche.codename_ludos.Games.Surge.Surge;
import com.vetche.codename_ludos.LibraryTools.Math.Vector2D;

import java.util.HashMap;

public class PrefabManager {
    private static SpriteMap bg = ArcadeMachine
            .getGame("Surge")
            .getAssetLoader()
            .getAsset("s_bg")
            .asSpriteMap();

    private final ArcadeGame gameRef;
    public HashMap<Integer, Function<Vector2D, SurgeEntity>> entitySpawns = new HashMap<>();
    private Prefab nextPrefab;
    private static PrefabArrays prefabArrays = new PrefabArrays();

    float ox;
    float oy;

    float prefabSpawnRange;
    float prefabSpawnIncrement;

    // Entity spawning enumerator
    enum ES {

    }

    public PrefabManager(ArcadeGame game) {
        gameRef = game;

        ox = 0;
        oy = ArcadeMachine.SCREEN_OFFSET_Y;
        prefabSpawnRange =  prefabSpawnIncrement = oy + ArcadeMachine.SCREEN_HEIGHT / 2f;

        entitySpawns.put(1, (vec) -> new DoubleJump(vec.x + ox, vec.y + oy));
        entitySpawns.put(2, (vec) -> new WallJump(vec.x + ox, vec.y + oy));
        entitySpawns.put(12, (vec) -> new WallClimb(vec.x + ox, vec.y + oy));
        entitySpawns.put(3, (vec) -> new OneWayPlatform(vec.x + ox, vec.y + oy));
        entitySpawns.put(4, (vec) -> new SolidObject(vec.x + ox, vec.y + oy));
        entitySpawns.put(5, (vec) -> new SupremeStore(vec.x + ox, vec.y + oy));

        // Test versions
        entitySpawns.put(6, (vec) -> new SolidObject(vec.x + ox, vec.y + oy, Surge.TILE_SIZE, 6 * Surge.TILE_SIZE));
        entitySpawns.put(7, (vec) -> new SolidObject(vec.x + ox, vec.y + oy, Surge.TILE_SIZE * 3, 2 * Surge.TILE_SIZE));
        entitySpawns.put(8, (vec) -> new SolidObject(vec.x + ox, vec.y + oy, Surge.TILE_SIZE, 3 * Surge.TILE_SIZE));
        entitySpawns.put(9, (vec) -> new SolidObject(vec.x + ox, vec.y + oy, Surge.TILE_SIZE * 3, 6 * Surge.TILE_SIZE));
        entitySpawns.put(10, (vec) -> new SolidObject(vec.x + ox, vec.y + oy, Surge.TILE_SIZE * 5, Surge.TILE_SIZE));
        entitySpawns.put(11, (vec) -> new SolidObject(vec.x + ox, vec.y + oy, Surge.TILE_SIZE * 2, Surge.TILE_SIZE));

        setNextPrefab(new Prefab(0, 0, gameRef));
        scanArrayAndSpawnEntities();
    }

    public static PrefabArrays getPrefabArrays() {
        return prefabArrays;
    }

    public void setNextPrefab(Prefab nextPrefab) {
        this.nextPrefab = nextPrefab;
    }

    public void scanArrayAndSpawnEntities() {
        for (int i = 0; i < nextPrefab.size(); i++) {
            for (int j = 0; j < nextPrefab.get(i).size(); j++) {
                int tile = nextPrefab.get(i).get(j);
                if (tile != 0) {
                    try {
                        gameRef.spawnEntity(entitySpawns.get(tile).apply(
                                new Vector2D(j * nextPrefab.getTileSize(), i * nextPrefab.getTileSize() + nextPrefab.getOffset().y)));
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        Log.i("LudosLog", "NullPointerException caught at PrefabManager.scanArrayAndSpawnEntities with tile ID " + tile);
                    }
                }
            }
        }
    }

    public void update() {
        if (Surge.player.mPos.y < prefabSpawnRange) {
            int i = (int) (Math.random() * prefabArrays.arrays.length);
            nextPrefab.setArray(prefabArrays.arrays[i]);
            prefabSpawnRange -= nextPrefab.getMapHeight();
            nextPrefab.setOffset(0, nextPrefab.getOffset().y - nextPrefab.getMapHeight());
            scanArrayAndSpawnEntities();
        }
    }

    public void draw() {
       bg.drawAt("full", 0, ArcadeMachine.SCREEN_OFFSET_Y, 1080, 1920);
    }
}
