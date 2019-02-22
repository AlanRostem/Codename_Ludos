package com.example.codename_ludos;

import android.graphics.Canvas;

import java.util.ArrayList;

public class EntityManager {
    // Class that handles update and draw methods for GameObjects and
    // decides its existence
    private ArrayList<GameEntity> objects;

    public EntityManager() {
        this.objects = new ArrayList<>();
    }

    // Append a new GameEntity to the manager
    public void spawnEntity(GameEntity object) {
        objects.add(object);
    }

    // Calls the 'update' methods of all GameObjects
    public void updateEntities() {
        for (GameEntity o : objects) {
            if (o.isRemoved()) {
                objects.remove(o);
            }
            o.update();
        }
    }

    // Calls the 'draw' methods of all GameObjects
    public void drawEntities(Canvas canvas) {
        for (GameEntity o : objects) {
            o.draw();
        }
    }
}