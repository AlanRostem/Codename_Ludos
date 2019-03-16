package com.example.codename_ludos.Games.Surge;

import android.graphics.Color;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.Entity.BaseGummyCash;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.Games.Surge.Objects.SurgeGummyCash;
import com.example.codename_ludos.UserInterface.Controllers.Button;
import com.example.codename_ludos.Games.Surge.Objects.Items.DoubleJump;
import com.example.codename_ludos.Games.Surge.Objects.Items.PowerUp;
import com.example.codename_ludos.Games.Surge.Objects.UnderPassObject;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.R;

public class Surge extends ArcadeGame {

    public static Player player;

    public static SpriteMap objects = new SpriteMap(R.drawable.collisionobjects);

    public static Camera camera = new Camera(0, 0);

    private SpriteMap bg;
    private SpriteMap tileSet = new SpriteMap(R.drawable.surge_tiles);

    public Surge() {
        super("Surge");
    }

    @Override
    public void setup() {
        makeButtons();

        bg = new SpriteMap(R.drawable.bg);

        TileManager.tileMap.setOffset(ArcadeMachine.SCREEN_OFFSET_X, ArcadeMachine.SCREEN_OFFSET_Y);

        PowerUp.sprite.bindSprite("doublejump", 0, 0, 20, 20);

        objects.bindSprite("metalWall", 0, 0, 10, 50);
        objects.bindSprite("grassFloor", 10, 0, 90, 12);

        player = new Player();
        spawnEntity(new DoubleJump(320, 320));
        spawnEntity(new SurgeGummyCash(600, 320));
        spawnEntity(player);
    }

    @Override
    public void update() {
        if (getEntityList().size() < 20) {
            // TODO: Implement an algorithm to spawn entities above the player
        }
        updateEntities();
    }

    @Override
    public void draw() {
        bg.drawAt("full", 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        tileSet.drawTileMap(TileManager.tileMap, 32, 6, 0,
                TileManager.tileMap.getOffset().x,
                TileManager.tileMap.getOffset().y);
        drawEntities();
    }

    @Override
    public void onExit() {
        camera = new Camera(0, 0);
    }

    private void makeButtons() {
        controls.createController("jump", new Button(controls, "jump", 800, 1500, 200, 200) {
            private int color = Color.GREEN;

            public void onPressed(float x, float y) {
                color = Color.BLUE;
            }

            public void onReleased(float x, float y) {
                color = Color.GREEN;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(this.outPutPos.x, this.outPutPos.y, this.getWidth(), this.getHeight());
            }
        });
        controls.createController("left", new Button(controls, "left", 140 , 1500, 100, 100) {
            private int color = Color.GREEN;

            public void onHolding(float x, float y) {
                color = Color.BLUE;
            }

            public void onReleased(float x, float y) {
                color = Color.GREEN;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(this.outPutPos.x, this.outPutPos.y, this.getWidth(), this.getHeight());
            }
        });
        controls.createController("right", new Button(controls, "right",250, 1500, 100, 100) {
            private int color = Color.GREEN;

            public void onHolding(float x, float y) {
                color = Color.BLUE;
            }

            public void onReleased(float x, float y) {
                color = Color.GREEN;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(this.outPutPos.x, this.outPutPos.y, this.getWidth(), this.getHeight());
            }
        });
    }
}