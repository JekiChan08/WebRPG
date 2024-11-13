package com.example.WebRPG.Service.Impl.dwarfImpl;

import com.example.WebRPG.Model.Characters.Dwarf.DwarfGroup;
import com.example.WebRPG.Model.Characters.Dwarf.Gate;
import com.example.WebRPG.Repositories.dwarfRepositories.GateRepository;
import com.example.WebRPG.Service.dwarf.GateService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Data
public class GateServiceImpl implements GateService {
    @Autowired
    private final GateRepository gateRepository;

    @Override
    public Gate findById(String id) {
        return gateRepository.findById(id).orElse(null);
    }

    @Override
    public Gate saveGateGroup(Gate gate) {
        return gateRepository.save(gate);
    }

    @Override
    public void deleteGateById(String id) {
        gateRepository.deleteById(id);
    }

    //Добавь уже этого долбанного гнома
    @Override
    public boolean addInDwarf(DwarfGroup dwarfGroup, Gate gate) {
        if (gate.getDwarf() != null) {
            gate.setDwarf(dwarfGroup);
            return true;
        }else {
            return false;
        }
    }
}
