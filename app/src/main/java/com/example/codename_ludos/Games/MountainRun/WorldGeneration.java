package com.example.codename_ludos.Games.MountainRun;

import android.graphics.Path;
import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.LibraryTools.cl;
import com.example.codename_ludos.R;
import java.util.ArrayList;
import java.util.Random;


public class WorldGeneration {

    public WorldGeneration() {
        for (int i = 0; i < height; i++) {
            level.add(new ArrayList<Integer>(width));
            for (int j = 0; j < width; j++) level.get(i).add(0);
        }
    }

    public int tileSize = 8;
    public boolean asc = true;
    public int dirCount = 0;
    public int step = 5;
    public int elevation = 0;
    public int preElevation = 0;
    public int preTotalElevation = 0;
    public int prepreTotalElevation = 0;
    public int totalElevation = 0;
    public final int MAXELEVATION = 19;

    int counter = 0;

    public int offSet = 0;

    int map1offset = 0;
    int map2offset = 0;

    int parallaxW = 2160;
    int parallaxH = 1920;

    int height = 40;
    int width  = 48;

    int chunkWidth = width/2;

    public TileMap level = new TileMap(tileSize);

    Random rnd = new Random();
    SpriteMap tilemap = new SpriteMap(R.drawable.worldtiles);

    public void genDirection() { if(++dirCount == step && (dirCount -= step) == 0) asc = !asc; }

    public void randomize()
    {
        for (int i = 0; i < level.size(); i++) {
            for (int j = 0; j < level.get(0).size(); j++) {
                if (j < chunkWidth) level.get(i).set(j, level.get(i).get(j + chunkWidth));
                else level.get(i).set(j, 0);
            }
        }

        preElevation = elevation;

        prepreTotalElevation = preTotalElevation;

        preTotalElevation = totalElevation;

        elevation = rnd.nextInt(MAXELEVATION) + 1;
        if(!asc) elevation*=-1;

        totalElevation += elevation;

        Vector2D vec = new Vector2D(tileSize, elevation/(float)chunkWidth);
        setTrace(vec);
    }

    public boolean stackOverflow(int i, int j) { return !(i > 0 && i < height - 1 && j > 0 && j < width - 1); }

    public int lvl(int x, int y, int j, int i) { return level.get(i + y).get(j + x); }

    public int lvl(int j, int i) { return level.get(i).get(j); }



    public void setTrace(Vector2D vec)
    {
        float y = height/2f;
        for(int x = chunkWidth; x < width; x++)
        {
            level.get((int)y).set(x, 5);
            y += vec.y;
        }

       /* for(int i = 0; i<level.size(); i++)
            for(int j = chunkWidth; j <level.get(0).size(); j++)
                    if (lvl(j, i) > 0 && i<height-1)
                        if (lvl(0,1, j, i) == 0) level.get(i + 1).set(j, 5); // Check if block under is empty
        */
    }

    public void update() {
        if(counter ++ == 60)
        {
            counter = 0;
            genDirection();
            randomize();
        }
    }

    public void draw()
    {
        tilemap.drawTileMap(level, 40, 3, 0, offSet, prepreTotalElevation*tileSize + 500, 0, chunkWidth);
        tilemap.drawTileMap(level, 40, 3, 0, offSet, preTotalElevation*tileSize + 500, chunkWidth, level.get(0).size());
    }
}