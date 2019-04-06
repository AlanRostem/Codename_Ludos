package com.vetche.codename_ludos.Entity;

import com.vetche.codename_ludos.Core.MainThread;
import com.vetche.codename_ludos.LibraryTools.Math.Vector2D;

public class EntitySpawner {
    private EntityManager binding;
    private float timeInterval;
    private float currentTime = 0;
    private Vector2D spawnRegion;
    private Vector2D spawnOffset = new Vector2D(0,0);

    public EntitySpawner(EntityManager binding, float timeInterval, float spawnRegionX, float spawnRegionY) {
        this.binding = binding;
        this.timeInterval = timeInterval;
        spawnRegion = new Vector2D(spawnRegionX, spawnRegionY);
    }

    public void setTimeInterval(float val) {
        timeInterval = val;
    }

    public void setSpawnRegion(float x, float y) {
        this.spawnRegion.x = x;
        this.spawnRegion.y = y;
    }

    public void setSpawnOffset(float x, float y) {
        this.spawnOffset.x = x;
        this.spawnOffset.y = y;
    }

    public GameEntity create(float x, float y) {
        return new GameEntity(x, y);
    }

    public void update() {
        currentTime += MainThread.getCurrentDeltaTime();
        if (currentTime >= timeInterval) {
            currentTime = 0;
            float x = spawnOffset.x + spawnRegion.x * (float)Math.random();
            float y = spawnOffset.y + spawnRegion.y * (float)Math.random();
            GameEntity entity = create(x, y);
            binding.spawnEntity(entity);
        }
    }
}
