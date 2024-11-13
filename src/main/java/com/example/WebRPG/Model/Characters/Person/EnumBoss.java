package com.example.WebRPG.Model.Characters.Person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public enum EnumBoss {
    DRACULA("Дракула", 500, 90),
    DRAGON("Дракон", 500, 90),
    GIANT_WORM("Гиганский Червь", 500, 90),
    GRIFFIN("Грифон", 500, 90),
    ELF("Эльф", 500, 90),
    NAZGUL("Назгул", 500, 90);

    private String name;
    private double health;
    private double damage;

}
