package com.example.codename_ludos.ArcadeMachine;

import android.graphics.Color;

import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Controllers.Button;
import com.example.codename_ludos.Controllers.Controls;

public class PauseMenu {

    Controls controls;
    ArcadeGame myGame;

    public PauseMenu(ArcadeGame game) {
        controls = new Controls();
        myGame = game;
        // TODO: Make a real button dude
        controls.createController("returnToArcadeMachine", new Button(100 + 900/2 - 700/2, 320 + 50, 700, 100) {
            public void draw() {
                Shapes.setColor(Color.argb(1f, 1f, 0f, 1f));
                Shapes.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
            }

            public void onPressed(float x, float y) {
                ArcadeMachine.exitGame();
            }
        });
        // TODO: Make proper user interface bases
    }

    public void update() {
        if (myGame.isPaused()) {
            controls.update();
        }
    }

    public void draw() {
        if (myGame.isPaused()) {
            Shapes.setColor(Color.argb(0.5f, 1f, 1f, 1f));
            Shapes.drawRect(100, 320, 900, 500);
            controls.draw();
        }
    }
}
