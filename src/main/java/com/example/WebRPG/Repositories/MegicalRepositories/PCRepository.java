package com.example.WebRPG.Repositories.MegicalRepositories;

import com.example.WebRPG.Model.Characters.MagicalBest.PC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PCRepository extends JpaRepository<PC, String> {
}
