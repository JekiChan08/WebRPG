package com.example.WebRPG.Model.Characters.Person;


import com.example.WebRPG.Model.Item.All.Armor;
import com.example.WebRPG.Model.Item.All.Weapon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Data
@Setter
@AllArgsConstructor
@Getter
@Table(name = "main_hero")
public class MainHero {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    @Cascade(CascadeType.ALL)
    private String name;
    @Column(name = "rasa")
    private String rasa;
    @Column(name = "health")
    @Cascade(CascadeType.ALL)
    private double health;
    @Column(name = "person_class")
    private String personClass; //mag/physical
    @OneToOne
    @JoinColumn(name = "weapon_id", referencedColumnName = "id")
    private Weapon weapon;
    @OneToOne
    @JoinColumn(name = "armor_id", referencedColumnName = "id")
    private Armor armor;
    @Column(name = "money")

    private double money;
    @Column(name = "end_game")
    private boolean endGame;
    @Column(name = "level")
    private int level;

    public MainHero() {
        this.health = 100;
        this.money = 150;
        this.armor = new Armor(20);
        this.endGame = false;
        this.level = 1;
    }


    public double getDamage() {
        return weapon.getDamage();
    }

    public void upgradeArmor(int increase) {
        this.armor.upgradeDefense(increase);
    }
}
