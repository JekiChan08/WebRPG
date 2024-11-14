package com.example.WebRPG.Service.Impl.dwarfImpl;


import com.example.WebRPG.Model.Characters.Dwarf.DwarfGroup;
import com.example.WebRPG.Model.Characters.Dwarf.Fortress;
import com.example.WebRPG.Model.Characters.Dwarf.Gate;
import com.example.WebRPG.Model.Characters.Dwarf.enemies.EnemiesGroup;
import com.example.WebRPG.Model.Characters.Dwarf.enemies.GroupEnemies;
import com.example.WebRPG.Repositories.dwarfRepositories.DwarfGroupRepository;
import com.example.WebRPG.Repositories.dwarfRepositories.FortressRepository;
import com.example.WebRPG.Repositories.dwarfRepositories.GateRepository;
import com.example.WebRPG.Service.dwarf.GameDwarfService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Data
@Service
public class GameDwarfServiceImpl implements GameDwarfService {

    @Autowired
    private final FortressRepository fortressRepository;
    @Autowired
    private final GateRepository gateRepository;
    @Autowired
    private final DwarfGroupRepository dwarfGroupRepository;

    private final ArrayList<EnemiesGroup> enemies = new ArrayList<>();

    public GameDwarfServiceImpl() {
        fortressRepository = getFortressRepository();
        dwarfGroupRepository = getDwarfGroupRepository();
        gateRepository = getGateRepository();
        enemies.add(new EnemiesGroup(GroupEnemies.VAMPIRE));
        enemies.add(new EnemiesGroup(GroupEnemies.GOBLIN));
        enemies.add(new EnemiesGroup(GroupEnemies.SKELETON));
        enemies.add(new EnemiesGroup(GroupEnemies.COBALT));
        enemies.add(new EnemiesGroup(GroupEnemies.WOLF));
        enemies.add(new EnemiesGroup(GroupEnemies.EARTHWORMS));
        enemies.add(new EnemiesGroup(GroupEnemies.GIANT_MOLES));
        enemies.add(new EnemiesGroup(GroupEnemies.SPIDER));
    }

    @Override
    public void fortressDefense(Fortress fortress) {
        for (Gate gate : fortress.getGates()) {
            if (fortress.getDays() % 7 == 0) {
                enemies.forEach(enemy -> enemy.damageUp(fortress.getDays() / 7)); // Усиление монстров каждую неделю
            }

            if (gate.getSteps().size() > 0) {
                EnemiesGroup enemy = gate.getSteps().get(0);

                DwarfGroup defendingDwarf = gate.getDwarf();
                if (defendingDwarf != null && defendingDwarf.getHealth() > 0) {
                    enemy.setHealth(enemy.getHealth() - defendingDwarf.getDamage());
                    defendingDwarf.setHealth(defendingDwarf.getHealth() - Math.max(0, enemy.getDamage() - defendingDwarf.getProtection()));

                    if (defendingDwarf.getHealth() <= 0) {
                        gate.setDwarf(null);
                    }
                } else {
                    gate.setHealth(gate.getHealth() - enemy.getDamage());

                    if (gate.getHealth() <= 0) {
                        gate.setProtection(0);
                    }
                }

                if (enemy.getHealth() <= 0) {
                    gate.getSteps().remove(0);
                    fortress.setBalance(fortress.getBalance() + enemy.getCoin());
                }
            }

            if (gate.getHealth() <= 0 && !gate.getSteps().isEmpty()) {
                EnemiesGroup enemy = gate.getSteps().get(0);
                fortress.setHealth(fortress.getHealth() - enemy.getDamage());

                if (fortress.getHealth() <= 0) {
                    endGame(fortress);
                    return;
                }
            }
        }

        fortress.setDays(fortress.getDays() + 1);
        fortressRepository.save(fortress);
    }

    private void endGame(Fortress fortress) {
        fortress.setHealth(0);
        fortressRepository.save(fortress);
    }
}
