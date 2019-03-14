package com.example.codename_ludos.Games.Eggrun;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.SpriteMap;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.R;

import java.util.ArrayList;
import java.util.Random;


public class GameMap {
    public GameMap()
    {
        for (int i = 0; i < height; i++) {
            level.add(new ArrayList<Integer>(width));
            for (int j = 0; j < width; j++){
                level.get(i).add(0);
            }
    }
        initialize();
    }

    SpriteMap tilemap = new SpriteMap(R.drawable.worldtiles);
    SpriteMap sky = new SpriteMap(R.drawable.sky);
    SpriteMap sky2 = new SpriteMap(R.drawable.sky2);

    int tileSize = 40;
    public int offSet = 0;

    int map1offset = 0;
    int map2offset = 0;

    int parallaxW = 2160;
    int parallaxH = 1920;

    int speed = 8;

    int height = 30;
    int width  = 48;

    int chunkWidth = 24;

    int maxTop = height-6;
    int maxBottom = height;

    public TileMap level = new TileMap(tileSize);

    Random rnd = new Random();

    public void initialize() {
        for (int i = maxTop + 1; i < level.size(); i++)
            for (int j = 0; j < level.get(0).size(); j++) {
                level.get(i).set(j, 1);
            }

        for (int i = maxTop+1; i < level.size(); i++)
            for (int j = 0; j < level.get(0).size(); j++)
            {
                if(i==maxTop+1) level.get(i).set(j, 2);
                else level.get(i).set(j, 5);
            }

    }

    public void randomize()
    {
        for (int i = 0; i < level.size(); i++)
            for (int j = 0; j < level.get(0).size(); j++)
            {
                if (j < chunkWidth)
                {
                    level.get(i).set(j, level.get(i).get(j + chunkWidth));
                }

                else if (j>=chunkWidth && i>maxTop - 1) level.get(i).set(j, rnd.nextInt(2));

            }

        int rndS = rnd.nextInt(4);
           // rndS = 3;

        if(rndS == 3)
        {
            int rndI = maxTop - rnd.nextInt(3) - 5;
            int rndJ = rnd.nextInt(chunkWidth) - 2;
            int rndW = rnd.nextInt(chunkWidth) - rndJ;
            int rndH = rnd.nextInt(3) + 2;

            for (int i = 0; i < rndH; i++)
                for (int j = 0; j < rndW; j++)
                {
                    if(i<2) level.get(rndI - i).set(chunkWidth + rndJ + j, 1);
                    else level.get(rndI - i).set(chunkWidth + rndJ + j, rnd.nextInt(2));
                }

        }

        for (int i = 0; i < level.size(); i++)
            for (int j = chunkWidth; j < level.get(0).size(); j++)
            {
                if(level.get(i).get(j)>0 && j<level.get(0).size()-1 && j>chunkWidth && i < height-1) {
                    if (level.get(i + 1).get(j) == 0) level.get(i + 1).set(j, 1); // Check if block under is empty
                    if (level.get(i).get(j + 1) == 0 && level.get(i).get(j - 1) == 0) level.get(i).set(j, 0);
                }

                if(level.get(i).get(j)==0 && j<level.get(0).size()-1 && j>chunkWidth  && i < height-1)
                    if (level.get(i).get(j + 1) >  0 && level.get(i).get(j - 1) >  0) level.get(i).set(j, 1);

                if(j==chunkWidth && i < height-1) {
                    if (level.get(i).get(j) == 0 && level.get(i).get(j + 1) >  0) level.get(i).set(j, 1);
                    if (level.get(i).get(j) >  0 && level.get(i + 1).get(j) == 0) level.get(i + 1).set(j, 1);
                    if (level.get(i).get(j) >  0 && level.get(i).get(j - 1) == 0) level.get(i).set(j, 0);
                }

                if(j==level.get(0).size()-1 &&  i < height-1) {
                    if (level.get(i).get(j) == 0 && level.get(i).get(j - 1) >  0) level.get(i).set(j, 1);
                    if (level.get(i).get(j) >  0 && level.get(i).get(j - 1) == 0) level.get(i).set(j, 0);
                    if (level.get(i).get(j) >  0 && level.get(i + 1).get(j) == 0) level.get(i + 1).set(j, 1);
                }

                if(i == height - 1) {
                    if (level.get(i).get(j) == 0 && level.get(i).get(j - 1) > 0 && level.get(i).get(j + 1) > 0) level.get(i).set(j, 1);
                }

            }

            setMapSprite();
    }

