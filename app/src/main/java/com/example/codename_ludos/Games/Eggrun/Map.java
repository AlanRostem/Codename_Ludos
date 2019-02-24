package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;

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
    public int offSet = 0;
    int bottom = 23;
    int top = 24;
    int speed = 4;

    public boolean screen = false;

    public int[][] level1 = new int[30][24];
    public int[][] level2 = new int[30][24];

    Random rnd = new Random();

    public void randomize(boolean screen)
    {
        if(screen) {
            for (int i = 0; i < level1.length; i++)
                for (int j = 0; j < level1[i].length; j++) {
                    level1[i][j] = rnd.nextInt(2);
                }

            for (int k = 0; k < level1.length; k++)
                for (int l = 0; l < level1[k].length; l++) {

                    if (k >= bottom && k<level1.length-1 && l<level1[k].length-1 && level1[k][l+1] == 0 && level1[k][l-1] == 0 && level1[k][l] == 1) {
                    level1[k][l] = 0;
                    }
                    if (k >= bottom && k < level1.length - 1 && level1[k + 1][l] == 0 && level1[k][l] == 1) {
                        level1[k + 1][l] = 1;
                    }

                }
        }
        else {
            for (int i = 0; i < level2.length; i++)
                for (int j = 0; j < level2[i].length; j++) {
                    level2[i][j] = rnd.nextInt(2);
                }

            for (int k = 0; k < level2.length; k++)
                for (int l = 0; l < level2[k].length; l++) {
                    if (k >= bottom && k<level2.length-1 && l<level2[k].length-1 && level2[k][l+1] == 0 && level2[k][l-1] == 0 && level2[k][l] == 1) {
                        level2[k][l] = 0;
                    }
                    if (k >= bottom && k<level2.length-1 && level2[k + 1][l] == 0 && level2[k][l] == 1) {
                        level2[k + 1][l] = 1;
                    }
                }
        }
    }

    public void update() {
        offSet-=speed;
        if(offSet<=-top*tileSize+speed) {
            offSet = 0;
            screen = !screen;
            randomize(!screen);
        }
    }

    public void draw() {
        for(int i = 0; i<level1.length; i++)
            for(int j = 0; j<level1[i].length; j++)
            {
                if(i<bottom)  GamePanel.paint.setColor(Color.rgb(255,255,255));
                else if(i>top) GamePanel.paint.setColor(Color.rgb(0,0,0));
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
                else MainThread.canvas.drawRect((float)j*tileSize + offSet + top*tileSize, (float)i*tileSize, (float)j*tileSize + tileSize + offSet + top*tileSize, (float)i*tileSize + tileSize, GamePanel.paint);

                if(i<bottom)  GamePanel.paint.setColor(Color.rgb(255,255,255));
                else if(i>top) GamePanel.paint.setColor(Color.rgb(0,0,0));
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
                if(screen) MainThread.canvas.drawRect((float)j*tileSize + offSet + top*tileSize, (float)i*tileSize, (float)j*tileSize + tileSize + offSet + top*tileSize, (float)i*tileSize + tileSize, GamePanel.paint);
                else MainThread.canvas.drawRect((float)j*tileSize + offSet, (float)i*tileSize, (float)j*tileSize + tileSize + offSet, (float)i*tileSize + tileSize, GamePanel.paint);
            }
    }
}
