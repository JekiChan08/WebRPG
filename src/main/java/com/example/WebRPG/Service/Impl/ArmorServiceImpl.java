package com.example.WebRPG.Service.Impl;

import com.example.WebRPG.Model.Characters.Person.MainHero;
import com.example.WebRPG.Model.Item.All.Armor;
import com.example.WebRPG.Repositories.ArmorRepository;
import com.example.WebRPG.Service.ArmorService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
public class ArmorServiceImpl implements ArmorService {
    private final ArmorRepository repository;

    @Override
    public void upgradeDefense(String armorId, int increase) {
        Armor armor = repository.findById(armorId).orElse(null);
        armor.upgradeDefense(increase);
        repository.save(armor);
    }
}
