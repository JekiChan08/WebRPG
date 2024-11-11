package com.example.WebRPG.Model.Characters.Person;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EnumEnemies {
    GOBLIN(1, "Гоблин", 50, 10, 100),
    SKELETON(1, "Скелет", 50, 10, 100),
    ZOMBIE(1, "Зомби", 50, 10, 100),
    COBALT(1, "Кобальт", 50, 10, 100),
    VAMPIRE(1, "Вампир", 50, 10, 100),
    BASILISK(1, "Василиск", 50, 10, 100),
    GIANT(1, "Гигант", 50, 10, 100),
    SPIDER(1, "Паук", 50, 10, 100),
    CYCLOPS(1, "Циклоп", 50, 10, 100);

    private double protection;
    private String name;
    private double health;
    private double damage;
    private double many;
}



