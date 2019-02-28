package com.example.codename_ludos.Games.TestGame;

import android.graphics.Color;
import android.service.quicksettings.Tile;
import android.view.MotionEvent;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.Assets.Shapes;
import com.example.codename_ludos.Audio.Music;
import com.example.codename_ludos.Controllers.Button;
import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.R;

public class TestGame extends ArcadeGame {
    private Dude dude;

    private Music song;
    private TileMap tm;

    public TestGame() {
        super("TestGame");
    }

    @Override
    public void setup() {

        dude = new Dude();
        song = new Music(R.raw.music);
        tm = new TileMap(32);

        controls.createController("jump", new Button( 800, 1500, 200, 200) {
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
                Shapes.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
            }
        });
        controls.createController("left", new Button( 140 , 1500, 100, 100) {
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
                Shapes.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
            }
        });
        controls.createController("right", new Button(250, 1500, 100, 100) {
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
                Shapes.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
            }
        });

    }

    @Override
    public void update() {
        song.play();
        controls.update();
        dude.update();
    }

    @Override
    public void draw() {
        dude.draw();
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
