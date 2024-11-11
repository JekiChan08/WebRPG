package com.example.WebRPG.Service.Impl;

import com.example.WebRPG.Model.Characters.Person.Enemies;
import com.example.WebRPG.Model.Characters.Person.EnumEnemies;
import com.example.WebRPG.Model.Characters.Person.MainHero;
import com.example.WebRPG.Repositories.ArmorRepository;
import com.example.WebRPG.Repositories.MainHeroRepository;
import com.example.WebRPG.Repositories.WeaponRepository;
import com.example.WebRPG.Service.GameService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Data
public class GameServiceImpl implements GameService{
    private final WeaponRepository weaponRepository;
    private MainHeroRepository mainHeroRepository;

    private final ArmorRepository armorRepository;
    @Override
    public void attack(MainHero mainHero, Enemies enemies) {
        double damage = mainHero.getLevel() * enemies.getDamage() * (1 - mainHero.getArmor().getDefense() / 100.0);
        enemies.setHealth(enemies.getHealth() - mainHero.getDamage());
        if (enemies.getHealth() > 0) {
            mainHero.setHealth(mainHero.getHealth() - damage);

        }
        setEnd(mainHero);
    }
    @Override
    public void setEnd(MainHero mainHero) {
        if (mainHero.getHealth() <= 0) {
            mainHero.setEndGame(true);
            mainHeroRepository.save(mainHero);
        }
    }
    @Override
    public Enemies rnEnemies() {
        ArrayList<EnumEnemies> enumEnemies = new ArrayList<>();
        enumEnemies.add(EnumEnemies.SKELETON);
        enumEnemies.add(EnumEnemies.SPIDER);
        enumEnemies.add(EnumEnemies.GOBLIN);
        enumEnemies.add(EnumEnemies.GIANT);
        enumEnemies.add(EnumEnemies.VAMPIRE);
        enumEnemies.add(EnumEnemies.ZOMBIE);
        enumEnemies.add(EnumEnemies.BASILISK);
        enumEnemies.add(EnumEnemies.COBALT);
        enumEnemies.add(EnumEnemies.CYCLOPS);
        Random rn = new Random();
        return new Enemies(enumEnemies.get(rn.nextInt(enumEnemies.size())));
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

}

