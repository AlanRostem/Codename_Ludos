package com.example.codename_ludos.Games.Eggrun;

import android.graphics.Color;

import com.example.codename_ludos.Assets.Shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {

    public Map()
    {
        for (int i = 0; i < 30; i++) {
            level.add(new ArrayList<Integer>(72));
            for (int j = 0; j < 72; j++){
                level.get(i).add(0);
            }
    }
        randomize();
    }

    int tileSize = 40;
    public int offSet = 0;
    int speed = 4;

    public boolean screen = false;

    public List<List<Integer>> level = new ArrayList<List<Integer>>();

    Random rnd = new Random();

    public void randomize() {
        for (int i = 0; i < level.size(); i++)
            for (int j = 0; j < level.get(0).size(); j++) {
                level.get(i).set(j, rnd.nextInt(2));
            }

        for (int i = 0; i < level.size(); i++)
            for (int j = 0; j < level.get(0).size(); j++) {
                {
                     /*   if (k >= bottom && k < level.length - 1 && level[k][l].get(0) == 0 && level[k][l] == 1) {
                            level[k + 1][l].set() = 1;
                        }
                        if (k >= bottom && k<level1.length-1 && l<level1[k].length-1 && l>0 && level1[k][l+1] == 0 && level1[k][l-1] == 0 && level1[k][l] == 1) {
                            level1[k][l] = 0;
                        }
                        if (k >= bottom && k<level1.length-1 && l<level1[k].length-1 && l>0 && level1[k][l+1] == 1 && level1[k][l-1] == 1 && level1[k][l] == 0) {
                            level1[k][l] = 1;
                        }*/
                }
            }
    }

    public void update() {
        /*offSet-=speed;
        if(offSet<=-tileSize+speed) {
            offSet = 0;
            randomize();
        }*/
    }

    public void draw()
    {
        for(int i = 0; i<level.size(); i++)
            for(int j = 0; j<level.get(0).size(); j++)
            {
                if(i<=level.size()-6) continue;
                else if(i>level.size()-2) Shapes.setColor(Color.rgb(0,0,0));
                else {

                    switch (level.get(i).get(j)) {
                        case 0:
                            break;
                        case 1:
                            Shapes.setColor(Color.rgb(0, 0, 0));
                            break;
                        default:
                            break;

                    }
                }
            }
    }
}
