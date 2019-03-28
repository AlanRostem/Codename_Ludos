package com.example.codename_ludos.UserInterface;

import android.graphics.Color;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Assets.Graphics.TextDrawer;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.UserInterface.Controllers.Button;
import com.example.codename_ludos.UserInterface.Controllers.Controls;

public class PauseMenu extends Controls {

    ArcadeGame myGame;
    Vector2D pos = new Vector2D(60, 150);
    int width = 600;
    int height = 300;

    public PauseMenu(ArcadeGame game) {
        myGame = game;
        // TODO: Make a real button dude
        createController("returnToArcadeMachine", new Button(this, "returnToArcadeMachine",
                (int)pos.x + width/2 - 500/2, (int)pos.y + height/2 - 100/2, 500, 100) {
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
            Shapes.drawRect(pos.x, pos.y, width, height);
            super.draw();
        }
    }
}
