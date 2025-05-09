package com.vetche.codename_ludos.LibraryTools;

import android.graphics.Color;
import android.graphics.Paint;

import com.vetche.codename_ludos.Core.GamePanel;
import com.vetche.codename_ludos.Core.MainThread;

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
            //MainThread.canvas.drawText("dT: " + MainThread.getAverageDeltaTime() * 1000
            //        + "ms", 5, 50*2, GamePanel.paint);
            //MainThread.canvas.drawText("ptrCnt: " + GamePanel.ptrCnt, 5, 50*3, GamePanel.paint);
           /* GamePanel.paint.setColor(Color.MAGENTA);
            MainThread.canvas.drawText("Jobb med spelet Alan!", 5, 50*4, GamePanel.paint);*/
        }
    }
}