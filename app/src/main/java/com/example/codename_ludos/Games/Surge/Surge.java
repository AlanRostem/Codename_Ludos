package com.example.codename_ludos.Games.Surge;

import android.graphics.Color;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Games.Surge.Objects.Obstacles.Slope;
import com.example.codename_ludos.Games.Surge.Objects.Prefab.PrefabManager;
import com.example.codename_ludos.Games.Surge.Objects.SurgeEntity;
import com.example.codename_ludos.Games.Surge.Objects.SurgeGummyCash;
import com.example.codename_ludos.R;
import com.example.codename_ludos.UserInterface.Controllers.Button;

public class Surge extends ArcadeGame {

    public static Player player;

    public PrefabManager prefabManager;

    public static Camera camera = new Camera(0, 0);

    public static final int TILE_SIZE = 48;

    public Surge() {
        super("Surge", AMShowType.cut_frame);
    }

    @Override
    public void setup() {
        assetLoader.queueBitmapToLoad("s_rubigo", R.drawable.rubigo);
        assetLoader.queueBitmapToLoad("s_surge_world", R.drawable.surge_world);
        assetLoader.queueBitmapToLoad("s_bg", R.drawable.bg);
        assetLoader.queueSoundToLoad("a_jump", R.raw.jump_test);
        assetLoader.createAllQueuedAssets();

        makeButtons();

        spawnEntity(new SurgeGummyCash(600, 320));
        prefabManager = new PrefabManager(this);

        player = new Player();
    }

    @Override
    public void update() {
        prefabManager.update();
        updateEntities();
        player.update();
    }

    @Override
    public void draw() {
        prefabManager.draw();
        drawEntities();
        player.draw();
    }

    @Override
    public void onExit() {
        camera = new Camera(0, 0);
    }

    private void makeButtons() {
        controls.createController("jump", new Button(controls, "jump", 550, 1000, 150, 150) {
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

        controls.createController("left", new Button(controls, "left", 20 , 1000, 150, 150) {
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
        controls.createController("right", new Button(controls, "right",20 + 150, 1000, 150, 150) {
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