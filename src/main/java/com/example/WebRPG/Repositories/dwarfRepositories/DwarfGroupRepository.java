package com.example.WebRPG.Repositories.dwarfRepositories;


import com.example.WebRPG.Model.Characters.Dwarf.DwarfGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DwarfGroupRepository extends JpaRepository<DwarfGroup, String> {
}
