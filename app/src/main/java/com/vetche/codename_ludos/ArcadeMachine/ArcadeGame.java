package com.vetche.codename_ludos.ArcadeMachine;

import android.view.MotionEvent;

import com.vetche.codename_ludos.Assets.AssetLoader;
import com.vetche.codename_ludos.Assets.Audio.Music;
import com.vetche.codename_ludos.UserInterface.Controllers.Controls;
import com.vetche.codename_ludos.Entity.EntityManager;
import com.vetche.codename_ludos.ArcadeMachine.UI.PauseMenu;

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
    protected AMShowType showType = AMShowType.none;

    public enum AMShowType {
        none,
        full,
        cut_frame
    }

    // Upon extending this class make sure to create a public
    // static variable for the id
    public ArcadeGame(String ID, AMShowType showType) {
        this.id = ID;
        controls = new Controls();
        pauseMenu = new PauseMenu(this);
        songList = new ArrayList<>();
        assetLoader = new AssetLoader();
        this.showType = showType;
    }

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
            assetLoader.getSoundPool().autoPause();
            for (Music m : songList) {
                m.pause();
            }
        } else {
            for (Music m : songList) {
                m.play();
            }
            assetLoader.getSoundPool().autoResume();
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
        draw();
        if (mPaused) {
            pauseMenu.draw();
        }
        ArcadeMachine.drawMachine(showType);
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
