package com.example.WebRPG.Model.Characters.Dwarf;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Entity
@Data
@Setter
@AllArgsConstructor
@Getter
@Table(name = "dwarfs")
public class DwarfGroup {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "health")
    private double health;
    @Column(name = "damage")
    private double damage;
    @Column(name = "protection")
    private double protection;
    @Column(name = "level")
    private int level;
    @Column(name = "max_health")
    private double maxHealth;

    @ManyToOne
    @JoinColumn(name = "fortress_id")
    private Fortress fortress;

    @OneToOne
    @JoinColumn(name = "gate_id")  // Внешний ключ для связи с Gate
    private Gate gate;

    public DwarfGroup() {
        this.level = 1;
        this.damage = new Random().nextInt(20, 30);
        this.health = new Random().nextInt(23, 33);
        this.protection = new Random().nextInt(1, 10);
        this.maxHealth = health;
    }

    public void levelUp() {
        level++;
        setHealth(getHealth() * level);
        setDamage(getDamage() * level);

    }
}