package com.example.WebRPG.Model.Item.All;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

@Entity
@Data
@Setter
@AllArgsConstructor
@Table(name = "armor")
public class Armor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id;
    @Column(name = "defense")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private int defense;

    public Armor() {
        defense = 20;
    }

    public void upgradeDefense(int increase) {
        this.defense += increase;
    }

    public Armor(int defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "(Защита: " + defense + "%)";
    }
}

