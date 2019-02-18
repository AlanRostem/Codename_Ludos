package com.example.codename_ludos.Core;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.example.codename_ludos.LibraryTools.Constants;

public class MainActivity extends Activity {

    public static GamePanel gamePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create our window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        gamePanel = new GamePanel(this);
        setContentView(gamePanel);

        // Set maximum FPS for main thread
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        Constants.SCREEN_WIDTH = point.x;
        Constants.SCREEN_HEIGHT = point.y;
        MainThread.MAX_FPS = (int)display.getRefreshRate();
    }
}