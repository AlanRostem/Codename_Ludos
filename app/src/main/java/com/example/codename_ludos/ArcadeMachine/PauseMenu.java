package com.example.codename_ludos.ArcadeMachine;

import com.example.codename_ludos.Controllers.Button;
import com.example.codename_ludos.Controllers.Controls;

public class PauseMenu {

    Controls controls;
    ArcadeGame myGame;

    public PauseMenu(ArcadeGame game) {
        controls = new Controls();
        myGame = game;
        // TODO: Make a real button dude
        controls.createController("returnToArcadeMachine", new Button(0, 0, 32, 32));
        // TODO: Make proper user interface bases
    }

    public void update() {
        if (myGame.isPaused()) {
            controls.update();
        }
    }

    public void draw() {
        if (myGame.isPaused()) {
            controls.draw();
        }
    }
}
