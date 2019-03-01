package com.example.codename_ludos.Games.Surge.Objects;

import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.Games.Surge.Surge;

public class SolidObject extends GameEntity {
    private String drawName;
    public SolidObject(String drawName, float x, float y, int width, int height) {
        super(x, y);
        this.drawName = drawName;
        this.height = height;
        this.width = width;
    }

    @Override
    public void draw() {
        Surge.objects.drawAt(this.drawName, (int)mPos.x, (int)mPos.y + (int)Surge.camera.y, width, height);
    }
}
