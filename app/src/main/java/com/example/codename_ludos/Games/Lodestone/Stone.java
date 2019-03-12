package com.example.codename_ludos.Games.Lodestone;

import com.example.codename_ludos.Entity.GameEntity;

public class Stone extends GameEntity {
    public Stone(){ super(10,10);} // Start Position

    enum Type {LODE, ORB, MOVE, BUTTON, STATIC, GOAL}


}
