package com.example.WebRPG.Model.Characters.Person;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EnumEnemies {
    GOBLIN( "Гоблин", 50, 10, 100),
    SKELETON( "Скелет", 50, 10, 100),
    ZOMBIE("Зомби", 50, 10, 100),
    COBALT( "Кобальт", 50, 10, 100),
    VAMPIRE( "Вампир", 50, 10, 100),
    BASILISK( "Василиск", 50, 10, 100),
    GIANT( "Гигант", 50, 10, 100),
    SPIDER( "Паук", 50, 10, 100),
    CYCLOPS( "Циклоп", 50, 10, 100);

    private String name;
    private double health;
    private double damage;
    private double many;
}



