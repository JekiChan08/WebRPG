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
import java.util.Random;

@Service
@RequiredArgsConstructor
@Data

public class GameDwarfServiceImpl implements GameDwarfService {

    @Autowired
    private final FortressRepository fortressRepository;
    @Autowired
    private GateRepository gateRepository;
    @Autowired
    private final DwarfGroupRepository dwarfGroupRepository;

    private Random random = new Random();
    private ArrayList<EnemiesGroup> enemies = new ArrayList<>();
    private ArrayList<DwarfGroup> reserve = new ArrayList<>();

    public GameDwarfServiceImpl() {
        fortressRepository = getFortressRepository();
        dwarfGroupRepository = getDwarfGroupRepository();
        enemies = new ArrayList<>();
        reserve = new ArrayList<>();
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

    }
}
