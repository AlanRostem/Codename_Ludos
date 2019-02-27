package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Shapes;
import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.R;

import java.util.ArrayList;
import java.util.List;
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

    SpriteMap sky = new SpriteMap(R.drawable.sky);
    SpriteMap sky2 = new SpriteMap(R.drawable.sky2);

    int tileSize = 40;
    public int offSet = 0;

    int map1offset = 0;
    int map2offset = 0;

    int parallaxW = 2160;
    int parallaxH = 1920;

    int speed = 4;

    int height = 30;
    int width  = 48;

    int chunkWidth = 24;

    int maxTop = height-6;
    int maxBottom = height;

    public TileMap  level = new TileMap(tileSize);

    Random rnd = new Random();

    public void initialize() {
        for (int i = maxTop + 1; i < level.size(); i++)
            for (int j = 0; j < level.get(0).size(); j++) {
                level.get(i).set(j, 1);
            }
    }

    public void randomize()
    {
        for (int i = maxTop + 1; i < level.size(); i++)
            for (int j = 0; j < level.get(0).size(); j++)
            {
                if (j < chunkWidth)
                {
                    level.get(i).set(j, level.get(i).get(j + chunkWidth));
                } else
                    {
                    level.get(i).set(j, rnd.nextInt(2));
                    }
            }

        for (int i = 0; i < level.size(); i++)
            for (int j = 0; j < level.get(0).size(); j++)
            {

                        if (i < maxBottom-1 && level.get(i + 1).get(j) == 0 && level.get(i).get(j) == 1) level.get(i + 1).set(j, 1); // Check if block under is empty
                        if (i < maxBottom   && j < level.get(i).size() - 2 && j > 0 && level.get(i).get(j + 1) == 0 && level.get(i).get(j - 1) == 0 && level.get(i).get(j) == 1) level.get(i).set(j, 0);
                        if (i < maxBottom   && j < level.get(i).size() - 2 && j > 0 && level.get(i).get(j + 1) == 1 && level.get(i).get(j - 1) == 1 && level.get(i).get(j) == 0) level.get(i).set(j, 1);

                        /*if (j == chunkWidth-1 && level.get(i).get(j  - 1) == 1 && level.get(i).get(j) == 0) level.get(i).set(j, 1);
                        if (j == 0 && level.get(i).get(j + 1) == 1 && level.get(i).get(j) == 0) level.get(i).set(j, 1);*/

                    }
                }

    public void update() {
        offSet-=speed;
        if(offSet<=-tileSize*chunkWidth+speed) {
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

        for(int i = 0; i<level.size(); i++)
            for(int j = 0; j<level.get(0).size(); j++)
            {
                if(i>maxTop)
                {
                    switch (level.get(i).get(j)) {
                        case 0:
                            break;
                        case 1:
                            Shapes.setColor(Color.rgb(0, 0, 0));
                            Shapes.drawRect((float)j*tileSize + offSet + ArcadeMachine.SCREEN_OFFSET_X, (float)i*tileSize + ArcadeMachine.SCREEN_OFFSET_Y, tileSize , tileSize);
                            break;
                        default:
                            break;

                    }
                }
            }
    }
}
