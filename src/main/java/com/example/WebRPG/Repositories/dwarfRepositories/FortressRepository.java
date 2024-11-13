package com.example.WebRPG.Repositories.dwarfRepositories;

import com.example.WebRPG.Model.Characters.Dwarf.Fortress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FortressRepository extends JpaRepository<Fortress, String> {
}
