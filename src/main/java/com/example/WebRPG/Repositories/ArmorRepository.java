package com.example.WebRPG.Repositories;


import com.example.WebRPG.Model.Item.All.Armor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmorRepository extends JpaRepository<Armor, String> {
}

