package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.Entity.BaseGummyCash;
import com.example.codename_ludos.Entity.EntitySpawner;
import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.UserInterface.Controllers.Button;

import java.util.ArrayList;

public class Eggrun extends ArcadeGame {
    private Ali ali;
    private GameMap map;

    private EntitySpawner birdSpawner;


    public Eggrun() {
        super("Eggrun");
        birdSpawner = new EntitySpawner(this, 0.5f,
            0, ArcadeMachine.SCREEN_HEIGHT) {
            @Override
            public GameEntity create(float x, float y) {
                return new Bird(x, y);
            }
        };

    }

    @Override
    public void setup() {
        ali = new Ali();
        map = new GameMap();

        EggrunEntity.gameMap = map;
        spawnEntity(ali);
        birdSpawner.setSpawnOffset(ArcadeMachine.SCREEN_OFFSET_X + ArcadeMachine.SCREEN_WIDTH, ArcadeMachine.SCREEN_OFFSET_Y);

        controls.createController("Jump", new Button(controls, "Jump",100, 1400, 200, 200){
            private int color = Color.GRAY;

            public void onPressed(float x, float y) {
                color = Color.rgb(190,190,190);
            }

            public void onReleased(float x, float y) {
                color = Color.GRAY;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(outPutPos.x, outPutPos.y, getWidth(), getHeight());
            }
        });

        controls.createController("Slide", new Button(controls, "Slide",400, 1400, 200, 200){
            private int color = Color.GRAY;

            public void onPressed(float x, float y) {
                color = Color.rgb(190,190,190);
            }

            public void onReleased(float x, float y) {
                color = Color.GRAY;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(outPutPos.x, outPutPos.y, getWidth(), getHeight());
            }
        });

        controls.createController("Shoot", new Button(controls, "Shoot",800, 1400, 250, 250){
            private int color = Color.GRAY;

            public void onPressed(float x, float y) {
                color = Color.rgb(190,190,190);
                spawnEntity(new Bullet(ali.getPosition()));
            }

            public void onReleased(float x, float y) {
                color = Color.GRAY;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(outPutPos.x, outPutPos.y, getWidth(), getHeight());
            }
        });

        controls.createController("Spawn", new Button(controls, "Spawn",400, 1700, 100, 100){
            private int color = Color.GRAY;

            public void onPressed(float x, float y) {
                color = Color.rgb(190,190,190);
                spawnEntity(new Egg());
                spawnEntity(new Bird(26*EggrunEntity.gameMap.tileSize, 200));
            }

            public void onReleased(float x, float y) {
                color = Color.GRAY;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(outPutPos.x, outPutPos.y, getWidth(), getHeight());
            }
        });
    }

    @Override
    public void update() {
        float yPos = 0;
        for (ArrayList<Integer> i : EggrunEntity.gameMap.level) {
            if (i.get(i.size()-1) <= 1) {
                yPos += EggrunEntity.gameMap.level.getTileSize();
            }
        }
        birdSpawner.setSpawnRegion(0,
                yPos - 80);

        birdSpawner.update();
        controls.update();
        map.update();
        updateEntities();
    }

    @Override
    public void draw() {
        map.draw();
        drawEntities();
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
