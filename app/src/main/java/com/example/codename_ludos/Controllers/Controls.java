package com.example.codename_ludos.Controllers;

import java.util.ArrayList;

public class Controls extends ArrayList<Controller> {
    // Holds all Controller instances such as buttons and joysticks
    // each instance belongs to ArcadeGame as a member
    public Controls() {

    }

    public void createController(Controller c) {
        this.add(c);
    }

    public void update() {
        for (Controller c : this) {
            c.update();
        }
    }

    public void draw() {
        for (Controller c : this) {
            c.draw();
        }
    }
}
