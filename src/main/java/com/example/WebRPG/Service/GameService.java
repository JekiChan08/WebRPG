package com.example.WebRPG.Service;

import com.example.WebRPG.Model.Characters.Person.Boss;
import com.example.WebRPG.Model.Characters.Person.Enemies;
import com.example.WebRPG.Model.Characters.Person.EnumEnemies;
import com.example.WebRPG.Model.Characters.Person.MainHero;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public interface GameService {
    void attack(MainHero mainHero, Enemies enemies);
    void setEnd(MainHero mainHero);
    Enemies rnEnemies(int level);
    Enemies elfEnemies(int level);
    Enemies orcEnemies(int level);
    Enemies dwarfEnemies(int level);
    public Enemies magicalBestEnemies(int level);
    public Boss rnBoss(int level);
    public void attackBoss(MainHero mainHero, Boss boss);
    boolean upgradeWeapon(MainHero mainHero);
    boolean usePotion(MainHero mainHero);
}
