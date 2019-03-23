package com.example.codename_ludos.Games.Surge.Objects;

import android.arch.core.util.Function;
import android.util.Log;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.Games.Surge.Objects.Items.DoubleJump;
import com.example.codename_ludos.Games.Surge.Objects.Items.WallJump;
import com.example.codename_ludos.Games.Surge.Objects.Obstacles.OneWayPlatform;
import com.example.codename_ludos.Games.Surge.Objects.Obstacles.SolidObject;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.R;

import java.util.HashMap;

public class PrefabManager {
    private SpriteMap bg = new SpriteMap(R.drawable.bg);
    private SpriteMap tileSet = new SpriteMap(R.drawable.surge_tiles);

    private final ArcadeGame gameRef;
    public HashMap<Integer, Function<Vector2D, SurgeEntity>> entitySpawns = new HashMap<>();
    private Prefab nextPrefab;

    float ox;
    float oy;

    public PrefabManager(ArcadeGame game) {
        gameRef = game;

        ox = ArcadeMachine.SCREEN_OFFSET_X;
        oy = ArcadeMachine.SCREEN_OFFSET_Y;

        entitySpawns.put(1, (vec) -> new DoubleJump(vec.x + ox, vec.y + oy));
        entitySpawns.put(2, (vec) -> new WallJump(vec.x + ox, vec.y + oy));
        entitySpawns.put(3, (vec) -> new OneWayPlatform(vec.x + ox, vec.y + oy));
        entitySpawns.put(4, (vec) -> new SolidObject(vec.x + ox, vec.y + oy));

        setNextPrefab(new Prefab(0, 0, gameRef));
        scanArrayAndSpawnEntities();
    }

    public void setNextPrefab(Prefab nextPrefab) {
        this.nextPrefab = nextPrefab;
    }

    public void scanArrayAndSpawnEntities() {
        for (int i = 0; i < nextPrefab.size(); i++) {
            for (int j = 0; j < nextPrefab.get(i).size(); j++) {
                int tile = nextPrefab.get(i).get(j);
                try {
                    gameRef.spawnEntity(entitySpawns.get(tile).apply(
                            new Vector2D(j * nextPrefab.getTileSize(), i * nextPrefab.getTileSize())));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Log.i("LudosLog", "NullPointerException caught at PrefabManager.scanArrayAndSpawnEntities");
                }
            }
        }
    }

    public void update() {

    }

    public void draw() {
       bg.drawAt("full", 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

}
