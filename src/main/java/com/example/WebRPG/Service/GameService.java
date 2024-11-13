package com.example.WebRPG.Service;

import com.example.WebRPG.Model.Characters.Person.Boss;
import com.example.WebRPG.Model.Characters.Person.Enemies;
import com.example.WebRPG.Model.Characters.Person.MainHero;

public interface GameService {
    void attack(MainHero mainHero, Enemies enemies);
    void setEnd(MainHero mainHero);
    Enemies rnEnemies(int level);
    public Boss rnBoss(int level);
    public void attackBoss(MainHero mainHero, Boss boss);
    boolean upgradeWeapon(MainHero mainHero);
    boolean usePotion(MainHero mainHero);
}
