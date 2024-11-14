package com.example.WebRPG.Model.Characters.MagicalBest;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@AllArgsConstructor
@Getter
@Table(name = "magical_enemies")
public class MagicalEnemies {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "health")
    private double health;
    @Column(name = "name")
    private String name;
    @Column(name = "mana")
    private int mana;
    @Column(name = "max_mana")
    private int maxMana;

    public MagicalEnemies(String name) {
        this.mana = 10;
        this.maxMana = 10;
        this.health = 100;
        this.name = name;

    }

    public MagicalEnemies() {

    }
}
