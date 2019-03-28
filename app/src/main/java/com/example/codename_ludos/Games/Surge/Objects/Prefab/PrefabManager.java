package com.example.codename_ludos.Games.Surge.Objects.Prefab;

import android.arch.core.util.Function;
import android.util.Log;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.Games.Surge.Objects.Items.DoubleJump;
import com.example.codename_ludos.Games.Surge.Objects.Items.WallJump;
import com.example.codename_ludos.Games.Surge.Objects.Obstacles.OneWayPlatform;
import com.example.codename_ludos.Games.Surge.Objects.Obstacles.SolidObject;
import com.example.codename_ludos.Games.Surge.Objects.Obstacles.SupremeStore;
import com.example.codename_ludos.Games.Surge.Objects.Prefab.Prefab;
import com.example.codename_ludos.Games.Surge.Objects.SurgeEntity;
import com.example.codename_ludos.Games.Surge.Surge;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.R;

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

    public PrefabManager(ArcadeGame game) {
        gameRef = game;

        ox = 0;
        oy = ArcadeMachine.SCREEN_OFFSET_Y;
        prefabSpawnRange =  prefabSpawnIncrement = oy + ArcadeMachine.SCREEN_HEIGHT / 2f;

        entitySpawns.put(1, (vec) -> new DoubleJump(vec.x + ox, vec.y + oy));
        entitySpawns.put(2, (vec) -> new WallJump(vec.x + ox, vec.y + oy));
        entitySpawns.put(3, (vec) -> new OneWayPlatform(vec.x + ox, vec.y + oy));
        entitySpawns.put(4, (vec) -> new SolidObject(vec.x + ox, vec.y + oy));
        entitySpawns.put(5, (vec) -> new SupremeStore(vec.x + ox, vec.y + oy));

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
            prefabSpawnRange -= nextPrefab.getMapHeight();
            nextPrefab.setOffset(0, nextPrefab.getOffset().y - nextPrefab.getMapHeight());
            scanArrayAndSpawnEntities();
        }
    }

    public void draw() {
       bg.drawAt("full", 0, ArcadeMachine.SCREEN_OFFSET_Y, Constants.SCREEN_WIDTH, ArcadeMachine.SCREEN_HEIGHT);
    }

}
