package com.example.WebRPG.Model.Item.All;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@Table(name = "weapon")
public class Weapon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "type_damage")

    private String typeDamage;//mag/physical
    @Column(name = "damage")
    @Cascade(CascadeType.ALL)
    private double damage;
    @Column(name = "price")
    @Cascade(CascadeType.ALL)
    private double price;
    private static final double UPGRADE_MULTIPLIER = 1.5;

    public Weapon() {
        this.damage = 1000;
        this.price = 75;
        this.typeDamage = "mag";
    }

    public Weapon upgradeWeapon() {
        this.damage *= UPGRADE_MULTIPLIER;
        return this;
    }

    @Override
    public String toString() {
        return "(Урон: " + damage + ", Тип урона: " + typeDamage + ")";
    }
}
