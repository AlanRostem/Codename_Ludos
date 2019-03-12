package com.example.codename_ludos.ArcadeMachine;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.Assets.Graphics.Text;
import com.example.codename_ludos.UserInterface.Controllers.Button;
import com.example.codename_ludos.UserInterface.Controllers.Controls;
import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.Games.Eggrun.Eggrun;
import com.example.codename_ludos.Games.Surge.Surge;
import com.example.codename_ludos.Games.TestGame.TestGame;
import com.example.codename_ludos.Games.Lodestone.Lodestone;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.R;

import java.util.ArrayList;
import java.util.HashMap;


public class ArcadeMachine {
    // Static class that holds all ArcadeGame instances

    private static HashMap<String, ArcadeGame> games;
    private static ArrayList<String> gameIDList;
    private static String currentGameID = "Eggrun";
    private static Controls controls = new Controls();
    public static SpriteMap arcadeImage;

    public enum MachineState {
        in_game,
        in_game_select,
        in_settings
    }

    private static MachineState currentState = MachineState.in_game_select;

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static int SCREEN_OFFSET_X;
    public static int SCREEN_OFFSET_Y;

    private static int rawScreenOffsetX = 90;
    private static int rawScreenOffsetY = 160;

    private static int rawScreenWidth = 900;
    private static int rawScreenHeight = 1200;

    public static int rawImageWidth;
    public static int rawImageHeight;

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
        setCurrentState(MachineState.in_game);
        games.get(currentGameID).start();
        Log.i("Game entered", gameID);
    }

    public static void exitGame() {
        setCurrentState(MachineState.in_game_select);
        getCurrentGame().exit();
        // TODO: Add a way to stop a game and save its changes
    }

    public static MachineState getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(MachineState val) {
        currentState = val;
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
        createGame("Lodestone", new Lodestone());

        for (String n : getGameIDList()) {
            final String N = n;
            controls.createController(n, new Button(controls, n, 240, 250 + getGameIDList().indexOf(n) * 200, 600, 100) {
                int color = Color.WHITE;

                @Override
                public void onHolding(float eventX, float eventY) {
                    color = Color.YELLOW;
                }

                @Override
                public void onReleased(float eventX, float eventY) {
                    color = Color.WHITE;
                }

                public void onClick(float x, float y) {
                    if (!getCurrentGame().isStarted()) {
                        ArcadeMachine.enterGame(N);
                    }
                }

                public void draw() {
                    Shapes.setColor(Color.BLUE);
                    Shapes.drawRect(this.pos.x, this.pos.y, this.getWidth(), this.getHeight());
                    Text.draw(N, color, 60, this.pos.x, this.pos.y);
                }
            });
        }

        rawImageWidth = arcadeImage.getImageWidth();
        rawImageHeight = arcadeImage.getImageHeight();
        relativeWidthFactor = (float)rawScreenWidth / (float)rawImageWidth;
        relativeHeightFactor = (float)rawScreenHeight / (float)rawImageHeight;

        //games.get(currentGameIndex).setup();
    }

    public static void draw() {
        if (games.get(currentGameID).isStarted() && getCurrentState() == MachineState.in_game) {
            games.get(currentGameID).coreDraw();
            arcadeImage.drawAt("all", 0, 0, ArcadeMachine.rawImageWidth, ArcadeMachine.rawImageHeight);
            games.get(currentGameID).controls.draw();
        } else if (getCurrentState() == MachineState.in_game_select) {
            arcadeImage.drawAt("all", 0, 0, ArcadeMachine.rawImageWidth, ArcadeMachine.rawImageHeight);
            controls.draw();
        }
    }

    public static void update() {
        if (games.get(currentGameID).isStarted() && getCurrentState() == MachineState.in_game) {
            games.get(currentGameID).controls.update();
            games.get(currentGameID).coreUpdate();
        } else if (getCurrentState() == MachineState.in_game_select) {
            controls.update();
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
