package com.vetche.codename_ludos.ArcadeMachine;

import android.util.Log;
import android.view.MotionEvent;

import com.vetche.codename_ludos.ArcadeMachine.UI.GameButton;
import com.vetche.codename_ludos.Assets.Graphics.SpriteMap;
import com.vetche.codename_ludos.Games.Eggrun.Eggrun;
import com.vetche.codename_ludos.Games.MountainRun.MountainRun;
import com.vetche.codename_ludos.Games.Starbit.Starbit;
import com.vetche.codename_ludos.UserInterface.Controllers.Button;
import com.vetche.codename_ludos.Games.Surge.Surge;
import com.vetche.codename_ludos.Games.TestGame.TestGame;
import com.vetche.codename_ludos.Games.Lodestone.Lodestone;
import com.vetche.codename_ludos.LibraryTools.Constants;
import com.vetche.codename_ludos.R;
import com.vetche.codename_ludos.UserInterface.Elements.ScrollList;
import com.vetche.codename_ludos.UserInterface.UIContainer;
import com.vetche.codename_ludos.UserInterface.UIElement;

import java.util.ArrayList;
import java.util.HashMap;


public class ArcadeMachine {
    // Static class that holds all ArcadeGame instances

    private static HashMap<String, ArcadeGame> games;
    private static ArrayList<String> gameIDList;
    private static String currentGameID = "Surge";
    private static ScrollList gameButtons;

    public static SpriteMap arcadeImage;
    public static SpriteMap arcadeImage2;

    public enum MachineState {
        in_game,
        in_game_select,
        in_settings
    }

    private static MachineState currentState = MachineState.in_game_select;

    private static UIContainer uiElements = new UIContainer("uiElements", 0, 0, 0, 0);
    private static SpriteMap uiButtons = new SpriteMap(R.drawable.ui_buttons);
    private static Button pause = new Button(uiElements, "pause", 20, 20, 68, 68) {
        @Override
        public void onClick(float eventX, float eventY) {
            ArcadeMachine.getCurrentGame().togglePause();
        }

        @Override
        public void draw() {
            uiButtons.drawAt(this.getID(), this.getOutPutPos().x, this.getOutPutPos().y, this.getWidth(), this.getHeight());
        }
    };
    private static Button settings = new Button(uiElements, "settings", 20 + 10 + 80, 20, 68, 68) {
        @Override
        public void draw() {
            uiButtons.drawAt(this.getID(), this.getOutPutPos().x, this.getOutPutPos().y, this.getWidth(), this.getHeight());
        }
    };

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static int SCREEN_OFFSET_X;
    public static int SCREEN_OFFSET_Y;

    private static int rawScreenOffsetX = 60;
    private static int rawScreenOffsetY = 108;

    private static int rawScreenWidth = 600;
    private static int rawScreenHeight = 800;

    public static int rawImageWidth;
    public static int rawImageHeight;

    private static float relativeWidthFactor;
    private static float relativeHeightFactor;


    public static void calibrateScreen() {
        SCREEN_WIDTH = rawScreenWidth;//(int)((float)Constants.SCREEN_WIDTH * relativeWidthFactor);
        SCREEN_HEIGHT = rawScreenHeight;//(int)((float)Constants.SCREEN_HEIGHT * relativeHeightFactor);

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
        arcadeImage2 = new SpriteMap(R.drawable.maskin_two);
        arcadeImage.bindSprite("all", 0, 0, 1080, 1920);
        arcadeImage2.bindSprite("all", 1080, 0, 1080, 1920);
        gameButtons = new ScrollList("gameButtons", 0,  SCREEN_HEIGHT,
                0, 0, ArcadeMachine.SCREEN_WIDTH, Constants.SCREEN_HEIGHT) {
            @Override
            public void update() {
                super.update();
                if ((int)this.ySpeed != 0) {
                    for (UIElement b : getChildNodes()) {
                        ((Button) b).selected = false;
                    }
                }
            }
        };

        uiButtons.bindSprite("pause", 0, 0, 10, 11);
        uiButtons.bindSprite("settings", 10, 0, 10, 11);

        createGame("Surge", new Surge());
        createGame("MountainRun", new MountainRun());
        createGame("Lodestone", new Lodestone());
        createGame("Starbit", new Starbit());
        createGame("TestGame", new TestGame());

        createGame("Eggrun", new Eggrun());

        for (String n : getGameIDList()) {
            gameButtons.append(n, new GameButton(gameButtons, n, SCREEN_OFFSET_X,
                    SCREEN_OFFSET_Y + getGameIDList().indexOf(n) * GameButton.HEIGHT, ArcadeMachine.SCREEN_WIDTH, GameButton.HEIGHT));
        }

        uiElements.append("pause", pause);
        uiElements.append("settings", settings);

        rawImageWidth = arcadeImage.getImageWidth();
        rawImageHeight = arcadeImage.getImageHeight();
        relativeWidthFactor = (float)rawScreenWidth / (float)rawImageWidth;
        relativeHeightFactor = (float)rawScreenHeight / (float)rawImageHeight;

        //games.get(currentGameIndex).setup();
    }

    public static void drawMachine(ArcadeGame.AMShowType showType) {
        switch (showType) {
            case full:
                arcadeImage.drawAt("all", 0, 0, ArcadeMachine.rawImageWidth, ArcadeMachine.rawImageHeight);
                break;
            case cut_frame:
                arcadeImage2.drawAt("all", 0, 0, ArcadeMachine.rawImageWidth, ArcadeMachine.rawImageHeight);
            case none:
                break;
        }
    }

    public static void draw() {
        if (games.get(currentGameID).isStarted() && getCurrentState() == MachineState.in_game) {
            games.get(currentGameID).coreDraw();
            games.get(currentGameID).controls.draw();
        } else if (getCurrentState() == MachineState.in_game_select) {
            gameButtons.draw();
            arcadeImage.drawAt("all", 0, 0, ArcadeMachine.rawImageWidth, ArcadeMachine.rawImageHeight);
        }
        uiElements.draw();
    }

    public static void update() {
        if (games.get(currentGameID).isStarted() && getCurrentState() == MachineState.in_game) {
            games.get(currentGameID).controls.update();
            games.get(currentGameID).coreUpdate();
        } else if (getCurrentState() == MachineState.in_game_select) {
            gameButtons.update();
        }
        uiElements.update();
    }

    public static ArrayList<String> getGameIDList() {
        return gameIDList;
    }

    public static ArcadeGame getCurrentGame() {
        return games.get(currentGameID);
    }

    public static ArcadeGame getGame(String gameID) {
        return games.get(gameID);
    }

    public static void touchEventHandle(MotionEvent event) {
        games.get(currentGameID).onTouchEvent(event);
    }
}
