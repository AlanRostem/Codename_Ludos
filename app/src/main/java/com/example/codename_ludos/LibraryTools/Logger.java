package com.example.codename_ludos.LibraryTools;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.Core.MainThread;

public class Logger {
    private static boolean debug = false;

    public static void enableDebugStats(boolean on) {
        debug = on;
    }

    public static void draw() {
        if (debug) {
            GamePanel.paint.setColor(Color.GREEN);
            GamePanel.paint.setStyle(Paint.Style.FILL);
            GamePanel.paint.setTextSize(50);
            MainThread.canvas.drawText("FPS: " + MainThread.getAverageFPS(), 5, 50, GamePanel.paint);
            MainThread.canvas.drawText("dT: " + MainThread.getAverageDeltaTime() * 1000
                    + "ms", 5, 50*2, GamePanel.paint);
            MainThread.canvas.drawText("ptrCnt: " + GamePanel.ptrCnt, 5, 3*50, GamePanel.paint);
        }
    }
}