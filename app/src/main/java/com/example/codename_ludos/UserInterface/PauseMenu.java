package com.example.codename_ludos.UserInterface;

import android.graphics.Color;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Assets.Graphics.TextDrawer;
import com.example.codename_ludos.UserInterface.Controllers.Button;
import com.example.codename_ludos.UserInterface.Controllers.Controls;

public class PauseMenu extends Controls {

    ArcadeGame myGame;

    public PauseMenu(ArcadeGame game) {
        myGame = game;
        // TODO: Make a real button dude
        createController("returnToArcadeMachine", new Button(this, "returnToArcadeMachine",100 + 900/2 - 700/2, 320 + 50, 700, 100) {
            public void draw() {
                Shapes.setColor(Color.argb(1f, 1f, 0f, 1f));
                Shapes.drawRect(this.outPutPos.x, this.outPutPos.y, this.getWidth(), this.getHeight());
                TextDrawer.draw("Return to menu", Color.WHITE, 60, this.outPutPos.x, this.outPutPos.y);
            }

            public void onPressed(float x, float y) {
                ArcadeMachine.exitGame();
            }
        });
        // TODO: Make proper user interface bases
    }

    public void update() {
        updatePos();
        if (myGame.isPaused()) {
            super.update();
        }
    }

    public void draw() {
        if (myGame.isPaused()) {
            Shapes.setColor(Color.argb(0.5f, 1f, 1f, 1f));
            Shapes.drawRect(100, 320, 900, 500);
            super.draw();
        }
    }
}
