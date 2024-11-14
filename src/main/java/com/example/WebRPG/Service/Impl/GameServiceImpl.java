package com.example.WebRPG.Service.Impl;

import com.example.WebRPG.Model.Characters.Person.*;
import com.example.WebRPG.Repositories.ArmorRepository;
import com.example.WebRPG.Repositories.MainHeroRepository;
import com.example.WebRPG.Repositories.WeaponRepository;
import com.example.WebRPG.Service.GameService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Data
public class GameServiceImpl implements GameService{
    @Autowired
    private final WeaponRepository weaponRepository;
    @Autowired
    private MainHeroRepository mainHeroRepository;
    @Autowired
    private final ArmorRepository armorRepository;
    @Override
    public void attack(MainHero mainHero, Enemies enemies) {
        double damageToHero = calculateDamageToHero(enemies.getDamage(), mainHero.getArmor().getDefense());
        enemies.setHealth(enemies.getHealth() - mainHero.getDamage());

        if (enemies.getHealth() > 0) {
            mainHero.setHealth(mainHero.getHealth() - damageToHero);
        }

        setEnd(mainHero);
    }

    private double calculateDamageToHero(double enemyDamage, double heroDefense) {
        return enemyDamage * (1 - heroDefense / 100.0);
    }

    @Override
    public void setEnd(MainHero mainHero) {
        if (mainHero.getHealth() <= 0) {
            mainHero.setEndGame(true);
            mainHeroRepository.save(mainHero);
        }
    }

    @Override
    public Enemies rnEnemies(int level) {
        List<EnumEnemies> enumEnemies = Arrays.asList(EnumEnemies.values());
        Random rn = new Random();
        Enemies enemies = new Enemies(enumEnemies.get(rn.nextInt(enumEnemies.size())));
        enemies.setDamage(enemies.getDamage() * level);
        enemies.setHealth(45 + (level * 10));
        return enemies;
    }

    @Override
    public Enemies dwarfEnemies(int level) {
        List<EnumEnemies> enumEnemies = Arrays.asList(EnumEnemies.values());
        Random rn = new Random();
        Enemies enemies = new Enemies(enumEnemies.get(rn.nextInt(enumEnemies.size())));
        enemies.setDamage(enemies.getDamage() * level);
        enemies.setHealth(45 + (level * 10));
        return enemies;
    }
    @Override
    public Enemies magicalBestEnemies(int level) {
        List<EnumEnemies> enumEnemies = Arrays.asList(EnumEnemies.values());
        Random rn = new Random();
        Enemies enemies = new Enemies(enumEnemies.get(rn.nextInt(enumEnemies.size())));
        enemies.setDamage(enemies.getDamage() * level);
        enemies.setHealth(45 + (level * 10));
        return enemies;
    }

    @Override
    public Enemies elfEnemies(int level) {
        List<EnumEnemies> enumEnemies = new ArrayList<>();
        enumEnemies.add(EnumEnemies.SPYSPIRIT);
        enumEnemies.add(EnumEnemies.SPIDER);
        enumEnemies.add(EnumEnemies.DRAUGLIN);
        enumEnemies.add(EnumEnemies.DRAUGLIN);
        Enemies currentEnemies = new Enemies(enumEnemies.get(level - 1));
        currentEnemies.setDamage(currentEnemies.getDamage() * level);
        currentEnemies.setHealth(45 + (level * 10));
        return currentEnemies;
    }

    @Override
    public Enemies orcEnemies(int level) {
        List<EnumEnemies> enumEnemies = new ArrayList<>();
        enumEnemies.add(EnumEnemies.PERSON);
        enumEnemies.add(EnumEnemies.ORC);
        enumEnemies.add(EnumEnemies.DWARF);
        enumEnemies.add(EnumEnemies.DWARF);
        Enemies currentEnemies = new Enemies(enumEnemies.get(level - 1));
        currentEnemies.setDamage(currentEnemies.getDamage() * level);
        currentEnemies.setHealth(45 + (level * 10));
        return currentEnemies;
    }

    @Override
    public void attackBoss(MainHero mainHero, Boss boss) {
        double damageToHero = calculateDamageToHero(boss.getDamage(), mainHero.getArmor().getDefense());
        boss.setHealth(boss.getHealth() - mainHero.getDamage());

        if (boss.getHealth() > 0) {
            mainHero.setHealth(mainHero.getHealth() - damageToHero);
        }

        setEnd(mainHero);
    }

    @Override
    public Boss rnBoss(int level) {
        List<EnumBoss> enumBosses = Arrays.asList(EnumBoss.values());
        Random rn = new Random();
        EnumBoss selectedBoss = enumBosses.get(rn.nextInt(enumBosses.size()));
        return new Boss(selectedBoss);
    }


    @Override
    public boolean upgradeWeapon(MainHero mainHero) {
        if (mainHero.getMoney() >= 75) {
            mainHero.setMoney(mainHero.getMoney() - 75);
            weaponRepository.save(mainHero.getWeapon().upgradeWeapon());
            mainHeroRepository.save(mainHero);
            return true;
        } else return false;

    }
    @Override
    public boolean usePotion(MainHero mainHero) {
        if (mainHero.getMoney() >= 50) {
            mainHero.setHealth(mainHero.getHealth() + 50);
            mainHero.setMoney(mainHero.getMoney() - 50);
            mainHeroRepository.save(mainHero);
            return true;
        }
        return false;
    }


    Thread thread = new Thread();


}

