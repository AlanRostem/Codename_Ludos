package com.example.codename_ludos.ArcadeMachine;

import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.Games.Eggrun.Eggrun;
import com.example.codename_ludos.Games.GameSelect.GameSelect;
import com.example.codename_ludos.Games.Surge.Surge;
import com.example.codename_ludos.Games.TestGame.TestGame;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ArcadeMachine {
    // Static class that holds all ArcadeGame instances

    private static HashMap<String, ArcadeGame> games;
    private static ArrayList<String> gameIDList;
    private static String currentGameID = "Eggrun";
    private static SpriteMap arcadeImage;

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static int SCREEN_OFFSET_X;
    public static int SCREEN_OFFSET_Y;

    private static int rawScreenOffsetX = 90;
    private static int rawScreenOffsetY = 160;

    private static int rawScreenWidth = 900;
    private static int rawScreenHeight = 1200;

    private static int rawImageWidth;
    private static int rawImageHeight;

    private static float relativeWidthFactor;
    private static float relativeHeightFactor;

    public static void calibrateScreen() {
        SCREEN_WIDTH = rawScreenWidth;//(int)((float)Constants.SCREEN_WIDTH * relativeWidthFactor);
        SCREEN_HEIGHT = rawScreenHeight;// (int)((float)Constants.SCREEN_HEIGHT * relativeHeightFactor);

        // Asså eg vet da faen
        SCREEN_OFFSET_X = rawScreenOffsetX;//(int)((float)rawScreenOffsetX * (SCREEN_WIDTH / rawScreenWidth));
        SCREEN_OFFSET_Y = rawScreenOffsetY;//(int)((float)rawScreenOffsetY * (SCREEN_HEIGHT / rawScreenHeight));

        Constants.SCREEN_SCALE_X = (float)Constants.SCREEN_WIDTH / (float)rawImageWidth;
        Constants.SCREEN_SCALE_Y = (float)Constants.SCREEN_HEIGHT / (float)rawImageHeight;
    }

    private static void createGame(String name, ArcadeGame game) {
        games.put(name, game);
        gameIDList.add(name);
    }

    public static void enterGame(String gameID) {
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
        gameIDList = new ArrayList<>();
        arcadeImage = new SpriteMap(R.drawable.maskin);
        arcadeImage.bindSprite("all", 0, 0, 1080, 1920);

        createGame("TestGame", new TestGame());
        createGame("Eggrun", new Eggrun());
        createGame("Surge", new Surge());

        createGame("GameSelect", new GameSelect());
        enterGame("GameSelect");

        rawImageWidth = arcadeImage.getImageWidth();
        rawImageHeight = arcadeImage.getImageHeight();
        relativeWidthFactor = (float)rawScreenWidth / (float)rawImageWidth;
        relativeHeightFactor = (float)rawScreenHeight / (float)rawImageHeight;

        //games.get(currentGameIndex).setup();
    }

    public static void draw() {
        games.get(currentGameID).draw();
        arcadeImage.drawAt("all", 0, 0, rawImageWidth, rawImageHeight);
        games.get(currentGameID).controls.draw();

       // GamePanel.paint.setColor(Color.argb(0.5f, 1f, 1f, 1f));
       // MainThread.canvas.drawRect(new Rect(SCREEN_OFFSET_X, SCREEN_OFFSET_Y, SCREEN_OFFSET_X + SCREEN_WIDTH, SCREEN_OFFSET_Y + SCREEN_HEIGHT), GamePanel.paint);
    }

    public static void update() {
        if (games.get(currentGameID).isStarted()) {
            games.get(currentGameID).controls.update();
            games.get(currentGameID).update();
        }
    }

    public static ArrayList<String> getGameIDList() {
        return gameIDList;
    }

    public static ArcadeGame getCurrentGame() {
        return games.get(currentGameID);
    }

    public static void touchEventHandle(MotionEvent event) {
        games.get(currentGameID).onTouchEvent(event);
    }
}
