package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;

import com.example.codename_ludos.Assets.Shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {

    public Map()
    {
        for (int i = 0; i < height; i++) {
            level.add(new ArrayList<Integer>(width));
            for (int j = 0; j < width; j++){
                level.get(i).add(0);
            }
    }
        initialize();
    }

    int tileSize = 40;
    public int offSet = 0;
    int speed = 4;

    int height = 30;
    int width  = 48;

    int chunkWidth = 24;

    int maxTop = height-6;
    int maxBottom = height-2;

    public List<List<Integer>> level = new ArrayList<List<Integer>>();

    Random rnd = new Random();

    public void initialize() {
        for (int i = 0; i < level.size(); i++)
            for (int j = 0; j < level.get(0).size(); j++) {
                level.get(i).set(j, rnd.nextInt(2));
            }

        for (int i = 0; i < level.size(); i++)
            for (int j = 0; j < level.get(0).size(); j++)
            {
                {
                        if (i > maxTop && i <= maxBottom && level.get(i+1).get(j) == 0 && level.get(i).get(j) == 1) level.get(i+1).set(j,1);
                        if (i > maxTop && i <= maxBottom && j<level.get(i).size()-1 && j>0 && level.get(i).get(j+1) == 0 && level.get(i).get(j-1) == 0 && level.get(i).get(j) == 1)
                        {
                            level.get(i).set(j,0);
                        }
                        if (i > maxTop && i <= maxBottom && j<level.get(i).size()-1 && j>0 && level.get(i).get(j+1) == 1 && level.get(i).get(j-1) == 1 && level.get(i).get(j) == 0)
                        {
                        level.get(i).set(j,1);
                        }
                }
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
                } else
                    {
                    level.get(i).set(j, rnd.nextInt(2));
                    }
            }

        for (int i = 0; i < level.size(); i++)
            for (int j = chunkWidth-1; j < level.get(0).size(); j++)
            {
                {
                        if (i > maxTop && i <= maxBottom && level.get(i + 1).get(j) == 0 && level.get(i).get(j) == 1) level.get(i + 1).set(j, 1);
                        if (i > maxTop && i <= maxBottom && j < level.get(i).size() - 1 && j > 0 && level.get(i).get(j + 1) == 0 && level.get(i).get(j - 1) == 0 && level.get(i).get(j) == 1)
                        {
                            level.get(i).set(j, 0);
                        }
                        if (i > maxTop && i <= maxBottom && j < level.get(i).size() - 1 && j > 0 && level.get(i).get(j + 1) == 1 && level.get(i).get(j - 1) == 1 && level.get(i).get(j) == 0)
                        {
                            level.get(i).set(j, 1);
                        }
                    }
                }
    }

    public void update() {
        offSet-=speed;
        if(offSet<=-tileSize*chunkWidth+speed) {
           offSet = 0;
           randomize();
        }
    }

    public void draw()
    {
        for(int i = 0; i<level.size(); i++)
            for(int j = 0; j<level.get(0).size(); j++)
            {
                if(i<=maxTop) continue;
                else if(i>maxBottom) {
                    Shapes.setColor(Color.rgb(0, 0, 0));
                    Shapes.drawRect((float)j*tileSize + offSet + 90, (float)i*tileSize + 160, tileSize , tileSize);
                }
                else {

                    switch (level.get(i).get(j)) {
                        case 0:
                            break;
                        case 1:
                            Shapes.setColor(Color.rgb(0, 0, 0));
                            Shapes.drawRect((float)j*tileSize + offSet + 90, (float)i*tileSize + 160, tileSize , tileSize);
                            break;
                        default:
                            break;

                    }
                }
            }
    }
}
