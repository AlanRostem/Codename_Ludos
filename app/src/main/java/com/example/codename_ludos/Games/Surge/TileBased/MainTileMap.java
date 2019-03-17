package com.example.codename_ludos.Games.Surge.TileBased;

import android.arch.core.util.Function;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.Entity.EntityManager;
import com.example.codename_ludos.Entity.GameTile;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.Games.Surge.Objects.Items.DoubleJump;
import com.example.codename_ludos.Games.Surge.Objects.Items.PowerUp;
import com.example.codename_ludos.Games.Surge.Objects.Items.WallJump;
import com.example.codename_ludos.Games.Surge.Player;
import com.example.codename_ludos.Games.Surge.Surge;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.R;

import java.util.HashMap;

public class MainTileMap extends TileMap {
    private SpriteMap bg = new SpriteMap(R.drawable.bg);
    private SpriteMap tileSet = new SpriteMap(R.drawable.surge_tiles);
    private ArcadeGame game;



    public HashMap<Integer, Function<Vector2D, PowerUp>> powerUpSpawns = new HashMap<>();


    private class TileRegion {
        public int min;
        public int max;
        public TileRegion(int a, int b) {
            min = a;
            max = b;
        }

        public boolean isWithinRegion(int tileID) {
            return min <= tileID && tileID <= max;
        }

        public void Xcollision(Player player, GameTile tile) {

        }
        public void Ycollision(Player player, GameTile tile) {

        }
    }

    private TileRegion solid = new TileRegion(10, 12) {

        @Override
        public void Xcollision(Player player, GameTile tile) {
            if (player.mVel.x > 0) {
                if (player.mPos.x + player.width > tile.x(tile.tileSize, (int)getOffset().x)) {
                    player.side.right = true;
                    player.mVel.x = 0;
                    player.mPos.x = tile.x(tile.tileSize, (int)getOffset().x) - player.width;
                }
            }
            if (player.mVel.x < 0) {
                if (player.mPos.x < tile.cx * tile.tileSize + tile.tileSize + (int)getOffset().x) {
                    player.side.left = true;
                    player.mVel.x = 0;
                    player.mPos.x = tile.cx * tile.tileSize + tile.tileSize + (int)getOffset().x;
                }
            }
        }

        @Override
        public void Ycollision(Player player, GameTile tile) {
            if (player.mVel.y > 0) {
                if (player.mPos.y + player.height > tile.y(tile.tileSize, (int)getOffset().y)) {
                    player.side.bottom = true;
                    player.mVel.y = 0;
                    player.onGround();
                    player.mPos.y = tile.y(tile.tileSize, (int)getOffset().y) - player.height;
                }
            }
            if (player.mVel.y < 0) {
                if (player.mPos.y < (float)(tile.y(tile.tileSize, (int)getOffset().y) + tile.tileSize)) {
                    player.side.top = true;
                    player.mVel.y = 0;
                    player.mPos.y = (float)(tile.y(tile.tileSize, (int)getOffset().y) + tile.tileSize);
                }
            }
        }
    };

    private  TileRegion oneWay = new TileRegion(7, 9) {
        private boolean collisionEnabled = true;

        @Override
        public void Xcollision(Player player, GameTile tile) {
            float tx = tile.x(tile.tileSize, (int)getOffset().x);
            float ty = tile.y(tile.tileSize, (int)getOffset().y);
            if (player.mPos.y + player.height < ty + tile.tileSize && player.mVel.y > 0)
                if (player.mVel.x > 0) {
                    if (player.mPos.x + player.width > ty) {
                        collisionEnabled = false;
                    }
                } else if (player.mVel.x < 0) {
                    if (player.mPos.x < tx + tile.tileSize) {
                        collisionEnabled = false;
                    }
                }
        }

        @Override
        public void Ycollision(Player player, GameTile tile) {
            float tx = tile.x(tile.tileSize, (int)getOffset().x);
            float ty = tile.y(tile.tileSize, (int)getOffset().y);

            Vector2D selfA = new Vector2D(tx, ty);
            Vector2D selfB = new Vector2D(tx + tile.tileSize, ty);

            Vector2D playerA1 = new Vector2D(player.mPos.x, player.mPos.y);
            Vector2D playerB1 = new Vector2D(player.mPos.x, player.mPos.y + player.height);

            Vector2D playerA2 = new Vector2D(player.mPos.x, player.mPos.y);
            Vector2D playerB2 = new Vector2D(player.mPos.x + player.width, player.mPos.y + player.height);

            if (player.mVel.y > 0 && collisionEnabled) {
                if (player.mPos.y + player.height - tile.tileSize < ty &&
                        (Vector2D.intersect(selfA, selfB, playerA1, playerB1) || (Vector2D.intersect(selfA, selfB, playerA2, playerB2)))){
                    player.mPos.y = ty - player.height;
                    player.mVel.y = 0;
                    player.onGround();
                }
            } else collisionEnabled = true;
        }
    };

    private TileRegion transparent = new TileRegion(1, 6);

    public MainTileMap(ArcadeGame mgr) {
        super(Surge.TILE_SIZE);

        game = mgr;

        TestPrefab prefab = new TestPrefab(getOffset().x, getOffset().y, game);
        setArray(prefab);

        powerUpSpawns.put(3, (vec) -> { return new DoubleJump(vec.x, vec.y); });
        powerUpSpawns.put(4, (vec) -> { return new WallJump(vec.x, vec.y); });
    }

    public void scanAndSpawnEntities() {
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.get(i).size(); j++) {
                int tile = this.get(i).get(j);
                if (tile >= 3 && tile <= 6) {
                    game.spawnEntity(Surge.tileMap.powerUpSpawns.get(tile).apply(new Vector2D(j * getTileSize(), i * getTileSize())));
                }
            }
        }
    }

    public void callXCollision(GameTile tile, Player player) {
        if (solid.isWithinRegion(tile.ID)) {
            solid.Xcollision(player, tile);
        } else if (oneWay.isWithinRegion(tile.ID)) {
            oneWay.Xcollision(player, tile);
        } else if (transparent.isWithinRegion(tile.ID)) {
            transparent.Xcollision(player, tile);
        }
    }

    public void callYCollision(GameTile tile, Player player) {
        if (solid.isWithinRegion(tile.ID)) {
            solid.Ycollision(player, tile);
        } else if (oneWay.isWithinRegion(tile.ID)) {
            oneWay.Ycollision(player, tile);
        } else if (transparent.isWithinRegion(tile.ID)) {
            transparent.Ycollision(player, tile);
        }
    }

    public void update() {

    }

    public void draw() {
        bg.drawAt("full", 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        tileSet.drawTileMap(this, 32, 6, 0,
                getOffset().x + Surge.camera.x,
                getOffset().y + Surge.camera.y);
    }
}
