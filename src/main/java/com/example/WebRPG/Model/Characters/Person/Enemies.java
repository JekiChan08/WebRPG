package com.example.WebRPG.Model.Characters.Person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Enemies {
    private EnumEnemies enemies;
    private String name;
    private double health;
    private double damage;
    private double many;

    public Enemies(EnumEnemies enemies) {
        name = enemies.getName();
        health = enemies.getHealth();
        damage = enemies.getDamage();
        many = enemies.getMany();
    }
}
