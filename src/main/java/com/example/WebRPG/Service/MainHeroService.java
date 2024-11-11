package com.example.WebRPG.Service;

import com.example.WebRPG.Model.Characters.Person.MainHero;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


public interface MainHeroService {
    MainHero findById(String id);
    MainHero saveMainHero(MainHero mainHero);
    void deleteMainHeroById(String id);
    List<MainHero> getAllMainHero();
    public Optional<MainHero> getMainHeroById(String id);
}
