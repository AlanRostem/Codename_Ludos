package com.example.codename_ludos.Games.Starbit;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;

public class Starbit extends ArcadeGame {
    public Starbit() {
        super("Starbit");
    }

    private Player player = new Player();

    @Override
    public void setup() {
        spawnEntuty(player);
    }

}
