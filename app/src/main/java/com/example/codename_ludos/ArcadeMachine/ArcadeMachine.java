package com.example.codename_ludos.ArcadeMachine;

import android.view.MotionEvent;

import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Games.TestGame.TestGame;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArcadeMachine {
    // Static class that holds all ArcadeGame instances

    private static Map<String, ArcadeGame> games;
    private static String currentGameID = "TestGame";
    private static SpriteMap arcadeImage;

    // Works like a constructor for this static class
    public static void initialize() {
        games = new HashMap<>();
        arcadeImage = new SpriteMap(R.drawable.maskin, Constants.SCREEN_WIDTH , Constants.SCREEN_HEIGHT);

        games.put("TestGame", new TestGame());
        games.get("TestGame").start();
        //games.get(currentGameIndex).setup();
    }

    public static void draw() {
        games.get(currentGameID).draw();
        arcadeImage.drawFull(0, 0);
    }

    public static void update() {
        if (games.get(currentGameID).isStarted())
            games.get(currentGameID).update();
    }

    public static void touchEventHandle(MotionEvent event) {
        games.get(currentGameID).onTouchEvent(event);
    }
}
