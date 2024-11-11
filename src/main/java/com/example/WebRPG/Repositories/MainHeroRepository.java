package com.example.WebRPG.Repositories;


import com.example.WebRPG.Model.Characters.Person.MainHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainHeroRepository extends JpaRepository<MainHero, String> {
}
