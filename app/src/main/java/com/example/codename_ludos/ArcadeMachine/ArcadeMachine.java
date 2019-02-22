package com.example.codename_ludos.ArcadeMachine;

import android.util.Log;
import android.view.MotionEvent;

import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Games.TestGame.TestGame;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.R;

import java.util.HashMap;

public class ArcadeMachine {
    // Static class that holds all ArcadeGame instances

    private static HashMap<String, ArcadeGame> games;
    private static String currentGameID = "Eggrun";
    private static SpriteMap arcadeImage;

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static int SCREEN_OFFSET_X;
    public static int SCREEN_OFFSET_Y;

    private static int rawScreenOffsetX = 9;
    private static int rawScreenOffsetY = 16;

    private static int rawScreenWidth = 90;
    private static int rawScreenHeight = 105;

    private static float relativeWidthFactor = 90.f / 108.f;
    private static float relativeHeightFactor = 105.f / 192.f;

    public static void calibrateScreen() {
       // relativeWidthFactor = 1 + (float)rawScreenWidth / (float)Constants.SCREEN_WIDTH ;
       // relativeHeightFactor = 1 + (float)rawScreenHeight / (float)Constants.SCREEN_HEIGHT;

        SCREEN_WIDTH = (int)((float)Constants.SCREEN_WIDTH * relativeWidthFactor);
        SCREEN_HEIGHT = (int)((float)Constants.SCREEN_HEIGHT * relativeHeightFactor);

        SCREEN_OFFSET_X = (int)((float)rawScreenOffsetX * (1 + relativeWidthFactor));
        SCREEN_OFFSET_Y = (int)((float)rawScreenOffsetY * (1 + relativeHeightFactor));
    }

    private static void enterGame(String gameID) {
        currentGameID = gameID;
        games.get(currentGameID).start();
        // TODO: Add user friendly ways to stop a game
    }

    private static void exitGame() {
        // TODO: Add a way to stop a game and save its changes
    }

    // Works like a constructor for this static class
    public static void initialize() {
        games = new HashMap<>();
        arcadeImage = new SpriteMap(R.drawable.maskin, Constants.SCREEN_WIDTH , Constants.SCREEN_HEIGHT);
        arcadeImage.bindSprite("all", 0, 0, 108, 192);
        games.put("TestGame", new TestGame());

        games.get("TestGame").start();
        //games.get(currentGameIndex).setup();
    }

    public static void draw() {
        games.get(currentGameID).draw();
        arcadeImage.drawAt("all", 0, 0, Constants.SCREEN_WIDTH , Constants.SCREEN_HEIGHT);
        games.get(currentGameID).controls.draw();
    }

    public static void update() {
        if (games.get(currentGameID).isStarted())
            games.get(currentGameID).update();
    }

    public static void touchEventHandle(MotionEvent event) {
        games.get(currentGameID).onTouchEvent(event);
    }
}
