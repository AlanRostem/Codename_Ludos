package com.vetche.codename_ludos.Games.Surge.Objects.Obstacles;

import com.vetche.codename_ludos.Games.Surge.Objects.SurgeEntity;

public class Obstacle extends SurgeEntity {
    public Obstacle(String drawName, float x, float y, int width, int height) {
        super(drawName, x, y, width, height);
        this.drawName = drawName;
    }
}