    public void setMapSprite()
    {
        for (int i = 0; i < level.size(); i++)
            for (int j = chunkWidth - 1; j < level.get(0).size(); j++)
            {
                if(level.get(i).get(j) > 0)
                {
                    if(j<level.get(0).size()-1 && i < height - 1) {
                        if(level.get(i).get(j-1) == 0 && level.get(i).get(j+1) >  0 && level.get(i-1).get(j) == 0 && level.get(i+1).get(j) >  0) level.get(i).set(j, 1);
                        if(level.get(i).get(j-1) >  0 && level.get(i).get(j+1) >  0 && level.get(i-1).get(j) == 0 && level.get(i+1).get(j) >  0) level.get(i).set(j, 2);
                        if(level.get(i).get(j-1) >  0 && level.get(i).get(j+1) == 0 && level.get(i-1).get(j) == 0 && level.get(i+1).get(j) >  0) level.get(i).set(j, 3);
                        if(level.get(i).get(j-1) == 0 && level.get(i).get(j+1) >  0 && level.get(i-1).get(j) >  0 && level.get(i+1).get(j) >  0) level.get(i).set(j, 4);
                        if(level.get(i).get(j-1) >  0 && level.get(i).get(j+1) >  0 && level.get(i-1).get(j) >  0 && level.get(i+1).get(j) >  0) level.get(i).set(j, 5);
                        if(level.get(i).get(j-1) >  0 && level.get(i).get(j+1) == 0 && level.get(i-1).get(j) >  0 && level.get(i+1).get(j) >  0) level.get(i).set(j, 6);
                        if(level.get(i).get(j-1) == 0 && level.get(i).get(j+1) >  0 && level.get(i-1).get(j) >  0 && level.get(i+1).get(j) == 0) level.get(i).set(j, 7);
                        if(level.get(i).get(j-1) >  0 && level.get(i).get(j+1) >  0 && level.get(i-1).get(j) >  0 && level.get(i+1).get(j) == 0) level.get(i).set(j, 8);
                        if(level.get(i).get(j-1) >  0 && level.get(i).get(j+1) == 0 && level.get(i-1).get(j) >  0 && level.get(i+1).get(j) == 0) level.get(i).set(j, 9);
                    }

                    else if(j == level.get(0).size() - 1 && i<height-1)
                    {
                        if(level.get(i).get(j-1) == 0 && level.get(i-1).get(j) == 0 && level.get(i+1).get(j) >  0) level.get(i).set(j, 1);
                        if(level.get(i).get(j-1) >  0 && level.get(i-1).get(j) == 0 && level.get(i+1).get(j) >  0) level.get(i).set(j, 2);
                        if(level.get(i).get(j-1) >  0 && level.get(i-1).get(j) == 0 && level.get(i+1).get(j) >  0) level.get(i).set(j, 3);
                        if(level.get(i).get(j-1) == 0 && level.get(i-1).get(j) >  0 && level.get(i+1).get(j) >  0) level.get(i).set(j, 4);
                        if(level.get(i).get(j-1) >  0 && level.get(i-1).get(j) >  0 && level.get(i+1).get(j) >  0) level.get(i).set(j, 5);
                        if(level.get(i).get(j-1) >  0 && level.get(i-1).get(j) >  0 && level.get(i+1).get(j) >  0) level.get(i).set(j, 6);
                        if(level.get(i).get(j-1) == 0 && level.get(i-1).get(j) >  0 && level.get(i+1).get(j) == 0) level.get(i).set(j, 7);
                        if(level.get(i).get(j-1) >  0 && level.get(i-1).get(j) >  0 && level.get(i+1).get(j) == 0) level.get(i).set(j, 8);
                        if(level.get(i).get(j-1) >  0 && level.get(i-1).get(j) >  0 && level.get(i+1).get(j) == 0) level.get(i).set(j, 9);
                    }

                    else if(i == height - 1)
                    {
                        if(j == level.get(0).size() - 1)
                        {
                            if(level.get(i).get(j-1) == 0 && level.get(i-1).get(j) == 0) level.get(i).set(j, 1);
                            else if(level.get(i).get(j-1) >  0 && level.get(i-1).get(j) == 0) level.get(i).set(j, 2);
                            else if(level.get(i).get(j-1) >  0 && level.get(i-1).get(j) == 0) level.get(i).set(j, 3);
                            else if(level.get(i).get(j-1) == 0 && level.get(i-1).get(j) >  0) level.get(i).set(j, 4);
                            else if(level.get(i).get(j-1) >  0 && level.get(i-1).get(j) >  0) level.get(i).set(j, 5);
                            else if(level.get(i).get(j-1) >  0 && level.get(i-1).get(j) >  0) level.get(i).set(j, 6);
                            else if(level.get(i).get(j-1) == 0 && level.get(i-1).get(j) >  0) level.get(i).set(j, 7);
                            else if(level.get(i).get(j-1) >  0 && level.get(i-1).get(j) >  0) level.get(i).set(j, 8);
                            else if(level.get(i).get(j-1) >  0 && level.get(i-1).get(j) >  0) level.get(i).set(j, 9);
                        }

                        else if(level.get(i).get(j-1) == 0 && level.get(i).get(j+1) >  0 && level.get(i-1).get(j) == 0) level.get(i).set(j, 1);
                        else if(level.get(i).get(j-1) >  0 && level.get(i).get(j+1) >  0 && level.get(i-1).get(j) == 0) level.get(i).set(j, 2);
                        else if(level.get(i).get(j-1) >  0 && level.get(i).get(j+1) == 0 && level.get(i-1).get(j) == 0) level.get(i).set(j, 3);
                        else if(level.get(i).get(j-1) == 0 && level.get(i).get(j+1) >  0 && level.get(i-1).get(j) >  0) level.get(i).set(j, 4);
                        else if(level.get(i).get(j-1) >  0 && level.get(i).get(j+1) >  0 && level.get(i-1).get(j) >  0) level.get(i).set(j, 5);
                        else if(level.get(i).get(j-1) >  0 && level.get(i).get(j+1) == 0 && level.get(i-1).get(j) >  0) level.get(i).set(j, 6);
                    }

                    else{
                        level.get(i).set(j, 10);
                    }
                }
            }
    }

