package com.example.codename_ludos.Core;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.codename_ludos.Core.GamePanel;

public class MainThread extends Thread {
    public static int MAX_FPS = 60;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;


    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public static float getDeltaTime() {
        return deltaTime;
    }

    private static float deltaTime;

    @Override
    public void run() {
        long startTime;
        long timeMillis = 1000/MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

       while (running) {
            startTime = System.nanoTime();
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockHardwareCanvas(); // Locks a new canvas with hardware acceleration!
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) { e.printStackTrace(); }
            finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {e.printStackTrace();}
                }
            }

           timeMillis = (System.nanoTime() - startTime)/1000000;
           waitTime = targetTime - timeMillis;
           deltaTime = waitTime / 1000.f;
           try {
                if (waitTime > 0)
                    this.sleep(waitTime);
           } catch (Exception e) { e.printStackTrace();}
           totalTime += System.nanoTime() - startTime;
           frameCount++;
           if (frameCount == MAX_FPS) {
                averageFPS = 1000 / ((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
           }
       }
    }
}