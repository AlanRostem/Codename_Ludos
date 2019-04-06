package com.vetche.codename_ludos.Core;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.vetche.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.vetche.codename_ludos.Assets.Audio.Music;
import com.vetche.codename_ludos.LibraryTools.Constants;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {

    public static GamePanel gamePanel;
    public static SoundPool soundPool;
    public static boolean soundLoaded = false;
    public static ArrayList<Music> songList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create our window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        gamePanel = new GamePanel(this);
        setContentView(gamePanel);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = /*new SoundPool(6, AudioManager.STREAM_MUSIC, 0); */new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                soundLoaded = true;
            }
        });

        // Set maximum FPS for main thread
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        Constants.SCREEN_WIDTH = point.x;
        Constants.SCREEN_HEIGHT = point.y;
        MainThread.MAX_FPS = (int)display.getRefreshRate();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        if (ArcadeMachine.getCurrentGame().isStarted()) {
            ArcadeMachine.getCurrentGame().togglePause();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        ArcadeMachine.getCurrentGame().togglePause();
    }
}