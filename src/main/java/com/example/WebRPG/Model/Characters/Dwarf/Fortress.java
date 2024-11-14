package com.example.WebRPG.Model.Characters.Dwarf;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Setter
@AllArgsConstructor
@Getter
@Table(name = "fortress")
public class Fortress {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "health")
    private double health;
    @OneToMany(mappedBy = "fortress", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DwarfGroup> dwarfGroups = new ArrayList<>();

    @Column(name = "provision")
    private int provision;
    @Column(name = "balance")
    private int balance;
    @Column(name = "experience")
    private int experience;
    @Column(name = "days")
    private int days;

    @OneToMany(mappedBy = "fortress", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Gate> gates = new ArrayList<>();


    public Fortress() {
        this.health = 500;
        this.dwarfGroups = new ArrayList<>();
        this.provision = 2;
        this.balance = 100;
        this.experience = 1;
        this.days = 1;
    }

    public void stateFortress() {
        System.out.println("Здоровье крепости: " + health);
        if (!dwarfGroups.isEmpty()) {
            System.out.println("У вас есть: " + dwarfGroups.size() + " групп гномов");
        } else {
            System.out.println("У вас нет гномов");
        }
        System.out.println("Провизия (1-1 день): " + provision);
        System.out.println("Баланс крепости: " + balance + " монет");
    }
}