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
@Table(name = "pc")
public class PC {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "health")

    private double health;
    @Column(name = "max_health")

    private double maxHealth;
    @Column(name = "mana")

    private int mana;
    @Column(name = "max_mana")
    private int maxMana;

    @Column(name = "battles")
    private int battles;

    public PC() {
        this.mana = 10;
        this.maxMana = 10;
        this.health = 100;
        this.maxHealth = health;
        this.battles = 1;
    }
    public void increasedHealth(double maxHealth) {
        this.maxHealth += maxHealth;
    }
}
