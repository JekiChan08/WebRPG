package com.example.WebRPG.Model.Characters.Person;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Boss {
    private EnumBoss enumBoss;
    private String name;
    private double health;
    private double damage;

    public Boss(EnumBoss enumBoss) {
        this.name = enumBoss.getName();
        this.health = enumBoss.getHealth();
        this.damage = enumBoss.getDamage();
    }
}
