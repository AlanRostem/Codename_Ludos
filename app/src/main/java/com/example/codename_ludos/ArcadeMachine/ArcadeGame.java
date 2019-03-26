package com.example.codename_ludos.ArcadeMachine;

import android.graphics.Color;
import android.view.MotionEvent;

import com.example.codename_ludos.Assets.AssetLoader;
import com.example.codename_ludos.Assets.Audio.Music;
import com.example.codename_ludos.Assets.Graphics.TextDrawer;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.UserInterface.Controllers.Controls;
import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.Entity.EntityManager;
import com.example.codename_ludos.UserInterface.PauseMenu;

import java.util.ArrayList;

public class ArcadeGame extends EntityManager {
    // Subclass for each arcade game that we develop

    private boolean mStarted = false;
    private boolean mPaused = false;
    private String id;
    protected Controls controls;
    protected PauseMenu pauseMenu;
    protected ArrayList<Music> songList;
    protected AssetLoader assetLoader;

    // Upon extending this class make sure to create a public
    // static variable for the id
    public ArcadeGame(String ID) {
        this.id = ID;
        controls = new Controls();
        pauseMenu = new PauseMenu(this);
        songList = new ArrayList<>();
        assetLoader = new AssetLoader();
    }

    public String getID() {
        return id;
    }

    public void start() {
        mStarted = true;
        setup();
        //Log.i("Game started", id);
    }

    public AssetLoader getAssetLoader() {
        return assetLoader;
    }

    public void onExit() {

    }

    public void exit() {
        mStarted = false;
        mPaused = false;
        for (Music m : songList) {
            m.stop();
        }
        onExit();
        clearEntities();
        assetLoader.recycleAll();
    }

    public void onPaused() {

    }

    public void togglePause() {
        mPaused = !mPaused;
        if (mPaused) {
            MainActivity.soundPool.autoPause();
            for (Music m : songList) {
                m.pause();
            }
        } else {
            for (Music m : songList) {
                m.play();
            }
            MainActivity.soundPool.autoResume();
        }
        onPaused();
    }

    public void createSong(Music song) {
        songList.add(song);
    }

    public boolean isPaused() {
        return mPaused;
    }

    public boolean isStarted() {
        return mStarted;
    }

    /**
     * Upon starting a game, this function is called. Use this to set up the game.
     * This includes member initializations and calling other methods for things
     * like animations or intros the game may or may not have.
     */
    public void setup() {

    }

    public void coreDraw() {
        while (!assetLoader.areAllAssetsLoaded()) {
            TextDrawer.draw("Loading...",
                    Color.RED, 200,
                    ArcadeMachine.SCREEN_OFFSET_X,
                    ArcadeMachine.SCREEN_OFFSET_Y);
        }
        draw();
        if (mPaused) {
            pauseMenu.draw();
        }
    }

    public void draw() {

    }

    public void coreUpdate() {
        if (!mPaused) {
            update();
        } else {
            pauseMenu.update();
        }
    }

    public void update() {

    }

    public void onTouchEvent(MotionEvent event) {

    }

    public Controls getControls() {
        return controls;
    }
}
