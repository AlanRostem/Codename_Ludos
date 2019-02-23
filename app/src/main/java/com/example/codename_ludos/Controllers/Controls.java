package com.example.codename_ludos.Controllers;

import android.util.Log;

import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.Input.Finger;

import java.util.ArrayList;
import java.util.HashMap;

public class Controls {
    // Holds all Controller instances such as buttons and joysticks
    // each instance belongs to ArcadeGame as a member
    private HashMap<String, Controller> checkMap;
    private ArrayList<Controller> container;

    public Controls() {
        super();
        checkMap = new HashMap<>();
        container = new ArrayList<>();
    }

    public void createController(String name, Controller c) {
        container.add(c);
        checkMap.put(name, c);
    }

    public boolean isTouched(String name) {
        try {
            for (Finger pos : MainActivity.gamePanel.getFingers()) {
                if (checkMap.get(name).isTouched(pos)) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    public void update() {
        for (Controller c : container) {
            c.update();
        }
    }

    public void draw() {
        for (Controller c : container) {
            c.draw();
        }
    }
}
