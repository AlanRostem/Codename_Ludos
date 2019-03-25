package com.example.codename_ludos.Assets;

import com.example.codename_ludos.Assets.Audio.Audio;
import com.example.codename_ludos.Assets.Audio.Music;
import com.example.codename_ludos.Assets.Graphics.SpriteMap;

public class Asset {
    private final String name;

    public Asset(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public SpriteMap asSpriteMap() {
        return (SpriteMap) this;
    }

    public Audio asAudio() {
        return (Audio) this;
    }

    public Music asMusic() {
        return (Music) this;
    }

}
