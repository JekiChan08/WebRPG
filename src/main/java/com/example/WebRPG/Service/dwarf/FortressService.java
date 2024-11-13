package com.example.WebRPG.Service.dwarf;

import com.example.WebRPG.Model.Characters.Dwarf.Fortress;

import java.util.Optional;

public interface FortressService {
    Fortress findById(String id);
    Fortress saveFortress(Fortress fortress);
    void deleteFortressById(String id);
    Optional<Fortress> getFortressById(String id);
    public void addNewDwarf(Fortress fortress);
    public void addGatesToFortress(String fortressId);

}
