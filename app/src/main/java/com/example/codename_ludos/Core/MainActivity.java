package com.example.codename_ludos.Core;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.LibraryTools.Constants;

public class MainActivity extends Activity {

    public static GamePanel gamePanel;
    public static SoundPool soundPool;
    public static boolean soundLoaded = false;


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
    public void onBackPressed() {
        ArcadeMachine.getCurrentGame().togglePause();
    }

    @Override
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        ArcadeMachine.getCurrentGame().togglePause();
    }
}