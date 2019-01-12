package com.mygdx.game;

//This enum contains all class names of classes that can be spawned by SpawnPool
//E.g. if you want SpawnPool to contain a pool with objects of class "Enemy", then
//Enemy should be defined here as SpawnType.
public enum SpawnType {
    Enemy,
    Missile,
    Item
}
