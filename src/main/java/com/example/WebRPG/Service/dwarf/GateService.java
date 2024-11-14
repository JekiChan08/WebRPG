package com.example.WebRPG.Service.dwarf;

import com.example.WebRPG.Model.Characters.Dwarf.DwarfGroup;
import com.example.WebRPG.Model.Characters.Dwarf.Gate;

public interface GateService {
    Gate findById(String id);
    Gate saveGateGroup(Gate gate);
    void deleteGateById(String id);
    public boolean addInDwarf(DwarfGroup dwarfGroup, Gate gate);
}
