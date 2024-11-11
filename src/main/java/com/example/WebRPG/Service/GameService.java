package com.example.WebRPG.Service;

import com.example.WebRPG.Model.Characters.Person.Enemies;
import com.example.WebRPG.Model.Characters.Person.MainHero;

public interface GameService {
    void attack(MainHero mainHero, Enemies enemies);
    void setEnd(MainHero mainHero);
    Enemies rnEnemies();
    boolean upgradeWeapon(MainHero mainHero);
    boolean usePotion(MainHero mainHero);
}
