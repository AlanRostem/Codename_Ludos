package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;
import android.graphics.Rect;

import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainThread;

import java.util.Random;

public class Map {

    public Map()
    {
        randomize(screen);
        randomize(!screen);
    }

    int tileSize = 40;
    int offSet = 0;

    boolean screen = false;

    int[][] level1 = new int[30][24];
    int[][] level2 = new int[30][24];

    public void randomize(boolean screen)
    {
        if(screen) {
            Random rnd = new Random();
            for (int i = 0; i < level1.length; i++)
                for (int j = 0; j < level1[i].length; j++) {
                    level1[i][j] = rnd.nextInt(2);
                }

            for (int k = 0; k < level1.length; k++)
                for (int l = 0; l < level1[k].length; l++) {
                    if (k <= 24 && k >= 22 && level1[k + 1][l] == 0) {
                        level1[k + 1][l] = 1;
                    }
                }
        }
        else {
            Random rnd = new Random();
            for (int i = 0; i < level2.length; i++)
                for (int j = 0; j < level2[i].length; j++) {
                    level2[i][j] = rnd.nextInt(2);
                }

            for (int k = 0; k < level2.length; k++)
                for (int l = 0; l < level2[k].length; l++) {
                    if (k <= 24 && k >= 22 && level2[k + 1][l] == 0) {
                        level2[k + 1][l] = 1;
                    }
                }
        }
    }

    public void update() {
        offSet-=4;
        if(offSet<=-24*tileSize) {
            offSet = 0;
            screen = !screen;
            randomize(screen);
        }
    }

    public void draw() {
        for(int i = 0; i<level1.length; i++)
            for(int j = 0; j<level1[i].length; j++)
            {
                if(i<22)  GamePanel.paint.setColor(Color.rgb(255,255,255));
                else if(i>24) GamePanel.paint.setColor(Color.rgb(0,0,0));
                else {
                    switch (level1[i][j]) {
                        case 0:
                            GamePanel.paint.setColor(Color.rgb(255, 255, 255));
                            break;
                        case 1:
                            GamePanel.paint.setColor(Color.rgb(0, 0, 0));
                            break;

                        default:
                            break;
                    }
                }
                if(screen) MainThread.canvas.drawRect((float)j*tileSize + offSet, (float)i*tileSize, (float)j*tileSize + tileSize + offSet, (float)i*tileSize + tileSize, GamePanel.paint);
                else MainThread.canvas.drawRect((float)j*tileSize + offSet + 24*tileSize, (float)i*tileSize, (float)j*tileSize + tileSize + offSet + 24*tileSize, (float)i*tileSize + tileSize, GamePanel.paint);

                if(i<22)  GamePanel.paint.setColor(Color.rgb(255,255,255));
                else if(i>24) GamePanel.paint.setColor(Color.rgb(0,0,0));
                else {
                    switch (level2[i][j]) {
                        case 0:
                            GamePanel.paint.setColor(Color.rgb(255, 255, 255));
                            break;
                        case 1:
                            GamePanel.paint.setColor(Color.rgb(0, 0, 0));
                            break;

                        default:
                            break;
                    }
                }
                if(screen) MainThread.canvas.drawRect((float)j*tileSize + offSet + 24*tileSize, (float)i*tileSize, (float)j*tileSize + tileSize + offSet + 24*tileSize, (float)i*tileSize + tileSize, GamePanel.paint);
                else MainThread.canvas.drawRect((float)j*tileSize + offSet, (float)i*tileSize, (float)j*tileSize + tileSize + offSet, (float)i*tileSize + tileSize, GamePanel.paint);
            }
    }
}
