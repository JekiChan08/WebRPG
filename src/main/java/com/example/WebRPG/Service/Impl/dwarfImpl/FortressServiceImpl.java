package com.example.WebRPG.Service.Impl.dwarfImpl;

import com.example.WebRPG.Model.Characters.Dwarf.DwarfGroup;
import com.example.WebRPG.Model.Characters.Dwarf.Fortress;
import com.example.WebRPG.Model.Characters.Dwarf.Gate;
import com.example.WebRPG.Repositories.dwarfRepositories.FortressRepository;
import com.example.WebRPG.Repositories.dwarfRepositories.GateRepository;
import com.example.WebRPG.Service.dwarf.FortressService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class FortressServiceImpl implements FortressService {
    @Autowired
    private final FortressRepository fortressRepository;
    @Autowired
    private GateRepository gateRepository;

    @Override
    public Fortress findById(String id) {
        return fortressRepository.findById(id).orElse(null);
    }

    @Override
    public Fortress saveFortress(Fortress fortress) {
        return fortressRepository.save(fortress);
    }

    @Override
    public void deleteFortressById(String id) {
        fortressRepository.deleteById(id);
    }

    @Override
    public Optional<Fortress> getFortressById(String id) {
        return fortressRepository.findById(id);
    }

    @Override
    public void addNewDwarf(Fortress fortress) {
        DwarfGroup newDwarf = new DwarfGroup();
        newDwarf.setFortress(fortress);
        fortress.getDwarfGroups().add(newDwarf);
        fortressRepository.save(fortress);
    }
    @Override
    public void addGatesToFortress(String fortressId) {
        Fortress fortress = fortressRepository.findById(fortressId).orElse(null);

        Gate left = new Gate("на Левых воротах");
        Gate right = new Gate("на правых воротах");
        Gate upper = new Gate("на верхних воротах");
        Gate lower = new Gate("на нижних воротах");

        left.setFortress(fortress);
        right.setFortress(fortress);
        upper.setFortress(fortress);
        lower.setFortress(fortress);

        gateRepository.save(left);
        gateRepository.save(right);
        gateRepository.save(upper);
        gateRepository.save(lower);

        fortress.getGates().add(left);
        fortress.getGates().add(right);
        fortress.getGates().add(upper);
        fortress.getGates().add(lower);

        fortressRepository.save(fortress);
    }
}
