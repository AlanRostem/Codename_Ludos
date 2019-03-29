package com.example.codename_ludos.Games.MountainRun;

import android.graphics.Color;
import android.graphics.Path;
import android.util.Log;

import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
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
    }

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

        level.get(height/2).set(chunkWidth, 1);
       // level.get(height/2 + elevation).set(width-1, 1);

        //Vector2D vec = new Vector2D(tileSize, (height/2f + elevation) / (float)width - height/2f / (float)width);
        Vector2D vec = new Vector2D(tileSize, elevation/(float)chunkWidth);
        setTrace(vec);
    }

    public boolean stackOverflow(int i, int j) { return !(i > 0 && i < height - 1 && j > 0 && j < width - 1); }

    public int lvl(int x, int y, int j, int i) { return level.get(i + y).get(j + x); }

    public int lvl(int j, int i) { return level.get(i).get(j); }

    private static Path path = new Path();

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
        if(counter ++ == 60) {
            genDirection();
            randomize();
            counter = 0;
            Log.d("El", "draw: " + preElevation);
        }
    }

    public void draw()
    {
        Vector2D vecTileSize = new Vector2D(chunkWidth*tileSize, (height/2f + elevation) * tileSize / (float)width - height/2f * tileSize / (float)width);

        paint.setColor((Color.CYAN));

        for(int i = width; i>0; i--) {
            paint.setColor(Color.rgb(i*10, 0,i*15));

            canvas.drawLine(0, height/2f * tileSize, vecTileSize.x * i, vecTileSize.y * i + height/2f * tileSize, paint);
        }

        tilemap.drawTileMap(level, 40, 3, 0, offSet, prepreTotalElevation*tileSize + 500, 0, chunkWidth);
        tilemap.drawTileMap(level, 40, 3, 0, offSet, preTotalElevation*tileSize + 500, chunkWidth, level.get(0).size());

    }
}