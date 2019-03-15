package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.Entity.BaseGummyCash;
import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.UserInterface.Controllers.Button;

public class Eggrun extends ArcadeGame {
    private Ali ali;
    private GameMap map;

    public Eggrun(){ super("Eggrun"); }

    @Override
    public void setup() {
        ali = new Ali();
        map = new GameMap();

        EggrunEntity.gameMap = map;
        spawnEntity(ali);

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

    float seconds = 10;

    @Override
    public void update() {
        seconds += MainThread.getAverageDeltaTime();
        if (seconds > 10f) {
            seconds = 0;
            BaseGummyCash cash = new BaseGummyCash(ArcadeMachine.SCREEN_OFFSET_X + ArcadeMachine.SCREEN_WIDTH,
                    ArcadeMachine.SCREEN_OFFSET_Y + 300 * (float)Math.random());
            cash.mVel.x = -70;
            Log.i("GUMMY", "" + cash.mPos.x + "," + cash.mPos.y);
            spawnEntity(cash);
        }

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
