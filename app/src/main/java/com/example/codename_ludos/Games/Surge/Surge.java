package com.example.codename_ludos.Games.Surge;

import android.graphics.Color;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Shapes;
import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Controllers.Button;
import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.Games.Surge.Objects.UnderPassObject;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.R;

public class Surge extends ArcadeGame {

    private Player player;

    public static SpriteMap objects = new SpriteMap(R.drawable.collisionobjects);

    public static Camera camera = new Camera(0, 0);

    private SpriteMap bg;

    public Surge() {
        super("Surge");
    }

    @Override
    public void setup() {
        makeButtons();

        bg = new SpriteMap(R.drawable.bg);

        objects.bindSprite("metalWall", 0, 0, 10, 50);
        objects.bindSprite("grassFloor", 10, 0, 90, 12);

        player = new Player();
        spawnEntity(player);
        spawnEntity(new UnderPassObject("grassFloor", 420, 1220, 90*3, 12));
        spawnEntity(new UnderPassObject("grassFloor", 420, 1020, 90*3, 12));
        spawnEntity(new UnderPassObject("grassFloor", 420, 420, 90*3, 12));
    }

    @Override
    public void update() {
        updateEntities();
    }

    @Override
    public void draw() {
        bg.drawAt("full", 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        drawEntities();
    }

    private void makeButtons() {
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
}