package com.example.WebRPG.Model.Characters.Dwarf.enemies;


import com.example.WebRPG.Model.Characters.Dwarf.Gate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "enemies_groups")
public class EnemiesGroup {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private GroupEnemies groupEnemies;
    @Column(name = "name")
    private String name;
    @Column(name = "health")
    private double health;
    @Column(name = "damage")
    private double damage;
    @Column(name = "coin")
    private int coin;
    @Column(name = "point")
    private int point;

    @ManyToOne
    @JoinColumn(name = "gate_id")
    private Gate gate;

    public EnemiesGroup(GroupEnemies groupEnemies) {
        this.groupEnemies = groupEnemies;
        this.name = groupEnemies.getName();
        this.health = groupEnemies.getHealth();
        this.damage = groupEnemies.getDamage();
        this.coin = 100;
        this.point = 1;
    }


    public void damageUp(int multiplier) {
        setHealth(getHealth() * multiplier);
        setDamage(getDamage() * multiplier);
    }
}
