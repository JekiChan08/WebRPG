package com.example.WebRPG.Model.Characters.Dwarf.enemies;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum GroupEnemies {
    WOLF("Волки", 25, 25, 100, 1, 1),
    EARTHWORMS("Земляные черви", 25, 25, 100, 1, 1),
    GIANT_MOLES("Гиганские кроты", 25, 25, 100, 1, 1),
    VAMPIRE("Вампиры", 25, 25, 100, 1, 1),
    SPIDER("Пауки", 25, 25, 100, 1, 1),
    COBALT("Кобальды", 25, 25, 100, 1, 1),
    SKELETON("Скелеты", 25, 25, 100, 1, 1),
    GOBLIN( "Гоблины", 25, 25, 100, 1, 1);


    private String name;
    private double damage;
    private double health;
    private int coin;
    private int level;
    private int point;

}
