package com.example.codename_ludos;

import android.graphics.Canvas;

import java.util.ArrayList;

public class GameObjectManager {
    // Class that handles update and draw methods for GameObjects and
    // decides its existence
    private ArrayList<GameObject> objects;

    public GameObjectManager() {
        this.objects = new ArrayList<>();
    }

    // Append a new GameObject to the manager
    public void addObject(GameObject object) {
        objects.add(object);
    }

    // Calls the 'update' methods of all GameObjects
    public void updateObjects() {
        for (GameObject o : objects) {
            if (o.toRemove) {
                objects.remove(o);
            }
            o.update();
        }
    }

    // Calls the 'draw' methods of all GameObjects
    public void drawObjects(Canvas canvas) {
        for (GameObject o : objects) {
            o.draw(canvas);
        }
    }
}
