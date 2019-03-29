package com.example.codename_ludos.Games.MountainRun;

import android.graphics.Color;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.R;

import java.util.ArrayList;
import java.util.Random;

import static com.example.codename_ludos.Core.GamePanel.paint;
import static com.example.codename_ludos.Core.MainThread.canvas;


public class WorldGeneration {

    public WorldGeneration() {
        for (int i = 0; i < height; i++) {
            level.add(new ArrayList<Integer>(width));
            for (int j = 0; j < width; j++) level.get(i).add(0);
        }

        /*for (int i = 0; i < level.size(); i++) {
            for (int j = 0; j < level.get(0).size(); j++) {
                level.get(i).set(j, rnd.nextInt(8));
            }
        }*/
    }

    public void genDirection() { if(++dirCount == step && (dirCount -= step) == 0) asc = !asc; }

    public void randomize()
    {
        elevation = rnd.nextInt(MAXELEVATION) + 1;
        level.get(10).set(0, 1);
        if(asc) level.get(10 + elevation*-1).set(11, 1);
        else level.get(10 + elevation).set(11, 1);
    }

    public int tileSize = 64;
    public boolean asc = true;
    public int dirCount = 0;
    public int step = 5;
    public int elevation = 0;
    public final int MAXELEVATION = 9;

    int counter = 0;

    public int offSet = 0;

    int map1offset = 0;
    int map2offset = 0;

    int parallaxW = 2160;
    int parallaxH = 1920;

    public int speed = 8;

    int height = 30;
    int width  = 48;

    int chunkWidth = 24;

    int maxTop = height-6;
    int maxBottom = height;

    public TileMap level = new TileMap(tileSize);

    Random rnd = new Random();
    SpriteMap tilemap = new SpriteMap(R.drawable.worldtiles);



    public void update() {

    }

    public void draw()
    {
        if(counter ++ == 120) {randomize(); counter = 0; }
        genDirection();
        tilemap.drawTileMap(level, 3, 0,
                offSet, 0);
    }
}
