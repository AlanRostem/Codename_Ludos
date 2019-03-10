package com.example.codename_ludos.UserInterface.Controllers;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.UserInterface.Finger;
import com.example.codename_ludos.UserInterface.UIBody;
import com.example.codename_ludos.UserInterface.UIContainer;

import java.util.ArrayList;
import java.util.HashMap;

public class Controls extends UIBody {
    // Holds all Touchable instances such as buttons and joysticks
    // each instance belongs to ArcadeGame as a member

    public Controls() {
        super("controls", 0, 0);
    }

    public void createController(String name, Touchable c) {
        append(name, c);
    }

    public boolean isTouched(String name) {
        try {
            for (Finger pos : MainActivity.gamePanel.getFingers()) {
                if (((Touchable)childMap.get(name)).isTouched(pos)) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }
}
