package com.vetche.codename_ludos.Games.TestGame;

import android.graphics.Color;
import android.view.MotionEvent;

import com.vetche.codename_ludos.ArcadeMachine.ArcadeGame;
import com.vetche.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.vetche.codename_ludos.Assets.Graphics.Shapes;
import com.vetche.codename_ludos.Assets.Graphics.SpriteMap;
import com.vetche.codename_ludos.Assets.Audio.Music;
import com.vetche.codename_ludos.LibraryTools.Timer;
import com.vetche.codename_ludos.UserInterface.Controllers.Button;
import com.vetche.codename_ludos.Entity.TileMap;
import com.vetche.codename_ludos.R;

public class TestGame extends ArcadeGame {
    private Dude dude;

    private Music song;
    private TileMap tm;
    private SpriteMap sm;

    public TestGame() {
        super("TestGame");
        song = new Music(R.raw.music, this);
        sm = new SpriteMap(R.drawable.worldtiles);
        controls.createController("jump", new Button( controls, "jump",800, 1500, 200, 200) {
            private int color = Color.GREEN;

            public void onPressed(float x, float y) {
                color = Color.BLUE;
                //dude.jumpNow = true;
            }

            public void onReleased(float x, float y) {
                color = Color.GREEN;
                //dude.jumpNow = false;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(this.outPutPos.x, this.outPutPos.y, this.getWidth(), this.getHeight());
            }
        });
        controls.createController("left", new Button(controls, "left",140, 1500, 100, 100) {
            private int color = Color.GREEN;

            public void onHolding(float x, float y) {
                color = Color.BLUE;
                //dude.addX(speed);
                //dude.right = true;
                //dude.left = false;
                //dude.walk = true;
            }

            public void onReleased(float x, float y) {
                color = Color.GREEN;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(this.outPutPos.x, this.outPutPos.y, this.getWidth(), this.getHeight());
            }
        });

        controls.createController("right", new Button(controls, "left",250, 1500, 100, 100) {
            private int color = Color.GREEN;

            public void onHolding(float x, float y) {
                color = Color.BLUE;
                //dude.addX(-speed);
                //dude.left = true;
                //dude.right = false;
                //dude.walk = true;
            }

            public void onReleased(float x, float y) {
                color = Color.GREEN;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(this.outPutPos.x, this.outPutPos.y, this.getWidth(), this.getHeight());
            }
        });

        controls.createController("release", new Button(controls, "left",(int)(140*3f), 1500, 100, 250) {
            private int color = Color.GREEN;

            public void onHolding(float x, float y) {
                color = Color.BLUE;
                //dude.addX(speed);
                //dude.right = true;
                //dude.left = false;
                //dude.walk = true;
            }

            public void onReleased(float x, float y) {
                color = Color.RED;
            }

            public void draw() {
                Shapes.setColor(color);
                Shapes.drawRect(this.outPutPos.x, this.outPutPos.y, this.getWidth(), this.getHeight());
            }
        });
    }

    Timer metronome = new Timer() {
        @Override
        protected void callback() {
            Dude.keem.play();
        }
    };

    @Override
    public void setup() {

        assetLoader.queueSoundToLoad("a_jump", R.raw.keem);
        assetLoader.createAllQueuedAssets();

        metronome.setBPM(120);

        dude = new Dude();

        //song.play();

        int [][] array = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
        tm = new TileMap(32, array);
    }

    @Override
    public void update() {
        controls.update();
        dude.update();
        metronome.tick();
    }

    @Override
    public void draw() {
        sm.drawTileMap(tm, 3, 0, ArcadeMachine.SCREEN_OFFSET_X, ArcadeMachine.SCREEN_OFFSET_Y,0 ,0);
        dude.draw();
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
