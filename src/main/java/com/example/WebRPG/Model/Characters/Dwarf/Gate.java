package com.example.WebRPG.Model.Characters.Dwarf;

import com.example.WebRPG.Model.Characters.Dwarf.enemies.EnemiesGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gate")
public class Gate {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "health")
    private double health;

    @Column(name = "protection")
    private double protection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fortress_id")
    private Fortress fortress;

    @OneToMany(mappedBy = "gate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EnemiesGroup> steps;

    @OneToOne(mappedBy = "gate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DwarfGroup dwarf;

    public Gate(String name) {
        this.name = name;
        this.health = 500;
        this.protection = 0;
    }
    public boolean AddEnemiesToThePolygon(EnemiesGroup groupEnemies) {
        if (steps.get(3) == null) {
            steps.set(3, groupEnemies);
            return true;
        } return false;
    }

    public boolean addEnemiesToStep(EnemiesGroup group) {
        if (steps.size() < 3) {
            steps.add(group);
            group.setGate(this);
            return true;
        }
        return false;
    }


}
