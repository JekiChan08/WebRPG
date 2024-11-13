package com.example.WebRPG.Service.Impl;


import com.example.WebRPG.Model.Characters.Person.MainHero;
import com.example.WebRPG.Repositories.ArmorRepository;
import com.example.WebRPG.Repositories.MainHeroRepository;
import com.example.WebRPG.Repositories.WeaponRepository;
import com.example.WebRPG.Service.MainHeroService;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class MainHeroServiceImpl implements MainHeroService {
    @Autowired
    private final MainHeroRepository mr;
    @Autowired
    private final ArmorRepository ar;
    @Autowired
    private final WeaponRepository wr;


    @Override
    public MainHero findById(String id) {
        return mr.findById(id).orElse(null);
    }

    @Override
    public MainHero saveMainHero(MainHero mainHero) {
        if (mainHero.getArmor() != null && mainHero.getArmor().getId() == null) {
            ar.save(mainHero.getArmor());
        }
        if (mainHero.getWeapon() != null && mainHero.getWeapon().getId() == null) {
            wr.save(mainHero.getWeapon());
        }
        return mr.save(mainHero);
    }

    @Override
    public void deleteMainHeroById(String id) {
        mr.deleteById(id);
    }

    @Override
    public List<MainHero> getAllMainHero() {
        return mr.findAll();
    }


    @Override
    public Optional<MainHero> getMainHeroById(String id) {
        return mr.findById(id);
    }

}
