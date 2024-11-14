package com.example.WebRPG.Service.dwarf;

import com.example.WebRPG.Model.Characters.Dwarf.DwarfGroup;
import com.example.WebRPG.Model.Characters.Dwarf.Fortress;

import java.util.Optional;

public interface DwarfGroupService {
    DwarfGroup findById(String id);
    DwarfGroup saveDwarfGroup(DwarfGroup dwarfGroup);
    void deleteDwarfGroupById(String id);
    Optional<DwarfGroup> getDwarfById(String id);
}
