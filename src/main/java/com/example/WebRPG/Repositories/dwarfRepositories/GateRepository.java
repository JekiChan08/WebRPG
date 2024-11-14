package com.example.WebRPG.Repositories.dwarfRepositories;

import com.example.WebRPG.Model.Characters.Dwarf.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateRepository extends JpaRepository<Gate, String> {

}

