package com.example.codename_ludos;

import com.example.codename_ludos.Entity.GameEntity;

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

    // Returns GameEntity in objects arraylist
    public GameEntity getEntity(int index){ return objects.get(index); }

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
    public void drawEntities() {
        for (GameEntity o : objects) {
            o.draw();
        }
    }
}