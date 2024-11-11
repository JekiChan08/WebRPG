package com.example.WebRPG.Repositories;


import com.example.WebRPG.Model.Item.All.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, String> {
}
