package com.example.codename_ludos.Games.Surge;

import android.graphics.Color;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Games.Surge.Objects.Prefab.PrefabManager;
import com.example.codename_ludos.Games.Surge.Objects.SurgeEntity;
import com.example.codename_ludos.Games.Surge.Objects.SurgeGummyCash;
import com.example.codename_ludos.UserInterface.Controllers.Button;

public class Surge extends ArcadeGame {

    public static Player player;

    public PrefabManager prefabManager;

    public static Camera camera = new Camera(0, 0);

    public static final int TILE_SIZE = 64;


    public Surge() {
        super("Surge");

    }

    @Override
    public void setup() {
        makeButtons();
        SurgeEntity.initializeSpriteMap();

        spawnEntity(new SurgeGummyCash(600, 320));
        prefabManager = new PrefabManager(this);

        player = new Player();
        spawnEntity(player);
    }

    @Override
    public void update() {
        prefabManager.update();
        if (getEntityList().size() < 20) {
            // TODO: Implement an algorithm to spawn entities above the player
        }
        updateEntities();
    }

    @Override
    public void draw() {
        prefabManager.draw();
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

        controls.createController("left", new Button(controls, "left", 50 , 1500, 200, 200) {
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
        controls.createController("right", new Button(controls, "right",250, 1500, 200, 200) {
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