package com.example.codename_ludos.ArcadeMachine;

import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.Games.Eggrun.Eggrun;
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

    private static int rawImageWidth;
    private static int rawImageHeight;

    private static float relativeWidthFactor;
    private static float relativeHeightFactor;

    public static void calibrateScreen() {
        SCREEN_WIDTH = (int)((float)Constants.SCREEN_WIDTH * relativeWidthFactor);
        SCREEN_HEIGHT = (int)((float)Constants.SCREEN_HEIGHT * relativeHeightFactor);

        // Asså eg vet da faen
        SCREEN_OFFSET_X = (int)((float)rawScreenOffsetX * (SCREEN_WIDTH / rawScreenWidth));
        SCREEN_OFFSET_Y = (int)((float)rawScreenOffsetY * (SCREEN_HEIGHT / rawScreenHeight));
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
        arcadeImage = new SpriteMap(R.drawable.maskin);
        arcadeImage.bindSprite("all", 0, 0, 108, 192);

        games.put("TestGame", new TestGame());
        games.put("Eggrun", new Eggrun());
        enterGame("TestGame");

        rawImageWidth = arcadeImage.getImageWidth();
        rawImageHeight = arcadeImage.getImageHeight();
        relativeWidthFactor = (float)rawScreenWidth / (float)rawImageWidth;
        relativeHeightFactor = (float)rawScreenHeight / (float)rawImageHeight;

        //games.get(currentGameIndex).setup();
    }

    public static void draw() {
        games.get(currentGameID).draw();
        arcadeImage.drawAt("all", 0, 0, Constants.SCREEN_WIDTH , Constants.SCREEN_HEIGHT);
        games.get(currentGameID).controls.draw();

       // GamePanel.paint.setColor(Color.argb(0.5f, 1f, 1f, 1f));
       // MainThread.canvas.drawRect(new Rect(SCREEN_OFFSET_X, SCREEN_OFFSET_Y, SCREEN_OFFSET_X + SCREEN_WIDTH, SCREEN_OFFSET_Y + SCREEN_HEIGHT), GamePanel.paint);
    }

    public static void update() {
        if (games.get(currentGameID).isStarted())
            games.get(currentGameID).update();
    }

    public static void touchEventHandle(MotionEvent event) {
        games.get(currentGameID).onTouchEvent(event);
    }
}