    public void update() {
        offSet-=speed;
        if(offSet <= -tileSize * chunkWidth + speed) {
           offSet = 0;
           randomize();
        }
        map1offset-=speed/2;
        map2offset-=speed/4;

        if(map1offset<=-parallaxW) {
            map1offset=0;
        }

        if(map2offset<=-parallaxW) {
            map2offset=0;
        }
    }

    public void draw()
    {
        sky.drawAt("full", ArcadeMachine.SCREEN_OFFSET_X + map2offset ,ArcadeMachine.SCREEN_OFFSET_Y, parallaxW, parallaxH);
        sky.drawAt("full", ArcadeMachine.SCREEN_OFFSET_X + map2offset + parallaxW ,ArcadeMachine.SCREEN_OFFSET_Y, parallaxW, parallaxH);

        sky2.drawAt("full", ArcadeMachine.SCREEN_OFFSET_X + map1offset ,ArcadeMachine.SCREEN_OFFSET_Y, parallaxW, parallaxH);
        sky2.drawAt("full", ArcadeMachine.SCREEN_OFFSET_X + map1offset + parallaxW ,ArcadeMachine.SCREEN_OFFSET_Y, parallaxW, parallaxH);

        tilemap.drawTileMap(level, 3, 0,
                ArcadeMachine.SCREEN_OFFSET_X + offSet, ArcadeMachine.SCREEN_OFFSET_Y);

    }
}
